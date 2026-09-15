package br.com.maximus.maximusappbackend.Controllers;

import br.com.maximus.maximusappbackend.Model.Despesa;
import br.com.maximus.maximusappbackend.Services.DespesaService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/despesas")
@RequiredArgsConstructor
public class DespesaController {
    private final DespesaService despesaService;

    @PostMapping
    public ResponseEntity<Despesa> criar(@Valid @RequestBody Despesa despesa) {
        return ResponseEntity.status(HttpStatus.CREATED).body(despesaService.save(despesa));
    }

    @GetMapping
    public ResponseEntity<List<Despesa>> listarTodas() {
        return ResponseEntity.ok(despesaService.listAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Despesa> buscarPorId(@PathVariable Long id) {
        return ResponseEntity.ok(despesaService.getById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Despesa> atualizar(@PathVariable Long id, @Valid @RequestBody Despesa despesa) {
        return ResponseEntity.ok(despesaService.update(id, despesa));
    }

    @GetMapping("/filtrar")
    public ResponseEntity<List<Despesa>> filterByMonthYear(@RequestParam int mes, @RequestParam int ano) {
        return ResponseEntity.ok(despesaService.searchMonthYear(mes, ano));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        despesaService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
