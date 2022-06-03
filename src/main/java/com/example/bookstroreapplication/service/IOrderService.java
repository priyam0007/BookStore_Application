package com.example.bookstroreapplication.service;

import com.example.bookstroreapplication.dto.OrderDTO;
import com.example.bookstroreapplication.model.Order;

import java.util.List;

public interface IOrderService {

    public com.example.bookstroreapplication.model.Order insertOrder(OrderDTO orderdto);

    public List<com.example.bookstroreapplication.model.Order> getAllOrderRecords();

    public com.example.bookstroreapplication.model.Order getOrderRecord(Integer id);

    public Order updateOrderRecord(Integer id, OrderDTO dto);

    public com.example.bookstroreapplication.model.Order deleteOrderRecord(Integer id);

    public com.example.bookstroreapplication.model.Order cancelOrder(Integer id);
}

