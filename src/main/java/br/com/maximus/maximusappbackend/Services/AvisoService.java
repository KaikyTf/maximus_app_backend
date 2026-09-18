package br.com.maximus.maximusappbackend.Services;

import br.com.maximus.maximusappbackend.Model.Aviso;
import br.com.maximus.maximusappbackend.Repositories.AvisoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AvisoService {
    private final AvisoRepository avisoRepository;

    public Aviso save(Aviso a) { return avisoRepository.save(a); }

    public List<Aviso> listAll() { return avisoRepository.findAll(); }

    public Aviso getById(Long id) { return avisoRepository.findById(id).orElseThrow(); }

    public void delete(Long id) { avisoRepository.delete(getById(id)); }

}
