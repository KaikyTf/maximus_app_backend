package br.com.maximus.maximusappbackend.Services;

import br.com.maximus.maximusappbackend.Model.Exercicio;
import br.com.maximus.maximusappbackend.Repositories.ExercicioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service // Faltou isso!
@RequiredArgsConstructor // Faltou isso!
public class ExercicioService {
    private final ExercicioRepository exercicioRepository;

    public Exercicio save(Exercicio e){
        return exercicioRepository.save(e);
    }

    public List<Exercicio> listAll(){
        return exercicioRepository.findAll();
    }

    public Exercicio getById(Long id){
        return exercicioRepository.findById(id).orElseThrow(() -> new RuntimeException("Exercício não encontrado"));
    }

    public void delete(Long id){
        Exercicio e = getById(id);
        exercicioRepository.delete(e);
    }
}