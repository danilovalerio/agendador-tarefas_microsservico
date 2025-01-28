package com.danilo.projeto.agendadortarefas.business.mapper;

import com.danilo.projeto.agendadortarefas.business.dto.TarefaDTO;
import com.danilo.projeto.agendadortarefas.infrastructure.entity.TarefasEntity;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

/**
 * Ao invés de usar o ternário para verificar se é nulo ou não vamos usar o
 * nullValuePropertyMappingStrategy para ignorar os campos nulos e adicionar com valores
 */

@Mapper(
        componentModel = "spring",
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE
)
public interface TarefaUpdateConverter {
    /**
     *
     * @param dto
     * @param tarefasEntity //@MappingTarget específica qual será o principal do valor.
     */

    void updateTarefa(TarefaDTO dto, @MappingTarget TarefasEntity tarefasEntity);
}
