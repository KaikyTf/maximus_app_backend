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
@PrimaryKeyJoinColumn(name = "USUARIO_idUsuario")
public class Aluno extends Usuario {

    private Double desconto;

    @ManyToOne
    @JoinColumn(name = "PLANO_idPlano")
    private Plano plano;

    @ManyToOne
    @JoinColumn(name = "ADMINISTRADOR_idAdministrador")
    private Administrador administrador;
}