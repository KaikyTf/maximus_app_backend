package br.com.maximus.maximusappbackend.Controllers;

import br.com.maximus.maximusappbackend.Model.Plano;
import br.com.maximus.maximusappbackend.Services.PlanoService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/planos")
@RequiredArgsConstructor //ja injeta o plano service
public class PlanoController {
    private final PlanoService planoService;

    @PostMapping
    public ResponseEntity<Plano> cadastrar(@Valid @RequestBody Plano plano){
        Plano p = planoService.save(plano);
        return ResponseEntity.status(HttpStatus.CREATED).body(p); //retorna 201
    }

    @GetMapping
    public ResponseEntity<List<Plano>> listar(){
        return ResponseEntity.ok(planoService.listAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Plano> buscarId(@PathVariable Long id){
        return ResponseEntity.ok(planoService.getById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Plano> atualizar(@PathVariable Long id, @Valid @RequestBody Plano plano){
        plano.setIdPlano(id);
        Plano novo = planoService.save(plano);
        return ResponseEntity.ok(novo);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id){
        planoService.delete(id);
        return ResponseEntity.noContent().build(); //retorna 204
    }
}
