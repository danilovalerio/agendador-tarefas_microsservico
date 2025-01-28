package com.danilo.projeto.agendadortarefas.business;

import com.danilo.projeto.agendadortarefas.business.dto.TarefaDTO;
import com.danilo.projeto.agendadortarefas.business.mapper.TarefaConverter;
import com.danilo.projeto.agendadortarefas.infrastructure.entity.TarefasEntity;
import com.danilo.projeto.agendadortarefas.infrastructure.enums.StatusNotificacaoEnum;
import com.danilo.projeto.agendadortarefas.infrastructure.repository.TarefasRepository;
import com.danilo.projeto.agendadortarefas.infrastructure.security.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TarefaService {

    private final TarefasRepository tarefasRepository;
    private final TarefaConverter tarefaConverter;
    private final JwtUtil jwtUtil;

    public TarefaDTO gravarTarefa(String token, TarefaDTO tarefaDTO) {
        String email = jwtUtil.extrairEmailToken(token.substring(7));

        tarefaDTO.setDataCriacao(LocalDateTime.now());
        tarefaDTO.setStatusNotificacaoEnum(StatusNotificacaoEnum.PENDENTE);
        tarefaDTO.setEmailUsuario(email);
        TarefasEntity entity = tarefaConverter.paraTarefaEntity(tarefaDTO);

        return tarefaConverter.paraTarefaDTO(tarefasRepository.save(entity));
    }

    public List<TarefaDTO> buscaTarefasAgendadasPorPeriodo(
            LocalDateTime dataInicial,
            LocalDateTime dataFinal
    ){
        return tarefaConverter.paraListaTarefaDTO(
                tarefasRepository.findByDataEventoBetween(dataInicial, dataFinal));
    }

    public List<TarefaDTO> buscaTarefasPorEmail(String token){
        String email = jwtUtil.extrairEmailToken(token.substring(7));

        return tarefaConverter.paraListaTarefaDTO(tarefasRepository.findByEmailUsuario(email));
    }
}
