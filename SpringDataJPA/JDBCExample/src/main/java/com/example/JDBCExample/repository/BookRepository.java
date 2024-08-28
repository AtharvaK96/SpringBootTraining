package com.example.JDBCExample.repository;

import com.example.JDBCExample.model.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.util.List;

@Repository
public class BookRepository {

    @Autowired
    JdbcTemplate jdbcTemplate;

    RowMapper rowMapper = (ResultSet rs, int rowNum) -> {
        Book book = new Book();
        book.setId(rs.getInt(1));
        book.setTitle(rs.getString(2));
        book.setPrice(rs.getInt(3));
        return book;
    };

    public List<Book> getAllBooks() {
        String sql = "SELECT * FROM Book";
        List<Book> bookList = jdbcTemplate.query(sql, rowMapper);
        return bookList;
    }

    public String addBook(Book book) {
        String sql = "INSERT INTO Book (id, title, price) VALUES (?, ?, ?)";
        int addedBooks = jdbcTemplate.update(sql, book.getId(), book.getTitle(), book.getPrice());
        if (addedBooks > 0) {
            return "Book added successfully";
        }
        return "Book not added";
    }

    public String updateBook(int id, Book book) {
        String sql = "UPDATE Book SET title = ?, price = ? WHERE id = ?";
        int updatedBooks = jdbcTemplate.update(sql, book.getTitle(), book.getPrice(), id);
        if (updatedBooks > 0) {
            return "Book updated successfully";
        }
        return "Book not updated";
    }

    public String deleteBook(int id) {
        String sql = "DELETE FROM Book WHERE id = ?";
        int deletedBooks = jdbcTemplate.update(sql, id);
        if (deletedBooks > 0) {
            return "Book deleted successfully";
        }
        return "Book not deleted";
    }
}
