package com.example.demo.repository;

import com.example.demo.entity.Professor;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ProfessorRepository extends JpaRepository<Professor, Integer> {


    Optional<Professor> findByNome(String nome);
}
