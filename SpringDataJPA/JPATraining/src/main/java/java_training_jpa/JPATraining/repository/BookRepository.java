package java_training_jpa.JPATraining.repository;


import java_training_jpa.JPATraining.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BookRepository extends JpaRepository<Book,Integer> {
    //    Book findByName
    Book findByTitle(String title);
    List<Book> findByPriceLessThan(Integer price);
    List<Book> findByAuthorId(Integer id);


}