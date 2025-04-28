package com.eespindola.ms.put.service;


import com.eespindola.ms.put.models.UsuarioML;

import java.text.ParseException;

public interface UsuarioService {

    public UsuarioML Normalizar(UsuarioML usuario,
                                 UsuarioML usuarioRecuperado) throws ParseException;

}
