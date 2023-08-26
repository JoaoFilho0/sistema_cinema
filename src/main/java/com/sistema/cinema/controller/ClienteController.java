package com.sistema.cinema.controller;

import com.sistema.cinema.domain.cliente.Cliente;
import com.sistema.cinema.domain.cliente.ClienteRepository;
import com.sistema.cinema.domain.cliente.DadosCadastroCliente;
import com.sistema.cinema.domain.cliente.DadosDetalhamentoCliente;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("cliente")
public class ClienteController {

    @Autowired
    private ClienteRepository repository;

    @PostMapping
    @Transactional
    public ResponseEntity cadastrar(@RequestBody @Valid DadosCadastroCliente dados, UriComponentsBuilder uriBuilder) {
        var cliente = new Cliente(dados);
        repository.save(cliente);

        var uri = uriBuilder.path("cliente/{id}").buildAndExpand(cliente.getCli_id()).toUri();

        return ResponseEntity.created(uri).body(new DadosDetalhamentoCliente(cliente));
    }
}
