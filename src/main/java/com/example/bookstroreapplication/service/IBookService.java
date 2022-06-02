package com.example.bookstroreapplication.service;

import com.example.bookstroreapplication.dto.BookDTO;

import java.awt.print.Book;
import java.util.List;

public interface IBookService {
    Book createBook(BookDTO bookDTO);

    List<Book> getAllBookData();

    Book getBookDataById(Integer bookId);

    List<Book> getBookByAuthorName(String authorName);

    List<Book> getBookByName(String name);

    List<Book> sortedListOfBooksInAscendingOrder();

    List<Book> sortedListOfBooksInDescendingOrder();

    Object deleteRecordById(Integer bookId);

    Book updateRecordById(Integer bookId, BookDTO bookDTO);
}
