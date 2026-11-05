package br.com.maximus.maximusappbackend.Repositories;

import br.com.maximus.maximusappbackend.Model.Agendamento;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface AgendamentoRepository extends JpaRepository<Agendamento, Long> {
    List<Agendamento> findByDiaSemana(String diaSemana);
    boolean existsByDiaSemanaAndAluno_IdUsuario(String diaSemana, Long idUsuario);
}