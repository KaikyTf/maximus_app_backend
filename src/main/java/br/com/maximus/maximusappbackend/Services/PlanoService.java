package br.com.maximus.maximusappbackend.Services;

import br.com.maximus.maximusappbackend.Model.Plano;
import br.com.maximus.maximusappbackend.Repositories.PlanoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor //ja injeta o construtor do PlanoRepository
public class PlanoService {
    private final PlanoRepository planoRepository;

    public Plano save (Plano p){
        return planoRepository.save(p);
    }

    public List<Plano> listAll(){
        return planoRepository.findAll();
    }

    public Plano getById(Long id){
        return planoRepository.findById(id).orElseThrow(() -> new RuntimeException("Plano não encontrado"));
    }

    public void delete(Long id){
        Plano p = getById(id);
        planoRepository.delete(p);
    }
}
