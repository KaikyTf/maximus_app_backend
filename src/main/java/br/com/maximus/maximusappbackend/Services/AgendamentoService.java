package br.com.maximus.maximusappbackend.Services;

import br.com.maximus.maximusappbackend.Model.Agendamento;
import br.com.maximus.maximusappbackend.Model.Aluno;
import br.com.maximus.maximusappbackend.Repositories.AgendamentoRepository;
import br.com.maximus.maximusappbackend.Repositories.AlunoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AgendamentoService {

    private final AgendamentoRepository agendamentoRepository;
    private final AlunoRepository alunoRepository;

    public List<Agendamento> listarPorDia(String diaSemana) {
        return agendamentoRepository.findByDiaSemana(diaSemana);
    }

    public Agendamento adicionarAlunoNoHorario(String diaSemana, String horario, Long idAluno) {
        if (agendamentoRepository.existsByDiaSemanaAndAluno_IdUsuario(diaSemana, idAluno)) {
            throw new RuntimeException("Este aluno já possui um horário neste dia!");
        }

        Aluno aluno = alunoRepository.findById(idAluno).orElseThrow(() -> new RuntimeException("Aluno não encontrado"));

        Agendamento novo = new Agendamento();
        novo.setDiaSemana(diaSemana);
        novo.setHorario(horario);
        novo.setAluno(aluno);

        return agendamentoRepository.save(novo);
    }

    public void removerAgendamento(Long idAgendamento) {
        agendamentoRepository.deleteById(idAgendamento);
    }
}