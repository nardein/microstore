package com.microstore.model.usermod;

import lombok.Data;

@Data
public class LoginResponse {

    private String message;
    private String token;
}
