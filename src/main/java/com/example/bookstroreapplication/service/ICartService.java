package com.example.bookstroreapplication.service;

import com.example.bookstroreapplication.dto.CartDTO;
import com.example.bookstroreapplication.dto.ResponseDTO;
import com.example.bookstroreapplication.model.Cart;

import java.util.List;

public interface ICartService {

    String insertItems(CartDTO cartdto);
    List<Cart> getCartDetails(String token);
    Cart getCartDetailsById(String token);
    void deleteCartItemById(String token);
    Cart updateRecordById(String token, CartDTO cartDTO);
}

