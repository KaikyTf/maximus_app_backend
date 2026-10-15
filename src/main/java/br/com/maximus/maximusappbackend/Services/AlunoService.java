package br.com.maximus.maximusappbackend.Services;

import br.com.maximus.maximusappbackend.Model.Aluno;
import br.com.maximus.maximusappbackend.Model.Ficha;
import br.com.maximus.maximusappbackend.Repositories.AlunoRepository;
import br.com.maximus.maximusappbackend.Repositories.FichaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AlunoService {
    private final AlunoRepository alunoRepository;
    private final FichaRepository fichaRepository;

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

    //FICHAS

    public void atribuirFicha(Long idAluno, Long idFicha) {
        Aluno aluno = alunoRepository.findById(idAluno).orElseThrow(() -> new RuntimeException("Aluno não encontrado"));
        Ficha ficha = fichaRepository.findById(idFicha).orElseThrow(() -> new RuntimeException("Ficha não encontrada"));

        if (!aluno.getFichas().contains(ficha)) {
            aluno.getFichas().add(ficha);
            alunoRepository.save(aluno);
        }
    }
    public List<Ficha> listarFichasDoAluno(Long idAluno) {
        Aluno aluno = alunoRepository.findById(idAluno).orElseThrow(() -> new RuntimeException("Aluno não encontrado"));

        return aluno.getFichas();
    }
}