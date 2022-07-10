package utilities;

import io.restassured.http.ContentType;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;
import pojos.User;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;

public class Api {

    private static final String BASE_URI = ConfigReader.getProperty("base_uri");
    private static final String AUTH = "/auth";

    private static final String TOKEN = "token";
    private static final String USERNAME = "username";
    private static final String PASSWORD = "password";

    private static final Map<String, Object> headerSpecs = new HashMap<>();

    public static ValidatableResponse response;

    private static RequestSpecification getRequest() {
        return given()
                .baseUri(BASE_URI)
                .headers(getHeaderSpecs())
                .when();
    }

    private static Map<String, Object> getHeaderSpecs() {
        headerSpecs.put("Content-Type", ContentType.JSON);
        headerSpecs.put("Accept", ContentType.JSON);

        return headerSpecs;
    }

    private static void generateToken() {
        User user = new User(ConfigReader.getProperty(USERNAME), ConfigReader.getProperty(PASSWORD));

        String token = given().headers(getHeaderSpecs())
                .body(user)
                .post(String.format("%s%s", BASE_URI, AUTH))
                .then().statusCode(200).extract().path(TOKEN);

        headerSpecs.put("Cookie", String.format("%s=%s", TOKEN, token));
    }

    public static ValidatableResponse get(String endpoint) {
        response = getRequest().get(endpoint).then();
        return response;
    }

    public static ValidatableResponse post(String endpoint, Object reqBody) {
        response = getRequest().body(reqBody).post(endpoint).then();
        return response;
    }

    public static ValidatableResponse put(String endpoint, Object reqBody) {
        generateToken();
        response = getRequest().body(reqBody).put(endpoint).then();
        return response;
    }

    public static ValidatableResponse patch(String endpoint, Object reqBody) {
        generateToken();
        response = getRequest().body(reqBody).patch(endpoint).then();
        return response;
    }

    public static ValidatableResponse delete(String endpoint) {
        generateToken();
        response = getRequest().delete(endpoint).then();
        return response;
    }
}
