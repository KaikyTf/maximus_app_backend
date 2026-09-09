package br.com.maximus.maximusappbackend.Model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.util.Date;

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
    private String telefone, nome, rua, cidade, bairro, cep, uf, cpf, foto, email, senha, nomeDeUsuario;
    private Date dataNascimento, dataInicio;
    private int tipo = 0;

}
