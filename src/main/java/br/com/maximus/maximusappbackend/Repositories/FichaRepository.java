package br.com.maximus.maximusappbackend.Repositories;

import br.com.maximus.maximusappbackend.Model.Ficha;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FichaRepository extends JpaRepository<Ficha, Long> {
}