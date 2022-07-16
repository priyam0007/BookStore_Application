package com.example.bookstroreapplication.dto;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class CartDTO {
    public Integer userId;
    public Integer bookId;
    @NotNull(message="Book quantity yet to be provided")
    public Integer quantity;
    public int total;
}

