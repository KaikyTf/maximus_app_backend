package br.com.maximus.maximusappbackend.Repositories;

import br.com.maximus.maximusappbackend.Model.Aluno;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AlunoRepository extends JpaRepository <Aluno, Long> {
}
