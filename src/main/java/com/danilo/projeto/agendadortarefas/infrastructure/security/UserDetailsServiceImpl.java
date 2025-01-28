package com.danilo.projeto.agendadortarefas.infrastructure.security;

import com.danilo.projeto.agendadortarefas.business.dto.UsuarioDTO;
import com.danilo.projeto.agendadortarefas.infrastructure.client.UsuarioClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImpl {

    @Autowired
    private UsuarioClient usuarioClient;

    public UserDetails carregaDadosDoUsuario(String email, String token) {

        UsuarioDTO usuarioDTO = usuarioClient.buscaUsuarioPorEmail(email, token);

        // Cria e retorna um objeto UserDetails com base no usuário encontrado
        return User
                .withUsername(usuarioDTO.getEmail()) // Define o nome de usuário como o e-mail
                .password(usuarioDTO.getSenha()) // Define a senha do usuário
                .build(); // Constrói o objeto UserDetails

    }
}
