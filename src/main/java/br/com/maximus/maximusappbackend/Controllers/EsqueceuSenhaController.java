package br.com.maximus.maximusappbackend.Controllers;

import br.com.maximus.maximusappbackend.Model.Usuario;
import br.com.maximus.maximusappbackend.Repositories.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import java.util.Optional;
import java.util.Random;

@RestController
@RequestMapping("/esqueci-senha")
public class EsqueceuSenhaController {
    @Autowired
    private JavaMailSender mailSender;

    @Autowired
    private UsuarioRepository usuarioRepository;

    //Gerar e enviar o token
    @PostMapping("/gerar")
    public ResponseEntity<Void> gerarToken(@RequestBody EmailRequest request) {
        Optional<Usuario> userOpt = usuarioRepository.findByEmail(request.getEmail());

        if (userOpt.isPresent()) {
            Usuario user = userOpt.get();

            // Gera um token aleatório de 6 números
            String token = String.format("%06d", new Random().nextInt(999999));

            user.setTokenRecuperacao(token);
            usuarioRepository.save(user);

            SimpleMailMessage email = new SimpleMailMessage();
            email.setTo(user.getEmail());
            email.setSubject("Maximus App - Recuperação de Senha");
            email.setText("Olá " + user.getNome() + ",\n\n"
                    + "Recebemos um pedido para redefinir sua senha.\n"
                    + "Seu código de recuperação é: " + token + "\n\n"
                    + "Se você não pediu isso, apenas ignore este e-mail.");
            mailSender.send(email);

            return ResponseEntity.ok().build();
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

    //Validar o Token
    @PostMapping("/validar")
    public ResponseEntity<Void> validarToken(@RequestBody ValidarRequest request) {
        Optional<Usuario> userOpt = usuarioRepository.findByEmail(request.getEmail());

        if (userOpt.isPresent()) {
            Usuario user = userOpt.get();
            // Confere se existe um token no banco e se eh igual ao que o app enviou
            if (user.getTokenRecuperacao() != null && user.getTokenRecuperacao().equals(request.getToken())) {
                return ResponseEntity.ok().build();
            }
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }

    //Trocar a Senha
    @PostMapping("/redefinir")
    public ResponseEntity<Void> redefinirSenha(@RequestBody RedefinirRequest request) {
        Optional<Usuario> userOpt = usuarioRepository.findByEmail(request.getEmail());

        if (userOpt.isPresent()) {
            Usuario user = userOpt.get();
            user.setSenha(request.getSenha()); //Atualiza a nova senha
            user.setTokenRecuperacao(null); //Limpa o token
            usuarioRepository.save(user);
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }

    //Classes auxiliares

    public static class EmailRequest {
        private String email;

        public String getEmail() { return email; }
        public void setEmail(String email) { this.email = email; }
    }

    public static class ValidarRequest {
        private String email;
        private String token;

        public String getEmail() { return email; }
        public void setEmail(String email) { this.email = email; }
        public String getToken() { return token; }
        public void setToken(String token) { this.token = token; }
    }

    public static class RedefinirRequest {
        private String email;
        private String senha;

        public String getEmail() { return email; }
        public void setEmail(String email) { this.email = email; }
        public String getSenha() { return senha; }
        public void setSenha(String senha) { this.senha = senha; }
    }
}