package br.com.maximus.maximusappbackend.Model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class ConfiguracaoSistema {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idConfiguracaoSistema;

    @NotNull(message = "Os dias limite de inatividade não podem ficar em branco.")
    private Integer diasLimiteInatividade;

    @NotNull(message = "Os dias de antecedência não podem ficar em branco.")
    private Integer diasAntecedenciaVencimento;

    @ManyToOne
    @JoinColumn(name = "ADMINISTRADOR_idAdministrador")
    private Administrador administrador;
}
