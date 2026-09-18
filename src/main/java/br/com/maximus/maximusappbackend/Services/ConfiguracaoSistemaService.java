package br.com.maximus.maximusappbackend.Services;

import br.com.maximus.maximusappbackend.Model.ConfiguracaoSistema;
import br.com.maximus.maximusappbackend.Repositories.ConfiguracaoSistemaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ConfiguracaoSistemaService {

    @Autowired
    private ConfiguracaoSistemaRepository configuracaoSistemaRepository;

    public ConfiguracaoSistema getById(Long id) {
        return configuracaoSistemaRepository.findById(id).orElseThrow();
    }

    public ConfiguracaoSistema put(Long id, ConfiguracaoSistema nova) {
        return configuracaoSistemaRepository.findById(id).map(config -> {
            config.setDiasLimiteInatividade(nova.getDiasLimiteInatividade());
            config.setDiasAntecedenciaVencimento(nova.getDiasAntecedenciaVencimento());
            config.setAdministrador(nova.getAdministrador());
            return configuracaoSistemaRepository.save(config);
        }).orElse(null);
    }
}