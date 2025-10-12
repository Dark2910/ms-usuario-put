package com.eespindola.ms.put.service;


import com.eespindola.ms.put.models.dto.Result;
import com.eespindola.ms.put.models.dto.UsuarioDto;

public interface UsuarioService {

    Result<Void> actualizarUsuario(Result<UsuarioDto> request);
    Result<Void> actualizarUsuarioJpa(Result<UsuarioDto> request);

}
