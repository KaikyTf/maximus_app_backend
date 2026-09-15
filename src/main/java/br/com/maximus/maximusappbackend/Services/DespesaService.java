package br.com.maximus.maximusappbackend.Services;

import br.com.maximus.maximusappbackend.Model.Despesa;
import br.com.maximus.maximusappbackend.Repositories.DespesaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.YearMonth;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class DespesaService {
    private final DespesaRepository despesaRepository;

    public Despesa save(Despesa d) { return despesaRepository.save(d); }

    public List<Despesa> listAll() { return despesaRepository.findAll(); }

    public Despesa getById(Long id) { return despesaRepository.findById(id).orElseThrow(); }

    public void delete(Long id) { despesaRepository.delete(getById(id)); }

    public Despesa update(Long id, Despesa nova) {
        Despesa d = getById(id);
        d.setNome(nova.getNome());
        d.setDescricao(nova.getDescricao());
        d.setValor(nova.getValor());
        d.setCategoria(nova.getCategoria());
        d.setData(nova.getData());
        d.setRecorrente(nova.getRecorrente());
        d.setParcelado(nova.getParcelado());
        d.setParcelas(nova.getParcelas());

        return despesaRepository.save(d);
    }

    public List<Despesa> searchMonthYear(int mes, int ano) {
        //data alvo no objeto do Java
        YearMonth busca = YearMonth.of(ano, mes);

        //filtro para buscar as despesas
        return despesaRepository.findAll().stream().filter(d -> {
            //pega a data despesa, transforma em mes/ano e conta a diferença exata entre a criação da despesa e o mês pesquisado
            //comprou em janeiro pesquisou em abril diff sera 3
            long diff = ChronoUnit.MONTHS.between(YearMonth.from(d.getData()), busca);

            //tem que ter a diff >= 0
            //&&
            //REGRA 1 - se 0, foi registrada no mesmo mes que eu estou pesquisando
            //REGRA 2 - se ela for recorrente ela entra também
            //REGRA 3 - se ela for parcelada e a diff for menor que a quantidade de parcelas ela entra também
            return diff >= 0 && (diff == 0 || Boolean.TRUE.equals(d.getRecorrente()) || (Boolean.TRUE.equals(d.getParcelado()) && diff < d.getParcelas()));

        //devolve a lista
        }).collect(Collectors.toList());
    }
}
