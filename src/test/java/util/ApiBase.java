package util;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.config.HttpClientConfig;
import io.restassured.config.RestAssuredConfig;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

public class ApiBase {

    private static final int CONNECTION_TIMEOUT_MS = 30_000;
    private static final int SOCKET_TIMEOUT_MS     = 30_000;

    private static RequestSpecification requestSpec;
    private static ResponseSpecification responseSpec;

    public static RequestSpecification getRequestSpec() {
        if (requestSpec == null) {
            RestAssuredConfig config = RestAssuredConfig.config()
                    .httpClient(HttpClientConfig.httpClientConfig()
                            .setParam("http.connection.timeout", CONNECTION_TIMEOUT_MS)
                            .setParam("http.socket.timeout", SOCKET_TIMEOUT_MS));

            String wwwBaseUrl = getWwwBaseUrl(); // Origin/Referer için www subdomain

            requestSpec = new RequestSpecBuilder()
                    .setBaseUri(getApiBaseUrl())
                    .setContentType(ContentType.JSON)
                    .addHeader("accept", "*/*")
                    .addHeader("accept-language", "en-US,en;q=0.9,tr;q=0.8")
                    .addHeader("User-Agent", "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_7) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/145.0.0.0 Safari/537.36")
                    .addHeader("Origin", wwwBaseUrl)
                    .addHeader("Referer", wwwBaseUrl + "/")
                    .addHeader("sec-ch-ua", "\"Not:A-Brand\";v=\"99\", \"Google Chrome\";v=\"145\", \"Chromium\";v=\"145\"")
                    .addHeader("sec-ch-ua-mobile", "?0")
                    .addHeader("sec-ch-ua-platform", "\"macOS\"")
                    .addHeader("Sec-Fetch-Site", "same-site")
                    .addHeader("Sec-Fetch-Mode", "cors")
                    .addHeader("Sec-Fetch-Dest", "empty")
                    .addHeader("priority", "u=1, i")
                    .setConfig(config)
                    .log(LogDetail.ALL)
                    .build();
        }
        return requestSpec;
    }

    public static ResponseSpecification getResponseSpec() {
        if (responseSpec == null) {
            responseSpec = new ResponseSpecBuilder()
                    .log(LogDetail.ALL)
                    .build();
        }
        return responseSpec;
    }

    public static String getApiBaseUrl() {
        String testEnv = System.getProperty("testEnv", "test");
        switch (testEnv.toLowerCase()) {
            case "test":    return ConfigReader.get("api.base.url.test");
            case "preprod": return ConfigReader.get("api.base.url.preprod");
            case "prod":    return ConfigReader.get("api.base.url.prod");
            default: throw new IllegalArgumentException("Unknown API environment: " + testEnv);
        }
    }

    // Origin ve Referer için www subdomain'i (areaprivata değil)
    public static String getWwwBaseUrl() {
        String testEnv = System.getProperty("testEnv", "test");
        switch (testEnv.toLowerCase()) {
            case "test":    return "https://test-www.millipiyangoonline.com";
            case "preprod": return "http://preprod-www.millipiyangoonline.com";
            case "prod":    return "https://www.millipiyangoonline.com";
            default: throw new IllegalArgumentException("Unknown API environment: " + testEnv);
        }
    }

    public static String getEndpoint(String key) {
        return ConfigReader.get(key);
    }
}
