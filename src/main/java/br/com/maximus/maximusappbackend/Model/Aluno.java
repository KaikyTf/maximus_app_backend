package br.com.maximus.maximusappbackend.Model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

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

    @ManyToMany
    @JoinTable(
            name = "aluno_fichas",
            joinColumns = @JoinColumn(name = "aluno_id"),
            inverseJoinColumns = @JoinColumn(name = "ficha_id")
    )
    private List<Ficha> fichas = new ArrayList<>();
}