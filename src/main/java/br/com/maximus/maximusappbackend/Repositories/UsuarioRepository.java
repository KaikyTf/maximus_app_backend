package br.com.maximus.maximusappbackend.Repositories;

import br.com.maximus.maximusappbackend.Model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioRepository extends JpaRepository <Usuario, Long> {
}
