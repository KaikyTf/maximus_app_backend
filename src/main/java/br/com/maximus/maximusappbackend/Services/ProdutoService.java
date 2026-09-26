package br.com.maximus.maximusappbackend.Services;

import br.com.maximus.maximusappbackend.Model.Produto;
import br.com.maximus.maximusappbackend.Repositories.ProdutoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProdutoService {
    private final ProdutoRepository produtoRepository;

    public Produto save(Produto p){
        return produtoRepository.save(p);
    }

    public List<Produto> listAll(){
        return produtoRepository.findAll();
    }

    public Produto getById(Long id){
        return produtoRepository.findById(id).orElseThrow(() -> new RuntimeException("Produto não encontrado"));
    }

    public void delete(Long id){
        Produto p = getById(id);
        produtoRepository.delete(p);
    }
}