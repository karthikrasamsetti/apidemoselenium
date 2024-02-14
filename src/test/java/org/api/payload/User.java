package org.api.payload;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class User {
    int id;
    String username;
    String firstName;
    String lastName;
    String email;
    String password;
    String phone;
    int userStatus = 0;
}
