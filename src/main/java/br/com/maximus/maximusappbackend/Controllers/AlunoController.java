package br.com.maximus.maximusappbackend.Controllers;

import br.com.maximus.maximusappbackend.Model.Aluno;
import br.com.maximus.maximusappbackend.Model.Ficha;
import br.com.maximus.maximusappbackend.Repositories.UsuarioRepository;
import br.com.maximus.maximusappbackend.Services.AlunoService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/alunos")
@RequiredArgsConstructor
public class AlunoController {
    private final AlunoService alunoService;

    @PostMapping
    public ResponseEntity<Aluno> cadastrar(@Valid @RequestBody Aluno aluno){
        Aluno a = alunoService.save(aluno);
        return ResponseEntity.status(HttpStatus.CREATED).body(a);
    }

    @GetMapping
    public ResponseEntity<List<Aluno>> listar(){
        return ResponseEntity.ok(alunoService.listAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Aluno> buscarId(@PathVariable Long id){
        return ResponseEntity.ok(alunoService.getById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Aluno> atualizar(@PathVariable Long id, @Valid @RequestBody Aluno aluno){
        aluno.setIdUsuario(id);

        // Verifica se a senha veio vazia do front
        // Se sim, mantem
        if (aluno.getSenha() == null || aluno.getSenha().isEmpty()) {
            Aluno antigo = alunoService.getById(id);
            aluno.setSenha(antigo.getSenha());
        }

        Aluno novo = alunoService.save(aluno);
        return ResponseEntity.ok(novo);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id){
        alunoService.delete(id);
        return ResponseEntity.noContent().build(); //retorna 204
    }

    //FICHAS

    @GetMapping("/{id}/fichas")
    public ResponseEntity<List<Ficha>> listarFichasDoAluno(@PathVariable Long id) {
        return ResponseEntity.ok(alunoService.listarFichasDoAluno(id));
    }

    @PostMapping("/{idAluno}/fichas/{idFicha}")
    public ResponseEntity<Void> atribuirFicha(@PathVariable Long idAluno, @PathVariable Long idFicha) {
        alunoService.atribuirFicha(idAluno, idFicha);
        return ResponseEntity.ok().build();
    }
}