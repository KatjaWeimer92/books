package de.ait.books.server;

import de.ait.books.dto.BookRequestDto;
import de.ait.books.dto.BookResponseDto;
import de.ait.books.entity.Book;

import java.util.List;

public interface BookServer {
    List<BookResponseDto> getBooks(String title, String author);
    //List<Book> findAll();
    BookResponseDto findById(Long id);
    BookResponseDto createNewBook(BookRequestDto book);
    //List<Book> findByTitle(String title);
    BookResponseDto updateBook(Long id, BookRequestDto book);
}
