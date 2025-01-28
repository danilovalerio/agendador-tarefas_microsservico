package com.danilo.projeto.agendadortarefas.infrastructure.client;

import com.danilo.projeto.agendadortarefas.business.dto.UsuarioDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * CONECTA COM API USUARIO
 */
@FeignClient(name = "usuario", url = "${usuario.url}")
public interface UsuarioClient {

    @GetMapping
    UsuarioDTO buscaUsuarioPorEmail(
            @RequestParam("email") String email,
            @RequestHeader("Authorization") String token);
}
