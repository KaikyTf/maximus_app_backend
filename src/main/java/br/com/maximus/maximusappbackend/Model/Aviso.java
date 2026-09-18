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

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Aviso {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idAviso;

    @NotBlank(message = "O titulo do aviso não pode ser vazio")
    private String titulo;

    @NotBlank(message = "O texto do aviso não pode ser vazio")
    private String texto;

    @NotBlank(message = "O tipo do aviso não pode ser vazio")
    private String tipo;

    @NotNull(message = "A data de publicação do aviso não pode ficar em branco...")
    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate dtPublicacao;

    @NotNull(message = "A data de expiração do aviso não pode ficar em branco...")
    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate dtExpiracao;

    private boolean enviarAlunos, enviarProfessores;

    @ManyToOne
    @JoinColumn(name = "ADMINISTRADOR_idAdministrador")
    private Administrador administrador;

    @ManyToOne
    @JoinColumn(name = "ALUNO_idAluno")
    private Aluno aluno;
}
