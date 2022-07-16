package com.example.bookstroreapplication.controller;

import com.example.bookstroreapplication.dto.CartDTO;
import com.example.bookstroreapplication.dto.ResponseDTO;
import com.example.bookstroreapplication.model.Cart;
import com.example.bookstroreapplication.service.ICartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
/**
 *  1) @RestController :-
 *           @RestController is used for making restful web services with the help of the @RestController annotation.
 *           This annotation is used at the class level and allows the class to handle the requests made by the client
 * 2) @RequestMapping :-
 *           @RequestMapping used to map web requests onto specific handler classes and/or handler methods.
 *           RequestMapping can be applied to the controller class as well as methods
 *
 * - Created controller so that we can perform rest api calls
 */
@CrossOrigin(allowedHeaders = "*", origins = "*")
@RestController
@RequestMapping("/cart")
/**
 * create class name as CartController
 */
public class CartController {

    @Autowired
    ICartService cartService;

    @PostMapping("/create")
    public ResponseEntity<ResponseDTO> insertItem(@Valid @RequestBody CartDTO cartdto) {
        String newCart = cartService.insertItems(cartdto);
        ResponseDTO responseDTO = new ResponseDTO("User registered successfully !", newCart);
        return new ResponseEntity(responseDTO, HttpStatus.CREATED);
    }

    @GetMapping("/getAll")
    public ResponseEntity<ResponseDTO> getAllCartDetails() {
        List<Cart> cart=cartService.getCartDetails();
        ResponseDTO responseDTO = new ResponseDTO("Get call Success",cart);
        return new ResponseEntity<ResponseDTO>(responseDTO, HttpStatus.OK);
    }

    @GetMapping("/getById/{id}")
    public ResponseEntity<ResponseDTO> getCartDetailsById(int id){
        Cart specificCartDetail=cartService.getCartDetailsById(id);
        ResponseDTO responseDTO=new ResponseDTO("Cart details retrieved successfully",specificCartDetail);
        return new ResponseEntity(responseDTO,HttpStatus.ACCEPTED);
    }

    @DeleteMapping("/delete/{Id}")
    public ResponseEntity<ResponseDTO> deleteCartById(@PathVariable("Id") int id) {
        cartService.deleteCartItemById(id);
        ResponseDTO responseDTO = new ResponseDTO("Cart delete successfully",id);
        return new ResponseEntity(responseDTO, HttpStatus.OK);
    }

    @PutMapping("/updateById/{id}")
    public ResponseEntity<ResponseDTO> updateCartById(int id,@Valid @RequestBody CartDTO cartDTO) {
        Cart updateRecord = cartService.updateRecordById(id,cartDTO);
        ResponseDTO dto = new ResponseDTO(" Cart Record updated successfully by Id",updateRecord);
        return new ResponseEntity(dto,HttpStatus.ACCEPTED);
    }
    @PutMapping("/UpdateQunatity/{Id}/{Qunatity}/{total}")
    public ResponseEntity<ResponseDTO> updateQuntityData(@PathVariable Integer Id, @PathVariable int Qunatity, @PathVariable int total) {
        Cart updateQuntity = cartService.updateQuntity(Id, Qunatity, total);
        ResponseDTO dto = new ResponseDTO("Cart quntity update Successfully " ,updateQuntity);
        return  new ResponseEntity<>(dto,HttpStatus.ACCEPTED);
    }
}
