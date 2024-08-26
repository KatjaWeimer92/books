package de.ait.books.repository;

import de.ait.books.entity.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import javax.sql.DataSource;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;



@Repository
public class BookRepositoryJDBCImpl implements BookRepository{

    private final DataSource dataSource;
    public final JdbcTemplate jdbcTemplate;

    @Autowired
    public BookRepositoryJDBCImpl(DataSource dataSource, JdbcTemplate jdbcTemplate) {
        this.dataSource = dataSource;
        this.jdbcTemplate = jdbcTemplate;
    }
    private static final RowMapper<Book> BOOK_ROW_MAPPER = (row, rowNumber) ->{
        Long id = row.getLong("id");
        String title  = row.getString("title");
        String author = row.getString("author");
        String genre = row.getString("genre");
        Integer year = row.getInt("year");

        return new Book(id, title, author,genre, year);
    };

    @Override
    public Book save(Book book) {
       if (book.getId() == null) {
           return create(book);
       }else {
         return  update(book);
       }
    }
    @Override
    public List<Book> findAll() {
        String queryStr = "SELECT * FROM t_book";
        return jdbcTemplate.query(queryStr, BOOK_ROW_MAPPER);
    }
    private Book update(Book book) {
        String queryStr = "UPDATE t_book SET title = ?, author = ?, genre = ?, year = ? WHERE id = ?";
        int affectedRows  = jdbcTemplate.update(queryStr, book.getTitle(), book.getAuthor(), book.getGenre(), book.getYear());
        return affectedRows==1 ? book:null;

    }

    private Book create(Book book) {
        SimpleJdbcInsert jdbcInsert = new SimpleJdbcInsert(dataSource)
                .usingGeneratedKeyColumns("id")
                .withTableName("t_book");

        Map<String, Object> parametrs = new HashMap<>();
        parametrs.put("title", book.getTitle());
        parametrs.put("author", book.getAuthor());
        parametrs.put("genre", book.getGenre());
        parametrs.put("year",book.getYear());

        Long generatedId = jdbcInsert.executeAndReturnKey(parametrs).longValue();
       book.setId(generatedId);
       return book;
    }


}
