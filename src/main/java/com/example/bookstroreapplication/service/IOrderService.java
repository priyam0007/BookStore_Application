package com.example.bookstroreapplication.service;

import com.example.bookstroreapplication.dto.OrderDTO;
import com.example.bookstroreapplication.model.Order;

import java.util.List;

public interface IOrderService {

    String insertOrder(OrderDTO orderdto);
   List <Order> getOrderRecord(String token);
  List<Order> getAllOrderRecords(String token);


    Order cancelOrder(String token,int userId);
}

