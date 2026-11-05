package br.com.maximus.maximusappbackend.Controllers;

import br.com.maximus.maximusappbackend.Model.Agendamento;
import br.com.maximus.maximusappbackend.Services.AgendamentoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/agendamentos")
@RequiredArgsConstructor
public class AgendamentoController {

    private final AgendamentoService agendamentoService;

    @GetMapping
    public ResponseEntity<List<Agendamento>> listarPorDia(@RequestParam String diaSemana) {
        return ResponseEntity.ok(agendamentoService.listarPorDia(diaSemana));
    }

    @PostMapping
    public ResponseEntity<Agendamento> adicionar(@RequestBody Map<String, Object> payload) {
        String diaSemana = payload.get("diaSemana").toString();
        String horario = payload.get("horario").toString();
        Long idAluno = Long.parseLong(payload.get("idAluno").toString());

        Agendamento salvo = agendamentoService.adicionarAlunoNoHorario(diaSemana, horario, idAluno);
        return ResponseEntity.status(HttpStatus.CREATED).body(salvo);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> remover(@PathVariable Long id) {
        agendamentoService.removerAgendamento(id);
        return ResponseEntity.noContent().build();
    }
}