package com.example.demo.controller;

import com.example.demo.entity.Professor;
import com.example.demo.repository.ProfessorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
public class ProfessorController {
    @Autowired
    private ProfessorRepository pfRepository;

    @PostMapping("/professor/add")
    public ResponseEntity<Boolean> adicionarProfessor(@RequestBody Professor p){
        pfRepository.save(p);
        return new ResponseEntity<Boolean>(true, HttpStatus.OK);

    }
    @GetMapping("/professor/ver/nome ")
    public ResponseEntity<Boolean> vernome(@RequestParam String nome){
        Optional<Professor> professor = pfRepository.findByNome(nome);

        if (professor.isPresent()) {
            return ResponseEntity.ok(true);  // Retorna 200 OK com 'true' se encontrado
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(false);  // Retorna 404 com 'false' se não encontrado
        }
    }


    @GetMapping("/professor/ver")
    public List<Professor> ver(){
        return pfRepository.findAll();
    }
    @PutMapping("/profesor/atualizar/{id}")
    public ResponseEntity<Boolean> atualizar(@PathVariable Integer id, @RequestBody Professor p){
        if(!pfRepository.existsById(id)){
            return ResponseEntity.notFound().build();
        }
        p.setId(id);
        pfRepository.save(p);
        return new ResponseEntity<Boolean>(true , HttpStatus.OK);
    }

    @DeleteMapping("/professor/delete/{id}")
    public ResponseEntity<Boolean> delete(@PathVariable Integer id, @RequestBody Professor p){
        if(!pfRepository.existsById(id)){
            return ResponseEntity.notFound().build();
        }
        p.setId(id);
        pfRepository.delete(p);
        return new ResponseEntity<Boolean>(true , HttpStatus.OK);
    }



}
