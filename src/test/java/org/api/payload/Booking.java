package org.api.payload;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Booking {

    private String firstname;
    private String lastname;
    @JsonProperty("totalprice")
    private int totalPrice;
    @JsonProperty("depositpaid")
    private boolean depositPaid;
    @JsonProperty("bookingdates")
    private BookingDates bookingDates;
    @JsonProperty("additionalneeds")
    private String additionalNeeds;

}
