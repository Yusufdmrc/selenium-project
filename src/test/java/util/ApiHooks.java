package util;

import io.cucumber.java.After;
import io.cucumber.java.AfterAll;
import io.cucumber.java.Before;
import io.cucumber.java.BeforeAll;

public class ApiHooks {

    @BeforeAll
    public static void setUpApiSuite() {
        // İlk API testi başlamadan önce cookie cache'i temizle
        SeleniumCookieHelper.clearCache();
    }

    @Before("@api")
    public void setUpApi() {
        ScenarioContext.clear();
    }

    @After("@api")
    public void tearDownApi() {
        ScenarioContext.clear();
    }

    @AfterAll
    public static void tearDownApiSuite() {
        // Tüm API testleri bittikten sonra cache'i temizle
        SeleniumCookieHelper.clearCache();
    }
}

