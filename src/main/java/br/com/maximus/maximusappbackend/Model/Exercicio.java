package br.com.maximus.maximusappbackend.Model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Exercicio {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idExercicio;

    @NotBlank(message = "O nome do exercício não pode ficar em branco...")
    private String nome;

    @NotBlank(message = "A descrição do exercício não pode ficar em branco...")
    private String descricao;

    @NotBlank(message = "O grupo muscular do exercício não pode ficar em branco...")
    private String grupo;

    private Boolean status;

    private String url;

    @ManyToOne
    @JoinColumn(name = "ADMINISTRADOR_idAdministrador")
    private Administrador administrador;
}
