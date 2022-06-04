package com.example.bookstroreapplication.service;

import com.example.bookstroreapplication.dto.BookDTO;
import com.example.bookstroreapplication.model.Book;

import java.util.List;

public interface IBookService {
    String createBook(BookDTO bookDTO);
    List<Book> getAllBookData(String token);
    Book getBookDataById(String token);
    List<Book> getBookByName(String bookName);
    List<Book> sortedListOfBooksInAscendingOrder();
    List<Book> sortedListOfBooksInDescendingOrder();
    String deleteRecordById(String token);
    Book updateRecordById(String token,BookDTO bookDTO);
    List<Book> getBookByAuthorName(String authorName);

    Book updataBooksByQuantity(String token, int quantity);
}

