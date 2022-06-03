package com.example.bookstroreapplication.service;

import com.example.bookstroreapplication.dto.CartDTO;
import com.example.bookstroreapplication.dto.ResponseDTO;
import com.example.bookstroreapplication.exception.BookStoreException;
import com.example.bookstroreapplication.model.Book;
import com.example.bookstroreapplication.model.Cart;
import com.example.bookstroreapplication.model.UserRegistration;
import com.example.bookstroreapplication.repository.BookStoreCartRepository;
import com.example.bookstroreapplication.repository.BookStoreRepository;
import com.example.bookstroreapplication.repository.UserRepository;
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

    @Override
    public Cart insertItems(CartDTO cartdto) {
        Optional<Book> book = bookStoreRepository.findById(cartdto.getBookId());
        Optional<UserRegistration> userRegistration = userRepository.findById(cartdto.getUserId());
        if (book.isPresent() && userRegistration.isPresent()) {
            Cart newCart = new Cart(cartdto.getQuantity(), book.get(), userRegistration.get());
            bookStoreCartRepository.save(newCart);
            return newCart;
        } else {
            throw new BookStoreException("Book or User does not exists");
        }
    }

    @Override
    public ResponseDTO getCartDetails() {
        List<Cart> getCartDetails = bookStoreCartRepository.findAll();
        ResponseDTO dto = new ResponseDTO();
        if (getCartDetails.isEmpty()) {
            String message = " Not found Any Cart details ";
            dto.setMessage(message);
            dto.setData(0);
            return dto;
        } else {
            dto.setMessage("the list of cart items is sucussfully retrived");
            dto.setData(getCartDetails);
            return dto;
        }
    }

    @Override
    public Cart getCartDetailsById(Integer cartId) {
        Optional<Cart> getCartData = bookStoreCartRepository.findById(cartId);
        if (getCartData.isPresent()) {
            return getCartData.get();
        } else {
            throw new BookStoreException(" Didn't find any record for this particular cartId");
        }
    }

    public Cart getCartRecordByBookId(Integer bookId) {
        Optional<Cart> cart = bookStoreCartRepository.findByBookId(bookId);
        if (cart.isPresent()) {
            log.info("Cart record retrieved successfully for book id " + bookId);
            return cart.get();

        } else {
            return null;
            //throw new BookStoreException("Cart Record doesn't exists");
        }
    }

    @Override
    public Cart deleteCartItemById(Integer cartId) {
        Optional<Cart> deleteData = bookStoreCartRepository.findById(cartId);
        if (deleteData.isPresent()) {
            bookStoreCartRepository.deleteById(cartId);
            return deleteData.get();
        } else {
            throw new BookStoreException(" Did not get any cart for specific cart id ");
        }

    }


    @Override
    public Cart updateRecordById(Integer cartId, CartDTO cartDTO) {
        Optional<Cart> cart = bookStoreCartRepository.findById(cartId);
        Optional<Book> book = bookStoreRepository.findById(cartDTO.getBookId());
        Optional<UserRegistration> user = userRepository.findById(cartDTO.getUserId());
        if (cart.isPresent()) {
            if (book.isPresent() && user.isPresent()) {
                Cart newCart = new Cart(cartId, cartDTO.getQuantity(), book.get(), user.get());
                bookStoreCartRepository.save(newCart);
                log.info("Cart record updated successfully for id " + cartId);
                return newCart;
            } else {
                throw new BookStoreException("Book or User doesn't exists");
            }
        } else {
            throw new BookStoreException("Cart Record doesn't exists");
        }
    }
}
