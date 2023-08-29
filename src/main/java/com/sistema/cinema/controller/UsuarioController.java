package com.sistema.cinema.controller;

import com.sistema.cinema.domain.cinema.CinemaRepository;
import com.sistema.cinema.domain.sessao.DadosAtualizaSessao;
import com.sistema.cinema.domain.sessao.Sessao;
import com.sistema.cinema.domain.sessao.SessaoRepository;
import com.sistema.cinema.domain.usuario.*;
import com.sistema.cinema.domain.usuario_sessao.DadosCadastroUSuarioSessao;
import com.sistema.cinema.domain.usuario_sessao.UsuarioSessao;
import com.sistema.cinema.domain.usuario_sessao.UsuarioSessaoRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

@RestController
@RequestMapping("usuario")
public class UsuarioController {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private CinemaRepository cinemaRepository;

    @Autowired
    private SessaoRepository sessaoRepository;

    @Autowired
    private UsuarioSessaoRepository usuarioSessaoRepository;

    @PostMapping
    @Transactional
    public ResponseEntity cadastrar(@RequestBody @Valid DadosCadastroUsuario dados, UriComponentsBuilder uriBuilder) {
        var usuario = new Usuario(dados);
        usuarioRepository.save(usuario);

        var uri = uriBuilder.path("usuario/{id}").buildAndExpand(usuario.getId()).toUri();

        return ResponseEntity.created(uri).body(new DadosDetalhamentoUsuario(usuario));
    }

    @PostMapping("/sessao")
    @Transactional
    public void cadastrarUsuarioSessao(@RequestBody @Valid DadosCadastroUSuarioSessao dados, UriComponentsBuilder uriBuilder) {
        var usuarioSessao = new UsuarioSessao(dados);
        var sessao = sessaoRepository.getReferenceById(dados.sessao().id());

        usuarioSessao.setSessao(sessao);

        usuarioSessaoRepository.save(usuarioSessao);

        sessao.setIngressos(sessao.getIngressos() - 1);
        sessao.atualizaDados(new DadosAtualizaSessao(sessao.getId(), sessao.getIngressos(), sessao.getPreco()));

    }

    @GetMapping
    public List<Usuario> listar(@PageableDefault(size = 10, sort = {"nome"}) Pageable paginacao) {
        return usuarioRepository.findAll();
    }

//    @GetMapping("/sessao")
//    public List<Usuario> listarUsuarioSessao(@PageableDefault(size = 10, sort = {"nome"}) Pageable paginacao) {
//        return usuarioRepository.findAll();
//    }

    @GetMapping("/{id}")
    public Usuario selecionar(@PathVariable Long id) {
        var page = usuarioRepository.findById(id);
        return page.get();
    }

    @PutMapping
    @Transactional
    public ResponseEntity atualizar(@RequestBody @Valid DadosAtualizaUsuario dados) {
        var usuario = usuarioRepository.getReferenceById(dados.id());
        usuario.atualizaDados(dados);

        return ResponseEntity.ok(new DadosDetalhamentoUsuario(usuario));
    }
}
