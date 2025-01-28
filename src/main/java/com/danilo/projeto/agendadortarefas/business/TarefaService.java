package com.danilo.projeto.agendadortarefas.business;

import com.danilo.projeto.agendadortarefas.infrastructure.repository.TarefasRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TarefaService {

    private TarefasRepository tarefasRepository;
}
