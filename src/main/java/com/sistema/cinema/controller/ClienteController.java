package com.sistema.cinema.controller;

import com.sistema.cinema.domain.cinema.CinemaRepository;
import com.sistema.cinema.domain.cliente.*;
import com.sistema.cinema.domain.endereco.DadosAtualizaEndereco;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

@RestController
@RequestMapping("cliente")
public class ClienteController {

    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private CinemaRepository cinemaRepository;

    @PostMapping
    @Transactional
    public ResponseEntity cadastrar(@RequestBody @Valid DadosCadastroCliente dados, UriComponentsBuilder uriBuilder) {
        var cliente = new Cliente(dados);
        clienteRepository.save(cliente);

        var uri = uriBuilder.path("cliente/{id}").buildAndExpand(cliente.getId()).toUri();

        return ResponseEntity.created(uri).body(new DadosDetalhamentoCliente(cliente));
    }

    @GetMapping
    public List<Cliente> listar(@PageableDefault(size = 10, sort = {"nome"}) Pageable paginacao) {
        var page = clienteRepository.findAll();
        return page;
    }

    @GetMapping("/{id}")
    public Cliente selecionar(@PathVariable Long id) {
        var page = clienteRepository.findById(id);
        return page.get();
    }

    @PutMapping
    @Transactional
    public ResponseEntity atualizar(@RequestBody @Valid DadosAtualizaCliente dados) {
        var cliente = clienteRepository.getReferenceById(dados.id());
        cliente.atualizaDados(dados);

        return ResponseEntity.ok(new DadosDetalhamentoCliente(cliente));
    }
}
