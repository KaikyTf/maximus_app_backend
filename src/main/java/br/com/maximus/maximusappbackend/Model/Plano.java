package br.com.maximus.maximusappbackend.Model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Plano {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idPlano;

    @NotBlank(message = "O nome do plano não pode ficar em branco...")
    private String nome;

    @PositiveOrZero(message = "O valor do plano não pode ser negativo...")
    @NotNull(message = "O valor do plano não pode ser nulo...")
    private Double valor;

    @PositiveOrZero(message = "O valor do desconto não pode ser negativo...")
    @NotNull(message = "O valor do desconto não pode ser nulo...")
    private Double desconto;

    private int qtdDiasSemana;

    @PositiveOrZero(message = "A validade em dias não pode ser negativa...")
    @NotNull(message = "A validade em dias não pode ser nula...")
    private int validadeDias;

    private Boolean status;

    @ManyToOne
    @JoinColumn(name = "ADMINISTRADOR_idAdministrador")
    private Administrador administrador;

}
