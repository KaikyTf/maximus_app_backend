package br.com.maximus.maximusappbackend.Model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Ficha {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idFicha;

    @NotBlank(message = "O nome da ficha não pode ficar em branco...")
    private String nome;

    private String categoria;

    private Boolean status;

    @ManyToOne
    @JoinColumn(name = "ADMINISTRADOR_idAdministrador")
    private Administrador administrador;

    @ElementCollection
    @CollectionTable(name = "ficha_exercicios", joinColumns = @JoinColumn(name = "ficha_id"))
    private List<ExercicioFicha> exercicios;

    // Estrutura embutida para exercicios
    @Embeddable
    @Getter
    @Setter
    public static class ExercicioFicha {
        @ManyToOne
        @JoinColumn(name = "EXERCICIO_idExercicio")
        private Exercicio exercicio;

        private Integer series;
        private Integer repeticoes;
    }
}