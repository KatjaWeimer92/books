package de.ait.books.controller;

import de.ait.books.dto.BookRequestDto;
import de.ait.books.dto.BookResponseDto;
import de.ait.books.entity.Book;
import de.ait.books.server.BookServer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api")
public class BookController {

    private final BookServer server;


    @Autowired
    public BookController(BookServer server) {
        this.server = server;
    }

    @GetMapping("/books")
    public List<BookResponseDto> getBooks(@RequestParam(name ="t", required = false, defaultValue = "") String title,
                                          @RequestParam(name ="a", required = false, defaultValue = "") String author) {

        return server.getBooks(title, author);

    }


    @PostMapping("/books")
    public BookResponseDto createNewBook(@RequestBody BookRequestDto bookDto){
        return server.createNewBook(bookDto);
    }

    @GetMapping("/books/{id}")
    public BookResponseDto getBookById(@PathVariable(name="id") Long id) {

        return server.findById(id);
    }

    @PutMapping("books/{id}")
    public BookResponseDto updateBook(@PathVariable(name = "id") Long id, @RequestBody BookRequestDto bookRequestDto){
        return server.updateBook(id, bookRequestDto);
    }
}
