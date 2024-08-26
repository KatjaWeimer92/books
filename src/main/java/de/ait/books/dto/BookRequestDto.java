package de.ait.books.dto;

import de.ait.books.entity.Book;

public class BookRequestDto {

    private String title;
    private String author;
    private String genre;
    private Integer year;

    public BookRequestDto() {

    }

    public BookRequestDto(String title, String author, String genre, Integer year) {
        this.title = title;
        this.author = author;
        this.genre = genre;
        this.year = year;
    }

    public String getTitle() {
        return title;
    }


    public String getAuthor() {
        return author;
    }

    public String getGenre() {
        return genre;
    }



    public Integer getYear() {
        return year;
    }


    public static Book toEntity(BookRequestDto dto) {
        return new Book(null, dto.getTitle(), dto.getAuthor(), dto.getGenre(), dto.getYear());
    }
}
