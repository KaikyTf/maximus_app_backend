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
@Inheritance(strategy = InheritanceType.JOINED)
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idUsuario;

    @NotBlank(message = "O nome não pode ficar em branco...")
    private String nome;

    @NotBlank(message = "O CPF não pode ficar em branco...")
    private String cpf;

    @NotBlank(message = "O usuário não pode ficar em branco...")
    private String usuario;

    private String email;
    private String senha;

    private String telefone;
    private String cep;
    private String uf;
    private String rua;
    private String bairro;
    private String cidade;

    private String dataNascimento;
    private String dataInicio;

    @Column(columnDefinition = "TEXT")
    private String foto;

    private Boolean status;
    private int tipo = 0;
    private String tokenRecuperacao;
}