package de.ait.books.dto;

import de.ait.books.entity.Book;

import java.util.List;
import java.util.stream.Collectors;

public class BookResponseDto {
    private Long id;
    private String title;
    private String author;
    private String genre;
    private Integer year;


    public BookResponseDto(Long id, String title, String author, String genre, Integer year) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.genre = genre;
        this.year = year;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public static BookResponseDto of(Book book) {
        return new BookResponseDto(book.getId(), book.getTitle(), book.getAuthor(), book.getGenre(), book.getYear());
    }

    public static List<BookResponseDto> of(List<Book> book) {
        return book.stream()
                .map(BookResponseDto::of)
                .collect(Collectors.toList());
    }

    public BookResponseDto(){

    }
}
