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
    public List<Cart> getCartDetails() {
        List<Cart> listOfCartdata = bookStoreCartRepository.findAll();
        log.info("ALL cart records retrieved successfully");
        return listOfCartdata;
    }


    @Override
    public Cart getCartDetailsById(int id) {
        Optional<Cart> CartData = bookStoreCartRepository.findById(id);
        if (CartData.isPresent()) {
            return CartData.get();
        } else {
            throw new BookStoreException(" Didn't find any record for this particular cartId");
        }
    }
    @Override
    public Optional<Cart>  deleteCartItemById(int id) {
        Optional<Cart> delete = bookStoreCartRepository.findById(id);
        if (delete.isPresent()) {
            bookStoreCartRepository.deleteById(id);
            return delete;
        } else {
            throw new BookStoreException(" Did not get any cart for specific cart id ");
        }

    }

    @Override
    public Cart updateRecordById(int id, CartDTO cartDTO) {
        Optional<Cart> cart = bookStoreCartRepository.findById(id);
        Optional<Book>  book = bookStoreRepository.findById(cartDTO.getBookId());
        Optional<UserRegistration> user = userRepository.findById(cartDTO.getUserId());
        if(cart.isPresent()) {
            if(book.isPresent() && user.isPresent()) {
                Cart cartData = new Cart(id, cartDTO.getQuantity(),book.get(), user.get(), cartDTO.getTotal());
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
    @Override
    public Cart updateQuntity(Integer cartId, int quantity, int total) {
        System.out.println(cartId + " " + quantity + " " +total );
        Optional<Cart> cart=bookStoreCartRepository.findById(cartId);
        System.out.println(cart.get());
        if(cart.isPresent()){
            cart.get().setQuantity(quantity);
            cart.get().setTotal(total);
            bookStoreCartRepository.save(cart.get());
        }else {
            throw new BookStoreException("invalid id please input valid Id");
        }
        return cart.get();
    }

    @Override
    public Cart updateCartByid(int cartid, CartDTO cartDTO) {
        Optional<Cart> cart = bookStoreCartRepository.findById(cartid);
        Optional<Book> book = bookStoreRepository.findById(cartDTO.getBookId());
        Optional<UserRegistration> user = userRepository.findById(cartDTO.getUserId());
        if (cart.isPresent()) {
            throw new BookStoreException("Cart Record doesn't exists");
        } else {
            if (book.isPresent() && user.isPresent()) {
                Cart newCart = new Cart(cartDTO.quantity,book.get(), user.get());
                bookStoreCartRepository.save(newCart);
                return newCart;
            } else {
                throw new BookStoreException("Book or User doesn't exists");
            }
        }
    }
}


