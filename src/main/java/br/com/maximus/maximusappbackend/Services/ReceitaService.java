package br.com.maximus.maximusappbackend.Services;


import br.com.maximus.maximusappbackend.Model.Receita;
import br.com.maximus.maximusappbackend.Repositories.ReceitaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.YearMonth;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ReceitaService {
    private final ReceitaRepository receitaRepository;

    public Receita save(Receita r) { return receitaRepository.save(r); }

    public List<Receita> listAll() { return receitaRepository.findAll(); }

    public Receita getById(Long id) { return receitaRepository.findById(id).orElseThrow(); }

    public void delete(Long id) { receitaRepository.delete(getById(id)); }

    public Receita update(Long id, Receita nova) {
        Receita r = getById(id);
        r.setNome(nova.getNome());
        r.setDescricao(nova.getDescricao());
        r.setValor(nova.getValor());
        r.setCategoria(nova.getCategoria());
        r.setData(nova.getData());
        r.setRecorrente(nova.getRecorrente());
        r.setParcelado(nova.getParcelado());
        r.setParcelas(nova.getParcelas());

        return receitaRepository.save(r);
    }

    public List<Receita> searchMonthYear(int mes, int ano) {
        //data alvo no objeto do Java
        YearMonth busca = YearMonth.of(ano, mes);

        //filtro para buscar as despesas
        return receitaRepository.findAll().stream().filter(r -> {
            //pega a data despesa, transforma em mes/ano e conta a diferença exata entre a criação da despesa e o mês pesquisado
            //comprou em janeiro pesquisou em abril diff sera 3
            long diff = ChronoUnit.MONTHS.between(YearMonth.from(r.getData()), busca);

            //tem que ter a diff >= 0
            //&&
            //REGRA 1 - se 0, foi registrada no mesmo mes que eu estou pesquisando
            //REGRA 2 - se ela for recorrente ela entra também
            //REGRA 3 - se ela for parcelada e a diff for menor que a quantidade de parcelas ela entra também
            return diff >= 0 && (diff == 0 || Boolean.TRUE.equals(r.getRecorrente()) || (Boolean.TRUE.equals(r.getParcelado()) && diff < r.getParcelas()));

            //devolve a lista
        }).collect(Collectors.toList());
    }
}
