package br.com.maximus.maximusappbackend.Repositories;

import br.com.maximus.maximusappbackend.Model.Administrador;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AdministradorRepository extends JpaRepository <Administrador, Long> {
}
