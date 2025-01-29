package com.danilo.projeto.agendadortarefas.business;

import com.danilo.projeto.agendadortarefas.business.dto.TarefaDTO;
import com.danilo.projeto.agendadortarefas.business.mapper.TarefaConverter;
import com.danilo.projeto.agendadortarefas.business.mapper.TarefaUpdateConverter;
import com.danilo.projeto.agendadortarefas.infrastructure.entity.TarefasEntity;
import com.danilo.projeto.agendadortarefas.infrastructure.enums.StatusNotificacaoEnum;
import com.danilo.projeto.agendadortarefas.infrastructure.exceptions.ResourceNotFoundException;
import com.danilo.projeto.agendadortarefas.infrastructure.repository.TarefasRepository;
import com.danilo.projeto.agendadortarefas.infrastructure.security.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.*;

@Service
@RequiredArgsConstructor
public class TarefaService {

    private final TarefasRepository tarefasRepository;
    private final TarefaConverter tarefaConverter;
    private final JwtUtil jwtUtil;
    private final TarefaUpdateConverter tarefaUpdateConverter;

    public TarefaDTO gravarTarefa(String token, TarefaDTO tarefaDTO) {
        String email = jwtUtil.extrairEmailToken(token.substring(7));

        tarefaDTO.setDataCriacao(LocalDateTime.now());
//        ZonedDateTime zonedDateTime = LocalDateTime.now().atZone(ZoneId.of(TimeZone.getDefault().getID()));
//
//        tarefaDTO.setDataCriacao(zonedDateTime);
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
                tarefasRepository.findByDataEventoBetweenAndStatusNotificacaoEnum(dataInicial, dataFinal, StatusNotificacaoEnum.PENDENTE));
    }

    public List<TarefaDTO> buscaTarefasPorEmail(String token){
        String email = jwtUtil.extrairEmailToken(token.substring(7));

        return tarefaConverter.paraListaTarefaDTO(tarefasRepository.findByEmailUsuario(email));
    }

    public void deletarTarefaPorId(String id) {
        tarefasRepository.deleteById(String.valueOf(id));
    }

    public TarefaDTO alteraStatus(StatusNotificacaoEnum status, String id) {
        try {
            TarefasEntity entity = tarefasRepository.findById(id)
                    .orElseThrow(() -> new RuntimeException("Tarefa não encontrada"));
            entity.setStatusNotificacaoEnum(status);
            return tarefaConverter.paraTarefaDTO(tarefasRepository.save(entity));
        }  catch (ResourceNotFoundException e) {
            throw new ResourceNotFoundException("Tarefa não encontrada, erro ao alterar status" + e.getMessage());
        }

    }

    public TarefaDTO updateTarefa(TarefaDTO dto, String id) {
        try {
            TarefasEntity entity = tarefasRepository.findById(id)
                    .orElseThrow(() -> new RuntimeException("Tarefa nao encontrada"));
            tarefaUpdateConverter.updateTarefa(dto, entity);
            return tarefaConverter.paraTarefaDTO(tarefasRepository.save(entity));
        }  catch (ResourceNotFoundException e) {
            throw new ResourceNotFoundException("Tarefa nao encontrada, erro ao alterar status" + e.getMessage());
        }
    }
}
