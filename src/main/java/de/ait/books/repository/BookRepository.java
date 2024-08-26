package de.ait.books.repository;

import de.ait.books.entity.Book;

import java.util.List;

public interface BookRepository {

    List<Book> findAll();
    Book save(Book book);

}
