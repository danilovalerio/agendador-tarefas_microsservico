package com.danilo.projeto.agendadortarefas.controller;

import com.danilo.projeto.agendadortarefas.business.TarefaService;
import com.danilo.projeto.agendadortarefas.business.dto.TarefaDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/tarefas")
@RequiredArgsConstructor
public class TarefaController {

    private final TarefaService tarefaService;

    @PostMapping
    public ResponseEntity<TarefaDTO> gravarTarefa(
            @RequestBody TarefaDTO tarefaDTO,
            @RequestHeader("Authorization") String tokenRequestHeader
    ) {
        return ResponseEntity.ok(tarefaService.gravarTarefa(tokenRequestHeader, tarefaDTO));
    }
}
