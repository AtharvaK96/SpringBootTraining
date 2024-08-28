package com.example.assignment_three.repository;

import com.example.assignment_three.module.Book;
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
        Book book = new Book(rs.getInt(1), rs.getString(2), rs.getDouble(3));
        return book;
    };

    public List<Book> displayBooks() {
        String sql = "SELECT * FROM Book";
        List<Book> bookList = jdbcTemplate.query(sql, rowMapper);
        return bookList;
    }

    public String addBook(Book book) {
        String sql = "INSERT INTO Book VALUES (?,?,?)";
        int changes = jdbcTemplate.update(sql, book.getId(), book.getTitle(), book.getPrice());
        if (changes > 0) {
            return "Record added";
        }
        return "Record not added";
    }

    public String updateBook(int id, Book book) {
        String sql = "UPDATE Book SET title=?, price=? WHERE id=?";
        int changes = jdbcTemplate.update(sql, book.getTitle(), book.getPrice(), id);
        if (changes > 0) {
            return "Record updated";
        }
        return "Record not updated";
    }

    public String deleteBook(int id) {
        String sql = "DELETE FROM Book WHERE id=?";
        int changes = jdbcTemplate.update(sql, id);
        if (changes > 0) {
            return "Record deleted";
        }
        return "Record not deleted";
    }
}
