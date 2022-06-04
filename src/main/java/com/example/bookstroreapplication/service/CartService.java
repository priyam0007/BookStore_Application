package com.example.bookstroreapplication.service;

import com.example.bookstroreapplication.dto.CartDTO;
import com.example.bookstroreapplication.exception.BookStoreException;
import com.example.bookstroreapplication.model.Book;
import com.example.bookstroreapplication.model.Cart;
import com.example.bookstroreapplication.model.UserRegistration;
import com.example.bookstroreapplication.repository.BookStoreCartRepository;
import com.example.bookstroreapplication.repository.BookStoreRepository;
import com.example.bookstroreapplication.repository.UserRepository;
import com.example.bookstroreapplication.util.EmailSenderService;
import com.example.bookstroreapplication.util.TokenUtility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import lombok.extern.slf4j.Slf4j;

import java.util.List;
import java.util.Optional;


@Service
@Slf4j
public class CartService implements ICartService {

    @Autowired
    BookStoreRepository bookStoreRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    BookStoreCartRepository bookStoreCartRepository;

    @Autowired
    EmailSenderService mailService;

    @Autowired
    TokenUtility util;


    @Override
    public String insertItems(CartDTO cartdto) {
        Optional<Book> book = bookStoreRepository.findById(cartdto.getBookId());
        Optional<UserRegistration> userRegistration = userRepository.findById(cartdto.getUserId());
        if (book.isPresent() && userRegistration.isPresent()) {
            Cart newCart = new Cart(cartdto.getQuantity(), book.get(), userRegistration.get());
            bookStoreCartRepository.save(newCart);
            String token = util.createToken(newCart.getCartId());
            return token;
        } else {
            throw new BookStoreException("Book or User does not exists");
        }
    }

    @Override
    public List<Cart> getCartDetails(String token) {
        int id = util.decodeToken(token);
        Optional<Cart> cartData = bookStoreCartRepository.findById(id);
        if (cartData.isPresent()) {
            List<Cart> listOfCartdata = bookStoreCartRepository.findAll();
            log.info("ALL cart records retrieved successfully");
            return listOfCartdata;
        } else {
            System.out.println("Exception ...Token not found!");
            return null;
        }
    }


    @Override
    public Cart getCartDetailsById(String token) {
        int id = util.decodeToken(token);
        Optional<Cart> CartData = bookStoreCartRepository.findById(id);
        if (CartData.isPresent()) {
            return CartData.get();
        } else {
            throw new BookStoreException(" Didn't find any record for this particular cartId");
        }
    }
    @Override
    public void deleteCartItemById(String token) {
        int id = util.decodeToken(token);
        Optional<Cart> delete = bookStoreCartRepository.findById(id);
        if (delete.isPresent()) {
            bookStoreCartRepository.deleteById(id);
        } else {
            throw new BookStoreException(" Did not get any cart for specific cart id ");
        }

    }

    @Override
    public Cart updateRecordById(String token, CartDTO cartDTO) {
        int id = util.decodeToken(token);
        Optional<Cart> cart = bookStoreCartRepository.findById(id);
        Optional<Book>  book = bookStoreRepository.findById(cartDTO.getBookId());
        Optional<UserRegistration> user = userRepository.findById(cartDTO.getUserId());
        if(cart.isPresent()) {
            if(book.isPresent() && user.isPresent()) {
                Cart cartData = new Cart(id, cartDTO.getQuantity(),book.get(), user.get());
                bookStoreCartRepository.save(cartData);
                log.info("Cart record updated successfully for id "+id);
                return cartData;
            }
            else {
                throw new BookStoreException("Book or User doesn't exists");
            }
        }
        else {
            throw new BookStoreException("Cart Record doesn't exists");
        }
    }
}
