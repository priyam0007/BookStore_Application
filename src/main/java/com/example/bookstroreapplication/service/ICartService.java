package com.example.bookstroreapplication.service;

import com.example.bookstroreapplication.dto.CartDTO;
import com.example.bookstroreapplication.dto.ResponseDTO;
import com.example.bookstroreapplication.model.Cart;

public interface ICartService {

    Cart insertItems(CartDTO cartdto);
    Cart getCartDetailsById(Integer cartId);

    Cart deleteCartItemById(Integer cartId);

    Cart updateRecordById(Integer cartId, CartDTO cartDTO);

    Cart getCartRecordByBookId(Integer bookID);

    ResponseDTO getCartDetails();
}

