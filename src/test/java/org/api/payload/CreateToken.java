package org.api.payload;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateToken {
    private String username;
    private String password;
}
