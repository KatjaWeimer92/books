package de.ait.books.server;

import de.ait.books.dto.BookRequestDto;
import de.ait.books.dto.BookResponseDto;
import de.ait.books.entity.Book;
import de.ait.books.repository.BookRepository;
import de.ait.books.repository.BookRepositoryImp;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.function.Predicate;

@Service
public class BookServerImp implements BookServer {

    private final BookRepository bookRepository;
    private final ModelMapper mapper;

@Autowired
    public BookServerImp(@Qualifier("getRepository") BookRepository bookRepository, ModelMapper mapper) {
        this.bookRepository = bookRepository;
    this.mapper = mapper;
}


    public List<BookResponseDto> findAll() {
        return BookResponseDto.of(bookRepository.findAll());
    }

    @Override
    public List<BookResponseDto> getBooks(String title, String author) {
        Predicate<Book> predicateByTitle = (title.equals(""))? b->true : b -> b.getTitle().toLowerCase().contains(title.toLowerCase());
        Predicate<Book> predicateByAuthor = (author.equals(""))? b->true : b -> b.getAuthor().toLowerCase().contains(author.toLowerCase());

        Predicate<Book> allCondition = predicateByAuthor.and(predicateByTitle);

        List<Book> bookList =bookRepository.findAll()
                .stream()
                .filter(allCondition)
                .toList();

        return  BookResponseDto.of(bookList);
    }

    @Override
    public BookResponseDto createNewBook(BookRequestDto dto) {
        Book book = bookRepository.save(mapper.map(dto, Book.class));
        return mapper.map(book, BookResponseDto.class);
    }

    @Override
    public BookResponseDto findById(Long id) {
        return findAll()
                .stream()
                .filter(u->u.getId().equals(id))
                .findAny()
                .orElse(null);
    }



    @Override
    public BookResponseDto updateBook(Long id, BookRequestDto dto) {
        Book book = BookRequestDto.toEntity(dto);
        book.setId(id);
        return  BookResponseDto.of(bookRepository.save(book));
    }


    public List<BookResponseDto> findByTitle(String title) {
        return findAll()
                .stream()
                .filter(b->b.getTitle().equals(title))
                .toList();
    }
}
