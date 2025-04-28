package com.eespindola.ms.put.service.imp;

import com.eespindola.ms.put.models.UsuarioML;
import com.eespindola.ms.put.service.UsuarioService;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Service;

import java.text.ParseException;

@Service
public class UsuarioServiceImp implements UsuarioService {

    @Override
    public UsuarioML Normalizar(@NotNull UsuarioML usuario,
                                 @NotNull UsuarioML usuarioRecuperado) throws ParseException {

        usuarioRecuperado.setNombre(
                (usuario.getNombre().isEmpty() || usuario.getNombre().isBlank())?
                        usuarioRecuperado.getNombre() : usuario.getNombre()
        );

        usuarioRecuperado.setEmail(
                (usuario.getEmail().isEmpty() || usuario.getEmail().isBlank()) ?
                        usuarioRecuperado.getEmail() : usuario.getEmail()
        );

        usuarioRecuperado.setApellidoPaterno(
                (usuario.getApellidoPaterno().isEmpty() || usuario.getApellidoPaterno().isBlank())?
                        usuarioRecuperado.getApellidoPaterno() : usuario.getApellidoPaterno()
        );

        usuarioRecuperado.setApellidoMaterno(
                (usuario.getApellidoMaterno().isEmpty() || usuario.getApellidoMaterno().isBlank())?
                        usuarioRecuperado.getApellidoMaterno() : usuario.getApellidoMaterno()
        );

        usuarioRecuperado.setFechaNacimiento(
                (usuario.getFechaNacimiento() == null ||
                        usuario.getFechaNacimiento().isEmpty() ||
                        usuario.getFechaNacimiento().isBlank()) ?
                        usuarioRecuperado.getFechaNacimiento() : usuario.getFechaNacimiento()
        );

        usuarioRecuperado.setUsername(
                (usuario.getUsername().isEmpty() || usuario.getUsername().isBlank())?
                        usuarioRecuperado.getUsername() : usuario.getUsername()
        );

        usuarioRecuperado.setPassword(
                (usuario.getPassword().isEmpty() || usuario.getPassword().isBlank())?
                        usuarioRecuperado.getPassword() : usuario.getPassword()
        );

        usuarioRecuperado.setStatus(
                (usuario.getStatus().isEmpty() || usuario.getStatus().isBlank())?
                        usuarioRecuperado.getStatus() : usuario.getStatus()
        );

        return usuarioRecuperado;
    }
}
