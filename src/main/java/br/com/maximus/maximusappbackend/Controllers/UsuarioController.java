package br.com.maximus.maximusappbackend.Controllers;

import br.com.maximus.maximusappbackend.Model.Usuario;
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

    @GetMapping("/{id}")
    public ResponseEntity<Usuario> buscarPorId(@PathVariable Long id) {
        return ResponseEntity.ok(usuarioService.buscarPorId(id));
    }
    @PutMapping("/{id}")
    public ResponseEntity<Usuario> atualizar(@PathVariable Long id, @RequestBody Usuario usuario) {
        return ResponseEntity.ok(usuarioService.atualizar(id, usuario));
    }
}