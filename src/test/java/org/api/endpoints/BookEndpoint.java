package org.api.endpoints;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.api.payload.Booking;
import org.api.payload.CreateToken;

import static io.restassured.RestAssured.given;

public class BookEndpoint {
    public static Response createToken(CreateToken createToken) {

        return given()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .body(createToken)
                .when()
                .post(Routes.auth_post_url);
    }

    public static Response createBooking(Booking booking) {
        return given()
                .log().all()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .body(booking)
                .when()
                .post(Routes.booking_post_url);
    }

    public static Response deleteBooking(int bookingid) {
        return given()
                .header("Cookie", "token=2735aefc2a1f042")
                .pathParam("id", bookingid)
                .when()
                .delete(Routes.booking_delete_url);
    }

}
