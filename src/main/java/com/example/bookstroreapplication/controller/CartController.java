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

@RestController
@RequestMapping("/cart")
public class CartController {

    @Autowired
    ICartService cartService;

    @PostMapping("/create")
    public ResponseEntity<ResponseDTO> insertItem(@Valid @RequestBody CartDTO cartdto) {
        String newCart = cartService.insertItems(cartdto);
        ResponseDTO responseDTO = new ResponseDTO("User registered successfully !", newCart);
        return new ResponseEntity(responseDTO, HttpStatus.CREATED);
    }

    @GetMapping("/getAll/{token}")
    public ResponseEntity<ResponseDTO> getAllCartDetails(@PathVariable String token) {
        List<Cart> cart=cartService.getCartDetails(token);
        ResponseDTO responseDTO = new ResponseDTO("Get call Success",cart);
        return new ResponseEntity<ResponseDTO>(responseDTO, HttpStatus.OK);
    }

    @GetMapping("/getById/{token}")
    public ResponseEntity<ResponseDTO> getCartDetailsById(@PathVariable String token){
        Cart specificCartDetail=cartService.getCartDetailsById(token);
        ResponseDTO responseDTO=new ResponseDTO("Cart details retrieved successfully",specificCartDetail);
        return new ResponseEntity(responseDTO,HttpStatus.ACCEPTED);
    }

    @DeleteMapping("/delete/{token}")
    public ResponseEntity<ResponseDTO> deleteCartById(@PathVariable String token) {
        cartService.deleteCartItemById(token);
        ResponseDTO responseDTO = new ResponseDTO("Cart delete successfully",token);
        return new ResponseEntity(responseDTO, HttpStatus.OK);
    }

    @PutMapping("/updateById/{token}")
    public ResponseEntity<ResponseDTO> updateCartById(@PathVariable String token,@Valid @RequestBody CartDTO cartDTO) {
        Cart updateRecord = cartService.updateRecordById(token,cartDTO);
        ResponseDTO dto = new ResponseDTO(" Cart Record updated successfully by Id",updateRecord);
        return new ResponseEntity(dto,HttpStatus.ACCEPTED);
    }
}
