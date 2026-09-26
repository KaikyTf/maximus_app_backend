package br.com.maximus.maximusappbackend.Services;

import br.com.maximus.maximusappbackend.Model.Aluno;
import br.com.maximus.maximusappbackend.Repositories.AlunoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AlunoService {
    private final AlunoRepository alunoRepository;

    public Aluno save(Aluno a){
        return alunoRepository.save(a);
    }

    public List<Aluno> listAll(){
        return alunoRepository.findAll();
    }

    public Aluno getById(Long id){
        return alunoRepository.findById(id).orElseThrow(() -> new RuntimeException("Aluno não encontrado"));
    }

    public void delete(Long id){
        Aluno a = getById(id);
        alunoRepository.delete(a);
    }
}