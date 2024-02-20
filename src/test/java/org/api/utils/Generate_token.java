package org.api.utils;

import com.jayway.jsonpath.JsonPath;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.api.endpoints.Routes;

import static io.restassured.RestAssured.given;

public class Generate_token {
    public static String token;

    public static String getToken(String path) {
        if (token == null) {
            String tokenAPIRequestBody = FileReader.getData(path);
            Response tokenAPIResponse =
                    given().contentType(ContentType.JSON).body(tokenAPIRequestBody).when()
                            .post(Routes.auth_post_url)
                            .then().assertThat().statusCode(200)
                            .extract().response();
            ;
            token = JsonPath.read(tokenAPIResponse.body().asString(), "$.token");
        }


        return token;
    }
}
