package com.example.bookstroreapplication.service;


import com.example.bookstroreapplication.dto.BookDTO;
import com.example.bookstroreapplication.exception.BookStoreException;
import com.example.bookstroreapplication.model.Book;
import com.example.bookstroreapplication.repository.BookStoreRepository;
import com.example.bookstroreapplication.util.TokenUtility;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class BookService implements IBookService {

    @Autowired
    BookStoreRepository bookStoreRepository;
    @Autowired
    TokenUtility util;

    @Override
    public String createBook(BookDTO bookDTO) {
        Book bookData = new Book(bookDTO);
        bookStoreRepository.save(bookData);
        String token = util.createToken(bookData.getBookId());
        return token;
    }

    @Override
    public Book getBookDataById(String token) {
        int id = util.decodeToken(token);
        Optional<Book> book = bookStoreRepository.findById(id);
        if (book.isPresent()) {
            return book.get();
        } else {
            throw new BookStoreException("Exception with id" + id + "does not exist!!");
        }
    }

    @Override
    public List<Book> getAllBookData(String token) {
        int id = util.decodeToken(token);
        Optional<Book> bookData = bookStoreRepository.findById(id);
        if (bookData.isPresent()) {
            List<Book> listOfBooks = bookStoreRepository.findAll();
            return listOfBooks;
        } else {
            System.out.println("Exception ...Token not found!");
            return null;
        }
    }

    @Override
    public Book updataBooksByQuantity(String token, int quantity) {
        int id = util.decodeToken(token);
        Optional<Book> book = bookStoreRepository.findById(id);
        if (book.isPresent()) {
            Book bookData1 = new Book();
            bookData1.setQuantity(quantity);
            bookStoreRepository.save(bookData1);
            return bookData1;
        } else {
            throw new BookStoreException("Bookdata record does not found");
        }
    }

    @Override
    public Book updateRecordById(String token, BookDTO bookDTO) {
        int id = util.decodeToken(token);
        Optional<Book> bookData = bookStoreRepository.findById(id);
        if (bookData.isPresent()) {
            Book updateData = new Book(id, bookDTO);
            bookStoreRepository.save(updateData);
            return updateData;
        } else {
            throw new BookStoreException("Bookdata record does not found");
        }
    }

    @Override
    public String deleteRecordById(String token) {
        int id = util.decodeToken(token);
        Optional<Book> book = bookStoreRepository.findById(id);
        if (book.isPresent()) {
            bookStoreRepository.deleteById(id);
        } else {
            throw new BookStoreException("Book record does not found");
        }
        return token;
    }
    @Override
    public List<Book> getBookByName(String bookName) {
        List<Book> findBook = bookStoreRepository.findByBookName(bookName);
        if (findBook.isEmpty()) {
            throw new BookStoreException(" Details for provided Book is not found");
        }
        return findBook;
    }

    @Override
    public List<Book> sortedListOfBooksInAscendingOrder() {
        List<Book> getSortedList = bookStoreRepository.getSortedListOfBooksInAsc();
        return getSortedList;
    }

    @Override
    public List<Book> sortedListOfBooksInDescendingOrder() {
        List<Book> getSortedListInDesc = bookStoreRepository.getSortedListOfBooksInDesc();
        return getSortedListInDesc;
    }

    @Override
    public List<Book> getBookByAuthorName(String authorName) {
        List<Book> findBook = bookStoreRepository.findByBookAuthorName(authorName);
        if (findBook.isEmpty()) {
            throw new BookStoreException(" Details for provided Book is not found");
        }
        return findBook;
    }
}
