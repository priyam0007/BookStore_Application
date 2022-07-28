package com.example.bookstroreapplication.service;

import com.example.bookstroreapplication.dto.CartDTO;
import com.example.bookstroreapplication.model.Cart;

import java.util.List;
import java.util.Optional;

public interface ICartService {

    String insertItems(CartDTO cartdto);
    List<Cart> getCartDetails();
    Cart getCartDetailsById(int id);
    Optional<Cart> deleteCartItemById(int id);
    Cart updateRecordById(int id, CartDTO cartDTO);

    Cart updateQuntity(Integer cartId, int qunatity, int total);

    Cart updateCartByid(int cartid, CartDTO cartDTO);
}

