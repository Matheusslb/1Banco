package com.example.demo.controller;

import com.example.demo.entity.Professor;
import com.example.demo.repository.ProfessorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class ProfessorController {
    @Autowired
    private ProfessorRepository pfRepository;

    @PostMapping("/professor/add")
    public ResponseEntity<Boolean> adicionarProfessor(@RequestBody Professor p){
        pfRepository.save(p);
        return new ResponseEntity<Boolean>(true, HttpStatus.OK);

    }
    @GetMapping("/professor/adc")
    public List<Professor> ver(){
        return pfRepository.findAll();
    }
    @PutMapping("/profesor/atualizar{id}")
    public ResponseEntity<Boolean> atualizar(@PathVariable Integer id, @RequestBody Professor p){
        if(!pfRepository.existsById(id)){
            return ResponseEntity.notFound().build();
        }
        p.setId(id);
        pfRepository.save(p);
        return new ResponseEntity<Boolean>(true , HttpStatus.OK);
    }

    @DeleteMapping("/professor/delete{id}")
    public ResponseEntity<Boolean> delete(@PathVariable Integer id, @RequestBody Professor p){
        if(!pfRepository.existsById(id)){
            return ResponseEntity.notFound().build();
        }
        p.setId(id);
        pfRepository.delete(p);
        return new ResponseEntity<Boolean>(true , HttpStatus.OK);
    }



}
