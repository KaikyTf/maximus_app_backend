package br.com.maximus.maximusappbackend.Controllers;

import br.com.maximus.maximusappbackend.Model.Administrador;
import br.com.maximus.maximusappbackend.Model.Professor;
import br.com.maximus.maximusappbackend.Model.Usuario;
import br.com.maximus.maximusappbackend.Repositories.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/login")
public class LoginController {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @PostMapping
    public ResponseEntity<LoginResponse> login(@RequestBody LoginRequest request) {

        Optional<Usuario> userOpt = usuarioRepository.findByEmail(request.getEmail());

        if (userOpt.isPresent() && userOpt.get().getSenha().equals(request.getSenha())) {
            Usuario user = userOpt.get();

            //eh inativo
            if (!user.getStatus())
                return ResponseEntity.status(HttpStatus.FORBIDDEN).body(new LoginResponse(null, null, null, "Acesso bloqueado. Conta inativa!"));

            String tipo = "ALUNO";
            if (user instanceof Administrador) {
                tipo = "ADMIN";
            } else if (user instanceof Professor) {
                tipo = "PROFESSOR";
            }

            return ResponseEntity.ok(new LoginResponse(tipo, user.getIdUsuario(), user.getNome(), null));
        }

        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
    }

    //CLASSES AUXILIARES

    public static class LoginRequest {
        private String email;
        private String senha;

        public String getEmail() { return email; }
        public void setEmail(String email) { this.email = email; }
        public String getSenha() { return senha; }
        public void setSenha(String senha) { this.senha = senha; }
    }

    public static class LoginResponse {
        private String tipo;
        private Long id;
        private String nome;
        private String erro;

        public LoginResponse(String tipo, Long id, String nome, String erro) {
            this.tipo = tipo; this.id = id; this.nome = nome; this.erro = erro;
        }

        public String getTipo() { return tipo; }
        public Long getId() { return id; }
        public String getNome() { return nome; }
        public String getErro() { return erro; }
    }
}