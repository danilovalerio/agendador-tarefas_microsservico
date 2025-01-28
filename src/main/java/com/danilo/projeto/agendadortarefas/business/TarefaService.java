package com.danilo.projeto.agendadortarefas.business;

import com.danilo.projeto.agendadortarefas.business.dto.TarefaDTO;
import com.danilo.projeto.agendadortarefas.infrastructure.repository.TarefasRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TarefaService {

    private TarefasRepository tarefasRepository;

    public TarefaDTO gravarTarefa(TarefaDTO tarefaDTO) {

        return tarefasRepository.save()
    }
}
