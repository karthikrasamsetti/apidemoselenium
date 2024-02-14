package org.api.base;

import io.restassured.response.Response;
import org.api.endpoints.BookEndpoint;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

public class ApiTest extends Base {
    @Test(priority = 1)
    public void testCreateToken() {
        test = extent.createTest("testCreateToken")
                .assignAuthor("karthik").assignDevice("Windows Desktop");
        Response response = BookEndpoint.createToken(createToken);
        response.then().log().all();
        String token = response.then().statusCode(200)
                .extract()
                .path("token");
        System.out.println(token);
        assertEquals(response.getStatusCode(), 200);
    }

    @Test(priority = 2)
    public void testCreateBooking() {
        test = extent.createTest("testCreateBooking")
                .assignAuthor("karthik").assignDevice("Windows Desktop");
        Response response = BookEndpoint.createBooking(booking);
        response.then().log().all();
        System.out.println(response.getBody().asString());
        assertEquals(response.getStatusCode(), 200);
    }

    @Test(priority = 3)
    public void testDeleteBooking() {
        test = extent.createTest("testDeleteBooking")
                .assignAuthor("karthik").assignDevice("Windows Desktop");
        Response response = BookEndpoint.deleteBooking(401);
        response.then().log().all();
        System.out.println(response.getBody().asString());
        assertEquals(response.getStatusCode(), 201);
    }
}
