package br.com.maximus.maximusappbackend.Model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Agendamento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idAgendamento;

    private String diaSemana; // Ex: "Segunda-feira"
    private String horario;   // Ex: "08h00", "08h30"

    @ManyToOne
    @JoinColumn(name = "ALUNO_idUsuario")
    private Aluno aluno;
}