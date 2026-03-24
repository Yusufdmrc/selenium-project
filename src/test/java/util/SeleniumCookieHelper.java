package util;

import org.openqa.selenium.Cookie;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import io.github.bonigarcia.wdm.WebDriverManager;
import reporting.Logging;

import java.time.Duration;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * Selenium ile Akamai cookie'lerini otomatik olarak alır.
 * API testlerinden önce bir kez çalıştırılarak cookie'leri cache'e alır.
 */
public class SeleniumCookieHelper {

    private static Map<String, String> cachedCookies = null;
    private static long cacheTimestamp = 0;
    private static final long CACHE_DURATION_MS = 10 * 60 * 1000; // 10 dakika

    /**
     * Selenium ile login yapıp Akamai cookie'lerini alır.
     * 10 dakika boyunca cache'ler, sonra yeniden alır.
     */
    public static Map<String, String> getAkamaiCookies() {
        // Cache'de varsa ve geçerliyse cache'den dön
        if (cachedCookies != null && (System.currentTimeMillis() - cacheTimestamp) < CACHE_DURATION_MS) {
            Logging.writeConsoleLog("Using cached Akamai cookies (age: " +
                ((System.currentTimeMillis() - cacheTimestamp) / 1000) + "s)");
            return cachedCookies;
        }

        Logging.writeConsoleLog("Fetching fresh Akamai cookies via Selenium...");

        WebDriver driver = null;
        try {
            // Headless Chrome başlat
            WebDriverManager.chromedriver().setup();
            ChromeOptions options = new ChromeOptions();
            options.addArguments("--headless=new");
            options.addArguments("--disable-gpu");
            options.addArguments("--no-sandbox");
            options.addArguments("--disable-dev-shm-usage");
            options.addArguments("--window-size=1920,1080");
            options.addArguments("--disable-blink-features=AutomationControlled");
            options.addArguments("--disable-extensions");
            options.addArguments("--disable-images"); // Resimleri yükleme, hızlandır
            options.addArguments("--blink-settings=imagesEnabled=false");
            options.setPageLoadStrategy(org.openqa.selenium.PageLoadStrategy.EAGER); // Tüm sayfa yüklenmesini bekleme
            options.setExperimentalOption("excludeSwitches", new String[]{"enable-automation"});
            options.setExperimentalOption("useAutomationExtension", false);

            driver = new ChromeDriver(options);
            driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(60)); // Timeout'u artır
            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));

            // Ana sayfaya git (Akamai cookie'lerini almak için) - EAGER mod ile hızlı
            String wwwUrl = ApiBase.getWwwBaseUrl();
            Logging.writeConsoleLog("Navigating to: " + wwwUrl);

            try {
                driver.get(wwwUrl);
                Thread.sleep(2000); // DOM hazır olana kadar kısa bekle
            } catch (org.openqa.selenium.TimeoutException e) {
                Logging.writeConsoleLog("Homepage timeout (expected with EAGER mode), continuing...");
            }

            // Login sayfasına git
            Logging.writeConsoleLog("Opening login page...");
            try {
                driver.get(wwwUrl + "/giris");
                Thread.sleep(2000);
            } catch (org.openqa.selenium.TimeoutException e) {
                Logging.writeConsoleLog("Login page timeout (expected with EAGER mode), continuing...");
                Thread.sleep(1000);
            }

            // Cookie popup'ı kapat (varsa)
            try {
                driver.findElement(org.openqa.selenium.By.xpath("//*[@id='onetrust-close-btn-container']/button")).click();
                Thread.sleep(1000);
                Logging.writeConsoleLog("Cookie popup closed");
            } catch (Exception e) {
                Logging.writeConsoleLog("No cookie popup found, continuing...");
            }

            // Login form'unu doldur
            Logging.writeConsoleLog("Filling login form...");

            // Username alanını bekle ve doldur
            org.openqa.selenium.support.ui.WebDriverWait wait =
                new org.openqa.selenium.support.ui.WebDriverWait(driver, Duration.ofSeconds(10));

            org.openqa.selenium.WebElement usernameField = wait.until(
                org.openqa.selenium.support.ui.ExpectedConditions.presenceOfElementLocated(
                    org.openqa.selenium.By.id("username")));
            usernameField.sendKeys(ConfigReader.get("correct.tc.id"));

            driver.findElement(org.openqa.selenium.By.id("password"))
                  .sendKeys(ConfigReader.get("correct.password"));

            // Login butonuna tıkla (class veya type ile)
            try {
                driver.findElement(org.openqa.selenium.By.cssSelector("button.login-btn")).click();
            } catch (Exception e) {
                // Alternatif: type'a göre bul
                driver.findElement(org.openqa.selenium.By.cssSelector("button[type='submit']")).click();
            }
            Thread.sleep(5000); // Login sonrası cookie'lerin set edilmesini bekle

            // Cookie'leri al
            Set<Cookie> allCookies = driver.manage().getCookies();
            Map<String, String> akamaiCookies = new HashMap<>();

            for (Cookie cookie : allCookies) {
                String name = cookie.getName();
                // Sadece gerekli cookie'leri al
                if (name.equals("XSRF-TOKEN") ||
                    name.equals("ak_bmsc") ||
                    name.equals("_abck") ||
                    name.equals("bm_sv") ||
                    name.equals("bm_sz")) {
                    akamaiCookies.put(name, cookie.getValue());
                    Logging.writeConsoleLog("Cookie found: " + name + " = " +
                        cookie.getValue().substring(0, Math.min(50, cookie.getValue().length())) + "...");
                }
            }

            if (akamaiCookies.isEmpty()) {
                Logging.writeConsoleLog("WARNING: No Akamai cookies found! Login might have failed.");
                // Fallback: config.properties'den oku
                return getFallbackCookies();
            }

            // Cache'e kaydet
            cachedCookies = akamaiCookies;
            cacheTimestamp = System.currentTimeMillis();

            Logging.writeConsoleLog("Successfully fetched " + akamaiCookies.size() + " Akamai cookies");
            return akamaiCookies;

        } catch (Exception e) {
            Logging.writeConsoleLog("ERROR: Failed to fetch cookies via Selenium: " + e.getMessage());
            e.printStackTrace();
            // Fallback: config.properties'den oku
            return getFallbackCookies();
        } finally {
            if (driver != null) {
                driver.quit();
            }
        }
    }

    /**
     * Selenium başarısız olursa config.properties'den cookie'leri oku
     */
    private static Map<String, String> getFallbackCookies() {
        Logging.writeConsoleLog("Using fallback: reading cookies from config.properties");
        Map<String, String> cookies = new HashMap<>();

        String xsrfToken = ConfigReader.get("akamai.xsrf.token");
        String akBmsc = ConfigReader.get("akamai.ak_bmsc");
        String abck = ConfigReader.get("akamai.abck");
        String bmSv = ConfigReader.get("akamai.bm_sv");
        String bmSz = ConfigReader.get("akamai.bm_sz");

        if (xsrfToken != null && !xsrfToken.isEmpty()) cookies.put("XSRF-TOKEN", xsrfToken);
        if (akBmsc != null && !akBmsc.isEmpty()) cookies.put("ak_bmsc", akBmsc);
        if (abck != null && !abck.isEmpty()) cookies.put("_abck", abck);
        if (bmSv != null && !bmSv.isEmpty()) cookies.put("bm_sv", bmSv);
        if (bmSz != null && !bmSz.isEmpty()) cookies.put("bm_sz", bmSz);

        return cookies;
    }

    /**
     * Cache'i temizle (test sonunda veya yeni oturum için)
     */
    public static void clearCache() {
        cachedCookies = null;
        cacheTimestamp = 0;
        Logging.writeConsoleLog("Cookie cache cleared");
    }
}




