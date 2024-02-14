package org.api.base;

import io.restassured.response.Response;
import org.api.endpoints.BookEndpoint;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

public class ApiTest extends Base {
    int bookingId;
    String token;
    @Test(priority = 1)
    public void testCreateToken() {
        test = extent.createTest("testCreateToken")
                .assignAuthor("karthik").assignDevice("Windows Desktop");
        Response response = BookEndpoint.createToken(createToken);
        response.then().log().all();
        token = response.then().statusCode(200)
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
        bookingId= response.then().log().all().statusCode(200)
                .extract()
                .path("bookingid");
        assertEquals(response.getStatusCode(), 200);
    }
    @Test(priority = 3)
    public void testGetBookingById() {
        test = extent.createTest("testReadBookingById")
                .assignAuthor("karthik").assignDevice("Windows Desktop");
        Response response = BookEndpoint.getBookingById(bookingId);
        response.then().log().all();
        System.out.println(response.getBody().asString());
        assertEquals(response.getStatusCode(), 200);
    }
    @Test(priority = 4)
    public void testUpdateBookingById() {
        test = extent.createTest("testUpdateBookingById")
                .assignAuthor("karthik").assignDevice("Windows Desktop");
        booking.setTotalPrice(500);
        booking.setAdditionalNeeds("BreakFast");
        Response response = BookEndpoint.updateBookingById(bookingId,booking,token);
        response.then().log().all();
        assertEquals(response.getStatusCode(), 200);
    }

    @Test(priority = 5)
    public void testDeleteBooking() {
        test = extent.createTest("testDeleteBooking")
                .assignAuthor("karthik").assignDevice("Windows Desktop");
        Response response = BookEndpoint.deleteBooking(bookingId,token);
        response.then().log().all();
        System.out.println(response.getBody().asString());
        assertEquals(response.getStatusCode(), 201);
    }
}
