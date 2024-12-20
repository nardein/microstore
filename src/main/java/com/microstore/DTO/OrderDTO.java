package com.microstore.DTO;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.Date;

@Data
public class OrderDTO {

    @NotNull
    private long user_id;  // Riferimento all'ID dell'utente

    @NotNull
    private String status;

    @NotNull
    private int total;

    private Date createdAt;
}
