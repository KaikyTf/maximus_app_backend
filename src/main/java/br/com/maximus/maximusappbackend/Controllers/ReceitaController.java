package br.com.maximus.maximusappbackend.Controllers;

import br.com.maximus.maximusappbackend.Model.Despesa;
import br.com.maximus.maximusappbackend.Model.Receita;
import br.com.maximus.maximusappbackend.Services.ReceitaService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/receitas")
@RequiredArgsConstructor
public class ReceitaController {
    private final ReceitaService receitaService;

    @PostMapping
    public ResponseEntity<Receita> criar(@Valid @RequestBody Receita receita) {
        return ResponseEntity.status(HttpStatus.CREATED).body(receitaService.save(receita));
    }

    @GetMapping
    public ResponseEntity<List<Receita>> listarTodas() {
        return ResponseEntity.ok(receitaService.listAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Receita> buscarPorId(@PathVariable Long id) {
        return ResponseEntity.ok(receitaService.getById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Receita> atualizar(@PathVariable Long id, @Valid @RequestBody Receita despesa) {
        return ResponseEntity.ok(receitaService.update(id, despesa));
    }

    @GetMapping("/filtrar")
    public ResponseEntity<List<Receita>> filterByMonthYear(@RequestParam int mes, @RequestParam int ano) {
        return ResponseEntity.ok(receitaService.searchMonthYear(mes, ano));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        receitaService.delete(id);
        return ResponseEntity.noContent().build();
    }
}