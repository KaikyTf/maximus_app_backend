package br.com.maximus.maximusappbackend.Controllers;

import br.com.maximus.maximusappbackend.Model.Exercicio;
import br.com.maximus.maximusappbackend.Services.ExercicioService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/exercicios")
@RequiredArgsConstructor
public class ExercicioController {
    private final ExercicioService exercicioService;

    @PostMapping
    public ResponseEntity<Exercicio> cadastrar(@Valid @RequestBody Exercicio exercicio){
        Exercicio e = exercicioService.save(exercicio);
        return ResponseEntity.status(HttpStatus.CREATED).body(e); //retorna 201
    }

    @GetMapping
    public ResponseEntity<List<Exercicio>> listar(){
        return ResponseEntity.ok(exercicioService.listAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Exercicio> buscarId(@PathVariable Long id){
        return ResponseEntity.ok(exercicioService.getById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Exercicio> atualizar(@PathVariable Long id, @Valid @RequestBody Exercicio exercicio){
        exercicio.setIdExercicio(id);
        Exercicio novo = exercicioService.save(exercicio);
        return ResponseEntity.ok(novo);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id){
        exercicioService.delete(id);
        return ResponseEntity.noContent().build(); //retorna 204
    }
}