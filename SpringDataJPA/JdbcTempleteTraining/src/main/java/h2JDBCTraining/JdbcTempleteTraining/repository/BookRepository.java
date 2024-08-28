package h2JDBCTraining.JdbcTempleteTraining.repository;

import h2JDBCTraining.JdbcTempleteTraining.model.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Repository
public class BookRepository {

    @Autowired
    JdbcTemplate jdbcTemplate;


    RowMapper<Book> rowMapper=(rs,rowNo)->{
        Book book = new Book();
        int id=rs.getInt(1);
        String title=rs.getString(2);
        int price=rs.getInt(3);
        book.setId(id);
        book.setTitle(title);
        book.setPrice(price);
        return book;
    };


    public List<Book> getAllBooks(){
        String sql="Select * from book";
        List<Book> bookList=jdbcTemplate.query(sql,rowMapper);
        return bookList;
    }

    public String addBook(Book book) {
        String sql = "insert into book(id,title,price) values(?,?,?)";
        int recordsInserted = jdbcTemplate.update(sql, book.getId(), book.getTitle(), book.getPrice());
        if (recordsInserted > 0) {
            return "Record added successfully";
        }
        return "Failed....";
    }
}
