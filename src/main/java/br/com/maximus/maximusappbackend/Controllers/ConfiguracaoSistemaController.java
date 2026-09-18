package br.com.maximus.maximusappbackend.Controllers;

import br.com.maximus.maximusappbackend.Model.ConfiguracaoSistema;
import br.com.maximus.maximusappbackend.Services.ConfiguracaoSistemaService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/configuracoes")
@RequiredArgsConstructor

public class ConfiguracaoSistemaController {
    private final ConfiguracaoSistemaService service;

    @GetMapping("/{id}")
    public ResponseEntity<ConfiguracaoSistema> buscarPorId(@PathVariable Long id) {
        ConfiguracaoSistema config = service.getById(id);
        if (config != null)
            return ResponseEntity.ok(config);
        return ResponseEntity.notFound().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<ConfiguracaoSistema> editar(@PathVariable Long id, @Valid @RequestBody ConfiguracaoSistema c) {
        ConfiguracaoSistema configEditada = service.put(id, c);
        if (configEditada != null)
            return ResponseEntity.ok(configEditada);
        return ResponseEntity.notFound().build();
    }
}