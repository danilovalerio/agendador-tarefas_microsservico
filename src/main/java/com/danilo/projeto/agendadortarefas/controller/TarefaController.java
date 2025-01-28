package com.danilo.projeto.agendadortarefas.controller;

import com.danilo.projeto.agendadortarefas.business.TarefaService;
import com.danilo.projeto.agendadortarefas.business.dto.TarefaDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

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

    @GetMapping("/eventos")
    public ResponseEntity<List<TarefaDTO>> buscaListaTarefasPorPeriodo(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime dataInicial,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime dataFinal)
    {
        return ResponseEntity.ok(
                tarefaService.buscaTarefasAgendadasPorPeriodo(dataInicial, dataFinal)
        );
    }

    @GetMapping
    public ResponseEntity<List<TarefaDTO>> buscaTarefasPorEmail(@RequestHeader("Authorization") String tokenRequestHeader) {

        return ResponseEntity.ok(tarefaService.buscaTarefasPorEmail(tokenRequestHeader));

    }
}
