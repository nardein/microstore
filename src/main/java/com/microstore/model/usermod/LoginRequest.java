package com.microstore.model.usermod;

import jakarta.validation.constraints.Email;
import lombok.Data;
import jakarta.validation.constraints.NotNull;
@Data
public class LoginRequest {

    @NotNull (message = "User not found")
    @Email(message = "Email should be valid")
    private String user;

    @NotNull(message = "Password not found")
    private String password;
}
