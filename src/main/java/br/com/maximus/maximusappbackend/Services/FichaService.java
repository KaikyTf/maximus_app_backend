package br.com.maximus.maximusappbackend.Services;

import br.com.maximus.maximusappbackend.Model.Ficha;
import br.com.maximus.maximusappbackend.Repositories.FichaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class FichaService {
    private final FichaRepository fichaRepository;

    public Ficha save(Ficha f){
        return fichaRepository.save(f);
    }

    public List<Ficha> listAll(){
        return fichaRepository.findAll();
    }

    public Ficha getById(Long id){
        return fichaRepository.findById(id).orElseThrow(() -> new RuntimeException("Ficha não encontrada"));
    }

    public void delete(Long id){
        Ficha f = getById(id);
        fichaRepository.delete(f);
    }
}