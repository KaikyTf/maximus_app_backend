package br.com.maximus.maximusappbackend.Controllers;

import br.com.maximus.maximusappbackend.Services.UsuarioService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/usuarios")
@RequiredArgsConstructor
public class UsuarioController {

    private final UsuarioService usuarioService;

    @GetMapping("/verificar")
    public ResponseEntity<Map<String, String>> verificarDuplicidade(
            @RequestParam String cpf,
            @RequestParam String usuario,
            @RequestParam String email,
            @RequestParam(required = false) Long idIgnorar) {

        Map<String, String> resultado = usuarioService.verificarDuplicidade(cpf, usuario, email, idIgnorar);
        return ResponseEntity.ok(resultado);
    }
}