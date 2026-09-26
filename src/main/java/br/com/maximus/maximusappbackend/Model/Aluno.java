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
public class Aluno {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idAluno;

    @NotBlank(message = "O nome do aluno não pode ficar em branco...")
    private String nome;

    @NotBlank(message = "O CPF não pode ficar em branco...")
    private String cpf;

    private String dtNascimento;
    private String telefone;
    private String cep;
    private String uf;
    private String rua;
    private String bairro;
    private String cidade;

    private Double desconto;

    private String email;

    @NotBlank(message = "O usuário não pode ficar em branco...")
    private String usuario;

    private String senha;
    private Boolean status;

    @Column(columnDefinition = "TEXT")
    private String foto;

    @ManyToOne
    @JoinColumn(name = "PLANO_idPlano")
    private Plano plano;

    @ManyToOne
    @JoinColumn(name = "ADMINISTRADOR_idAdministrador")
    private Administrador administrador;
}