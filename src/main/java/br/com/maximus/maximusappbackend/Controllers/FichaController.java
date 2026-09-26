package br.com.maximus.maximusappbackend.Controllers;

import br.com.maximus.maximusappbackend.Model.Ficha;
import br.com.maximus.maximusappbackend.Services.FichaService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/fichas")
@RequiredArgsConstructor
public class FichaController {

    private final FichaService fichaService;

    @PostMapping
    public ResponseEntity<Ficha> cadastrar(@Valid @RequestBody Ficha ficha){
        Ficha f = fichaService.save(ficha);
        return ResponseEntity.status(HttpStatus.CREATED).body(f);
    }

    @GetMapping
    public ResponseEntity<List<Ficha>> listar(){
        return ResponseEntity.ok(fichaService.listAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Ficha> buscarId(@PathVariable Long id){
        return ResponseEntity.ok(fichaService.getById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Ficha> atualizar(@PathVariable Long id, @Valid @RequestBody Ficha ficha){
        ficha.setIdFicha(id);
        Ficha nova = fichaService.save(ficha);
        return ResponseEntity.ok(nova);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id){
        fichaService.delete(id);
        return ResponseEntity.noContent().build();
    }
}