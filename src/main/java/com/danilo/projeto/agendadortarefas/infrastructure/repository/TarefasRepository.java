package com.danilo.projeto.agendadortarefas.infrastructure.repository;

import com.danilo.projeto.agendadortarefas.infrastructure.entity.TarefasEntity;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface TarefasRepository extends MongoRepository<TarefasEntity, String> {

}
