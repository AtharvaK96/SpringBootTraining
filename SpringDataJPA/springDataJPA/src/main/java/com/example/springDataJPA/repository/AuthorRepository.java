package com.example.springDataJPA.repository;

import com.example.springDataJPA.models.Author;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthorRepository extends JpaRepository<Author,Integer> {

}
