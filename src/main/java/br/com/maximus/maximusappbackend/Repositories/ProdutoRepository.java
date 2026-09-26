package br.com.maximus.maximusappbackend.Repositories;

import br.com.maximus.maximusappbackend.Model.Produto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProdutoRepository extends JpaRepository<Produto, Long> {
}