package com.danilo.projeto.agendadortarefas.business.mapper;

import com.danilo.projeto.agendadortarefas.business.dto.TarefaDTO;
import com.danilo.projeto.agendadortarefas.infrastructure.entity.TarefasEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UsuarioConverter {

    TarefasEntity paraTarefaEntity(TarefaDTO dto);

    TarefaDTO paraTarefaDTO(TarefasEntity entity);
}
