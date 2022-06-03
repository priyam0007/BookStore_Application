package com.example.bookstroreapplication.service;

import com.example.bookstroreapplication.dto.BookDTO;
import com.example.bookstroreapplication.model.Book;


import java.util.List;

public interface IBookService {
    Book createBook(BookDTO bookDTO);

    List<Book> getAllBookData();

    Book getBookDataById(int bookId);

    String deleteRecordById(int BookId);

    List<com.example.bookstroreapplication.model.Book> getBookByAuthorName(String authorName);

    List<com.example.bookstroreapplication.model.Book> getBookByName(String name);

    List<com.example.bookstroreapplication.model.Book> sortedListOfBooksInAscendingOrder();

    List<com.example.bookstroreapplication.model.Book> sortedListOfBooksInDescendingOrder();

    Book updateRecordById(Integer bookId, BookDTO bookDTO);
}
