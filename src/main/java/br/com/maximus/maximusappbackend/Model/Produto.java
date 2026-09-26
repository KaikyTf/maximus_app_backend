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
public class Produto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idProduto;

    @NotBlank(message = "O nome do produto não pode ficar em branco...")
    private String nome;

    private String descricao;

    private Double valor;

    private Integer estoque;

    private String categoria;

    @Column(columnDefinition = "TEXT")
    private String foto;

    @ManyToOne
    @JoinColumn(name = "ADMINISTRADOR_idAdministrador")
    private Administrador administrador;
}