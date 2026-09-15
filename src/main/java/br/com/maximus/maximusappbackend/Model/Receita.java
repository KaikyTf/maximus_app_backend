package br.com.maximus.maximusappbackend.Model;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Receita {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idReceita;

    @NotBlank(message = "O nome da Receita não pode ficar em branco...")
    private String nome;

    @NotBlank(message = "A descrição da Receita não pode ficar em branco...")
    private String descricao;

    @NotNull(message = "O valor da Receita não pode ficar em branco...")
    private Double valor;

    @NotBlank(message = "A categoria da Receita não pode ficar em branco...")
    private String categoria;

    @NotNull(message = "A data da Receita não pode ficar em branco...")
    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate data;

    private Boolean recorrente, parcelado;

    private int parcelas;

    @ManyToOne
    @JoinColumn(name = "ADMINISTRADOR_idAdministrador")
    private Administrador administrador;
}
