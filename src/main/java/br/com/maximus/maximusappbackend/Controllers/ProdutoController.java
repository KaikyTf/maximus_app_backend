package br.com.maximus.maximusappbackend.Controllers;

import br.com.maximus.maximusappbackend.Model.Produto;
import br.com.maximus.maximusappbackend.Services.ProdutoService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/produtos")
@RequiredArgsConstructor
public class ProdutoController {
    private final ProdutoService produtoService;

    @PostMapping
    public ResponseEntity<Produto> cadastrar(@Valid @RequestBody Produto produto){
        Produto p = produtoService.save(produto);
        return ResponseEntity.status(HttpStatus.CREATED).body(p);
    }

    @GetMapping
    public ResponseEntity<List<Produto>> listar(){
        return ResponseEntity.ok(produtoService.listAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Produto> buscarId(@PathVariable Long id){
        return ResponseEntity.ok(produtoService.getById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Produto> atualizar(@PathVariable Long id, @Valid @RequestBody Produto produto){
        produto.setIdProduto(id);

        Produto novo = produtoService.save(produto);
        return ResponseEntity.ok(novo);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id){
        produtoService.delete(id);
        return ResponseEntity.noContent().build();
    }
}