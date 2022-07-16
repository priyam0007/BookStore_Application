package com.example.bookstroreapplication.controller;


import com.example.bookstroreapplication.dto.OrderDTO;
import com.example.bookstroreapplication.dto.ResponseDTO;
import com.example.bookstroreapplication.service.IOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import com.example.bookstroreapplication.model.Order;
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
@RestController
@RequestMapping("/order")
/**
 * create a class name as OrderController
 */
public class OrderController {
    @Autowired
    private IOrderService orderService;

    @PostMapping("/insert")
    public ResponseEntity<ResponseDTO> insertOrder(@Valid @RequestBody OrderDTO orderdto) {
        String newOrder = orderService.insertOrder(orderdto);
        ResponseDTO dto = new ResponseDTO("Order placed successfully !", newOrder);
        return new ResponseEntity(dto, HttpStatus.CREATED);
    }

    @GetMapping("/getAllOrders/{token}")
    public ResponseEntity<ResponseDTO> getAllOrderRecords(@PathVariable String token) {
        List<Order> newOrder = orderService.getAllOrderRecords(token);
        ResponseDTO dto = new ResponseDTO("All records retrieved successfully !", newOrder);
        return new ResponseEntity(dto, HttpStatus.OK);
    }

    @GetMapping("/getById/{token}")
    public ResponseEntity<ResponseDTO> getBookRecord(@PathVariable String token) {
        List<Order> newOrder = orderService.getOrderRecord(token);
        ResponseDTO dto = new ResponseDTO("Record retrieved successfully !", newOrder);
        return new ResponseEntity(dto, HttpStatus.OK);
    }

    @PutMapping("/cancelOrder/{token}/{userId}")
    public ResponseEntity<ResponseDTO> getCancelOrder(@PathVariable String token, @PathVariable int userId){
        Order deletedOrder = orderService.cancelOrder(token,userId);
        ResponseDTO dto = new ResponseDTO("Cancel order successfully !",deletedOrder);
        return new ResponseEntity(dto,HttpStatus.OK);
    }
}

