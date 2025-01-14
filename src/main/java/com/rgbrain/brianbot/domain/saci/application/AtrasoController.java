package com.rgbrain.brianbot.domain.saci.application;

import java.util.List;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rgbrain.brianbot.domain.saci.core.model.command.AtrasoComissionadoCommand;
import com.rgbrain.brianbot.domain.saci.core.model.dados.DadosConsorciadoAtrasos;
import com.rgbrain.brianbot.domain.saci.core.ports.incoming.ObterTodosAtrasos;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("/atrasos")
@AllArgsConstructor
public class AtrasoController {

    @Qualifier("ObterTodosAtrasos")
    private final ObterTodosAtrasos obterTodosAtrasos;

    @GetMapping("")
    public ResponseEntity<List<DadosConsorciadoAtrasos>> handle(@RequestBody @Valid AtrasoComissionadoCommand command) {
        var todosAtrasos = obterTodosAtrasos.handle(command);
        return ResponseEntity.ok(todosAtrasos);
    }
}
