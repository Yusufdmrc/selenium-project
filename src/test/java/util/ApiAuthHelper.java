package util;

import io.restassured.response.Response;

import java.util.Map;
import java.util.UUID;

import static io.restassured.RestAssured.given;

public class ApiAuthHelper {

    public static Response login(String accountCode, String password) {
        String userAgent = "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_7) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/145.0.0.0 Safari/537.36";

        // Gerçek login request body yapısı (cURL'den alındı)
        String body = String.format(
                "{" +
                "\"channelId\":1," +
                "\"password\":\"%s\"," +
                "\"accountCode\":\"%s\"," +
                "\"requestId\":\"%s\"," +
                "\"sessionId\":\"%s\"," +
                "\"channelInfo\":\"%s\"," +
                "\"flagConto\":\"1\"," +
                "\"recaptchaToken\":\"\"" +
                "}",
                password,
                accountCode,
                UUID.randomUUID().toString(),
                UUID.randomUUID().toString(),
                userAgent
        );

        // Selenium ile otomatik cookie al (10 dk cache'li)
        Map<String, String> akamaiCookies = SeleniumCookieHelper.getAkamaiCookies();

        var requestSpec = given().spec(ApiBase.getRequestSpec());

        // Cookie'leri request'e ekle
        for (Map.Entry<String, String> entry : akamaiCookies.entrySet()) {
            requestSpec = requestSpec.cookie(entry.getKey(), entry.getValue());
        }

        return requestSpec
                .body(body)
                .when()
                .post(ApiBase.getEndpoint("api.login.endpoint"))
                .then()
                .spec(ApiBase.getResponseSpec())
                .extract()
                .response();
    }
}

