package org.api.base;

import io.restassured.module.jsv.JsonSchemaValidator;
import io.restassured.response.Response;
import org.apache.commons.io.FileUtils;
import org.api.endpoints.BookEndpoint;
import org.api.utils.FileConstant;
import org.testng.annotations.Test;

import java.io.File;
import java.io.IOException;

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
        assertEquals(response.getStatusCode(), 200);
    }

    @Test(priority = 2)
    public void testCreateBooking() throws IOException {
        test = extent.createTest("testCreateBooking")
                .assignAuthor("karthik").assignDevice("Windows Desktop");
        String jsonSchema = FileUtils.readFileToString(new File(FileConstant.JSONSCHEMA_PATH), "UTF-8");
        Response response = BookEndpoint.createBooking(booking);
        response.then().assertThat().statusCode(200).body(JsonSchemaValidator.matchesJsonSchema(jsonSchema));
        bookingId = response.then().log().all().statusCode(200)
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
        assertEquals(response.getStatusCode(), 200);
    }

    @Test(priority = 4)
    public void testUpdateBookingById() {
        test = extent.createTest("testUpdateBookingById")
                .assignAuthor("karthik").assignDevice("Windows Desktop");
        booking.setTotalPrice(500);
        booking.setAdditionalNeeds("BreakFast");
        Response response = BookEndpoint.updateBookingById(bookingId, booking, token);
        response.then().log().all();
        assertEquals(response.getStatusCode(), 200);
    }

    @Test(priority = 5)
    public void testDeleteBooking() {
        test = extent.createTest("testDeleteBooking")
                .assignAuthor("karthik").assignDevice("Windows Desktop");
        Response response = BookEndpoint.deleteBooking(bookingId, token);
        response.then().log().all();
        assertEquals(response.getStatusCode(), 201);
    }
}
