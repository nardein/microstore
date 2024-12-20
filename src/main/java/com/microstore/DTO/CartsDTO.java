package com.microstore.DTO;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.Date;

@Data
public class CartsDTO {

    @NotNull
    private long id;

    @NotNull
    private long user_id;

    @NotNull
    private Date created_at;
}
