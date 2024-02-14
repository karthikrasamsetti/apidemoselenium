package org.api.payload;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class BookingDates {
    private String checkin;
    private String checkout;


}
