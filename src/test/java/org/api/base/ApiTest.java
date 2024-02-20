package org.api.base;

import io.qameta.allure.*;
import io.restassured.module.jsv.JsonSchemaValidator;
import io.restassured.response.Response;
import org.apache.commons.io.FileUtils;
import org.api.endpoints.BookEndpoint;
import org.api.utils.FileConstant;
import org.api.utils.Generate_token;
import org.hamcrest.Matchers;
import org.testng.annotations.Test;

import java.io.File;
import java.io.IOException;

import static org.testng.Assert.assertEquals;

@Epic("API Automation")
public class ApiTest extends Base {
    int bookingId;

    @Test(description = "To create a new booking", priority = 1)
    @Description("Create Booking")
    @Severity(SeverityLevel.CRITICAL)
    @Owner("karthik")
    public void testCreateBooking() throws IOException {
        try {
            String jsonSchema = FileUtils.readFileToString(new File(FileConstant.JSON_SCHEMA), "UTF-8");
            Response response = BookEndpoint.createBooking(booking);
            response.then().assertThat().statusCode(200).body(JsonSchemaValidator.matchesJsonSchema(jsonSchema));
            bookingId = response.then().log().all().statusCode(200)
                    .extract()
                    .path("bookingid");
            Allure.step("Validating the Response body");
            assertEquals(response.getStatusCode(), 200);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test(description = "To get bookings by id", priority = 2)
    @Description("Get Bookings by id")
    @Severity(SeverityLevel.CRITICAL)
    @Owner("karthik")
    public void testGetBookingById() {
        try {
            Response response = BookEndpoint.getBookingById(bookingId);
            response.then().log().all();
            Allure.step("Validating the Response body");
            assertEquals(response.getStatusCode(), 200);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test(description = "To update an existing booking", priority = 3)
    @Description("Update Booking")
    @Severity(SeverityLevel.CRITICAL)
    @Owner("karthik")
    public void testUpdateBookingById() {
        try {
            String token = Generate_token.getToken(FileConstant.TOKEN_REQUEST_BODY);
            booking.setTotalPrice(500);
            booking.setAdditionalNeeds("BreakFast");
            Response response = BookEndpoint.updateBookingById(bookingId, booking, token);
            response.then().log().all().assertThat().body("additionalneeds", Matchers.equalTo("BreakFast"));
            Allure.step("Validating the Response body");
            assertEquals(response.getStatusCode(), 200);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test(description = "To delete the booking", priority = 4)
    @Description("Delete Booking")
    @Severity(SeverityLevel.CRITICAL)
    @Owner("karthik")
    public void testDeleteBooking() {
        try {
            String token = Generate_token.getToken(FileConstant.TOKEN_REQUEST_BODY);
            Response response = BookEndpoint.deleteBooking(bookingId, token);
            response.then().log().all();
            Allure.step("Validating the Response body");
            assertEquals(response.getStatusCode(), 201);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
