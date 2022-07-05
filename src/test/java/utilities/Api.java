package utilities;

import com.google.gson.Gson;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.internal.http.Status;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;

public class Api {

    public static final String BOOKING = "/booking";

    private static final String BASE_URI = ConfigReader.getProperty("base_uri");
    private static final String AUTH = "/auth";

    private static final String TOKEN = "token";
    private static final String USERNAME = "username";
    private static final String PASSWORD = "password";

    private static final Gson gson = new Gson();

    private Api(){}

    private static RequestSpecification getRequest() {
        return given()
                .baseUri(BASE_URI)
                .headers(getHeaderSpecs())
                .when();
    }

    private static Map<String, Object> getHeaderSpecs() {
        Map<String, Object> headerSpecs = new HashMap<>();
        headerSpecs.put("Content-Type", ContentType.JSON);
        headerSpecs.put("Accept", ContentType.JSON);
        headerSpecs.put("Cookie", String.format("%s=%s", TOKEN, generateToken()));

        return headerSpecs;
    }

    private static String generateToken(){
        Map<String, Object> reqBody = new HashMap<>();
        reqBody.put(USERNAME, ConfigReader.getProperty(USERNAME));
        reqBody.put(PASSWORD, ConfigReader.getProperty(PASSWORD));

        return given().headers("Content-Type", ContentType.JSON)
                .body(gson.toJson(reqBody))
                .post(String.format("%s%s", BASE_URI, AUTH)).then().statusCode(200).extract().path(TOKEN);
    }

    public static ValidatableResponse get(String endpoint){
        return getRequest().get(endpoint).then();
    }

    public static ValidatableResponse post(String endpoint, Map<String, Object> reqBody){
        return getRequest().body(gson.toJson(reqBody)).post(endpoint).then();
    }

    public static ValidatableResponse put(String endpoint, Map<String, Object> reqBody){
        return getRequest().body(gson.toJson(reqBody)).put(endpoint).then();
    }

    public static ValidatableResponse patch(String endpoint, Map<String, Object> reqBody){
        return getRequest().body(gson.toJson(reqBody)).patch(endpoint).then();
    }

    public static ValidatableResponse delete(String endpoint){
        return getRequest().delete(endpoint).then();
    }
}
