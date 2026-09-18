package br.com.maximus.maximusappbackend.Repositories;

import br.com.maximus.maximusappbackend.Model.ConfiguracaoSistema;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ConfiguracaoSistemaRepository extends JpaRepository <ConfiguracaoSistema, Long> {
}
