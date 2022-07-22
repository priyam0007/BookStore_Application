package com.example.bookstroreapplication.service;

import com.example.bookstroreapplication.dto.OrderDTO;
import com.example.bookstroreapplication.model.Order;

import java.util.List;

public interface IOrderService {

    Order insertOrder(OrderDTO orderdto);

    List<Order> getOrderRecord(int id);

    List<Order> getAllOrderRecords(int id);

    Order cancelOrder(int id, int userId);
}

