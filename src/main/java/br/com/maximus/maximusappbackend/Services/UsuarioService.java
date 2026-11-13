package br.com.maximus.maximusappbackend.Services;
import br.com.maximus.maximusappbackend.Model.Aluno;
import br.com.maximus.maximusappbackend.Model.Usuario;
import br.com.maximus.maximusappbackend.Repositories.AlunoRepository;
import br.com.maximus.maximusappbackend.Repositories.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.Collections;
import java.util.Map;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UsuarioService {
    private final UsuarioRepository usuarioRepository;

    public Map<String, String> verificarDuplicidade(String cpf, String usuario, String email, Long idIgnorar) {
        //Verifica email de todos os usuários
        Optional<Usuario> porEmail = usuarioRepository.findByEmail(email);
        if (porEmail.isPresent() && !porEmail.get().getIdUsuario().equals(idIgnorar)) {
            return Map.of("campo", "E-mail", "valor", email);
        }
        //CPF
        Optional<Usuario> porCpf = usuarioRepository.findByCpf(cpf);
        if (porCpf.isPresent() && !porCpf.get().getIdUsuario().equals(idIgnorar)) {
            return Map.of("campo", "CPF", "valor", cpf);
        }
        //Nome de usuario
        Optional<Usuario> porUser = usuarioRepository.findByUsuario(usuario);
        if (porUser.isPresent() && !porUser.get().getIdUsuario().equals(idIgnorar)) {
            return Map.of("campo", "Usuário", "valor", usuario);
        }
        return Collections.emptyMap(); // vazio = pode salvar
    }

    public Usuario buscarPorId(Long id) {
        return usuarioRepository.findById(id).orElseThrow(() -> new RuntimeException("Usuário não encontrado"));
    }
    public Usuario atualizar(Long id, Usuario dados) {
        Usuario usuario = buscarPorId(id);
        usuario.setNome(dados.getNome());
        usuario.setCpf(dados.getCpf());
        usuario.setDataNascimento(dados.getDataNascimento());
        usuario.setTelefone(dados.getTelefone());
        usuario.setCep(dados.getCep());
        usuario.setUf(dados.getUf());
        usuario.setRua(dados.getRua());
        usuario.setBairro(dados.getBairro());
        usuario.setCidade(dados.getCidade());
        usuario.setEmail(dados.getEmail());
        usuario.setUsuario(dados.getUsuario());

        if (dados.getSenha() != null && !dados.getSenha().trim().isEmpty())
            usuario.setSenha(dados.getSenha());
        if (dados.getFoto() != null)
            usuario.setFoto(dados.getFoto());
        return usuarioRepository.save(usuario);
    }
}
