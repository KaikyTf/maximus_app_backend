package br.com.maximus.maximusappbackend.Controllers;

import br.com.maximus.maximusappbackend.Model.Aviso;
import br.com.maximus.maximusappbackend.Services.AvisoService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/avisos")
@RequiredArgsConstructor
public class AvisoController {
    private final AvisoService avisoService;

    @PostMapping
    public ResponseEntity<Aviso> criar (@Valid @RequestBody Aviso aviso){
        return ResponseEntity.status(HttpStatus.CREATED).body(avisoService.save(aviso));
    }

    @GetMapping
    public ResponseEntity<List<Aviso>> listarTodos() { return ResponseEntity.ok(avisoService.listAll()); }

    @GetMapping("/{id}")
    public ResponseEntity<Aviso> buscarPorId(@Valid @RequestBody Long id) { return ResponseEntity.ok(avisoService.getById(id)); }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        avisoService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
