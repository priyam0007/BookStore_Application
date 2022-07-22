package com.example.bookstroreapplication.service;


import com.example.bookstroreapplication.dto.OrderDTO;
import com.example.bookstroreapplication.exception.BookStoreException;
import com.example.bookstroreapplication.model.Book;
import com.example.bookstroreapplication.model.UserRegistration;
import com.example.bookstroreapplication.repository.BookStoreRepository;
import com.example.bookstroreapplication.repository.OrderRepository;
import com.example.bookstroreapplication.repository.UserRepository;
import com.example.bookstroreapplication.model.Order;
import com.example.bookstroreapplication.util.EmailSenderService;
import com.example.bookstroreapplication.util.TokenUtility;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class OrderService implements IOrderService {

    @Autowired
    private OrderRepository orderRepo;

    @Autowired
    private BookStoreRepository bookRepo;

    @Autowired
    private UserRepository userRepo;
    @Autowired
    EmailSenderService mailService;
    @Autowired
    TokenUtility util;

    @Override
    public Order insertOrder(OrderDTO orderdto) {
//        Optional<Book> book = bookRepo.findById(orderdto.getBookId());
//        Optional<UserRegistration> user = userRepo.findById(orderdto.getUserId());
//        if (orderdto.getQuantity() <= book.get().getQuantity()) {
//            int quantity = book.get().getQuantity() - orderdto.getQuantity();
//            book.get().setQuantity(quantity);
//            bookRepo.save(book.get());
//            int totalPrice = book.get().getPrice() * orderdto.getQuantity();
//            Order newOrder = new Order(totalPrice, orderdto.getQuantity(), orderdto.getAddress(), book.get(), user.get(), orderdto.isCancel());
//            orderRepo.save(newOrder);
//            log.info("Order record inserted successfully");
//            String token = util.createToken(newOrder.getOrderID());
//            mailService.sendEmail(newOrder.getUser().getEmail(), "Test Email", "Registered SuccessFully, hii: "
//                    + newOrder.getOrderID() + "Please Click here to get data-> "
//                    + "http://localhost:8098/order/insert/" + token);
//            log.info("Order record inserted successfully");
//            return token;
//        } else {
//            throw new BookStoreException("Requested quantity is out of stock");
//        }
//}
        Optional<Book> book = bookRepo.findById(orderdto.getBookId());
        Optional<UserRegistration> userRegistration = userRepo.findById(orderdto.getUserId());
        if (book.isPresent() && userRegistration.isPresent()) {
            Order orderData = new Order(orderdto,book.get(),userRegistration.get());
            orderRepo.save(orderData);
            return orderData;
        } else {
            throw new BookStoreException("Book or User does not exists");
        }

    }
    @Override
    public List<Order> getOrderRecord(int id) {
        Optional<Order> order = orderRepo.findById(id);
        if (order.isPresent()) {
            List<Order> listOrder = orderRepo.findAll();
            return listOrder;

        } else {
            throw new BookStoreException("Order Record doesn't exists");
        }
    }


    @Override
    public List<Order> getAllOrderRecords(int id) {
        Optional<Order> orderData = orderRepo.findById(id);
        if (orderData.isPresent()) {
            List<Order> listOrderData = orderRepo.findAll();
            return listOrderData;
        } else {
            System.out.println("Exception ...Token not found!");
            return null;
        }

    }

    @Override
    public Order cancelOrder(int id, int userId) {
        Optional<Order> order = orderRepo.findById(id);
        Optional<UserRegistration> user = userRepo.findById(userId);
        if (order.isPresent() && user.isPresent()) {
            order.get().setCancel(true);
            orderRepo.save(order.get());
            return order.get();
        } else {
            throw new BookStoreException("Order Record doesn't exists");
        }
    }
}