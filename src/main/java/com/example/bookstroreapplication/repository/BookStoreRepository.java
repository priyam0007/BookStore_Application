package com.example.bookstroreapplication.repository;

import com.example.bookstroreapplication.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;


import java.util.List;

@Repository
public interface BookStoreRepository extends JpaRepository<Book, Integer> {
    @Query(value = "select * from book_registration where book_name like :bookName%", nativeQuery = true)
    List<Book> findByBookName(String bookName);


    @Query(value = "select * from book_registration order by price", nativeQuery = true)
    List<Book> getSortedListOfBooksInAsc();

    @Query(value = "select * from book_registration order by price desc", nativeQuery = true)
    List<Book> getSortedListOfBooksInDesc();

    @Query(value = "select * from book_registration where author_name like :authorName%", nativeQuery = true)
    List<Book> findByBookAuthorName(String authorName);

}
