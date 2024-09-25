package com.File.Example.file_uploading_example.repository;

import com.File.Example.file_uploading_example.model.Emploi;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmploiRepository extends JpaRepository<Emploi, Long> {
}
