package com.tadiewa.Pdfgenerator.repository;

import com.tadiewa.Pdfgenerator.entity.Generator;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GeneratorRepository extends JpaRepository<Generator ,Long> {
}
