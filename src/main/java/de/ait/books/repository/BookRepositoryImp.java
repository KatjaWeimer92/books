package de.ait.books.repository;

import de.ait.books.entity.Book;
import org.springframework.jdbc.support.CustomSQLErrorCodesTranslation;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class BookRepositoryImp implements BookRepository {

    private List<Book> database = new ArrayList<>(List.of(
            new Book(1L, "War and Peace", "Leo Tolstoy", "Classic Literature", 1869),
            new Book(2L, "1984", "George Orwell", "Dystopian", 1949 ),
            new Book(3L, "Harry Potter and the Philosopher's Stone", "J.K. Rowling", "Fantasy, Children’s Literature", 1997),
            new Book(4L, "Murder on the Orient Express", "Agatha Christie", "Detective", 1934)
    ));

    @Override
    public List<Book> findAll() {
        return new ArrayList<>(database);
    }

    @Override
    public Book save(Book book) {
        if(book.getId()==null){
            return createBook(book);
        } else {
            return updateBook(book);
        }
    }

    private Book updateBook(Book book) {
        Optional<Book> bookFroDB = findById(book.getId());
        if (bookFroDB.isEmpty()) {
            return null;
        } else {
            Book b = bookFroDB.get();
            b.setTitle(book.getTitle());
            b.setAuthor(book.getAuthor());
            b.setYear(book.getYear());
            b.setGenre(book.getGenre());
        }
        return book;
    }

    private Book createBook(Book book) {
        Long newId = database.get(database.size() - 1).getId() +1;
        book.setId(newId);
        database.add(book);
        return book;
    }

    private Optional<Book> findById(Long id) {
        return database.stream().filter(b -> b.getId().equals(id))
                .findAny();
    }


}
