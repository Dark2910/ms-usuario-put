package com.eespindola.ms.put.mapper;

import com.eespindola.ms.put.jpa.entities.UsuarioJpa;
import com.eespindola.ms.put.models.UsuarioMl;
import com.eespindola.ms.put.models.dto.UsuarioDto;
import com.eespindola.ms.put.utils.ConstantesUtil;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Objects;

public class UsuarioMapper {

    public static UsuarioMl normalizar(UsuarioDto usuarioRecibido, UsuarioDto usuarioRecuperado) {
        UsuarioMl usuarioMl = new UsuarioMl();

        usuarioMl.setIdUsuario(usuarioRecuperado.getIdUsuario());
        usuarioMl.setFolioId(usuarioRecuperado.getFolioId());

        usuarioMl.setNombre(
                isNullOrBlank(usuarioRecibido.getNombre()) ?
                usuarioRecuperado.getNombre() : usuarioRecibido.getNombre()
        );
        usuarioMl.setEmail(
                isNullOrBlank(usuarioRecibido.getEmail()) ?
                usuarioRecuperado.getEmail() : usuarioRecibido.getEmail()
        );
        usuarioMl.setApellidoPaterno(
                isNullOrBlank(usuarioRecibido.getApellidoPaterno()) ?
                usuarioRecuperado.getApellidoPaterno() : usuarioRecibido.getApellidoPaterno()
        );
        usuarioMl.setApellidoMaterno(
                isNullOrBlank(usuarioRecibido.getApellidoMaterno()) ?
                usuarioRecuperado.getApellidoMaterno() : usuarioRecibido.getApellidoMaterno()
        );
        usuarioMl.setFechaNacimiento(
                isNullOrBlank(usuarioRecibido.getFechaNacimiento()) ?
                usuarioRecuperado.getFechaNacimiento() : usuarioRecibido.getFechaNacimiento()
        );
        usuarioMl.setUsername(
                isNullOrBlank(usuarioRecibido.getUsername()) ?
                usuarioRecuperado.getUsername() : usuarioRecibido.getUsername()
        );
        usuarioMl.setPassword(
                isNullOrBlank(usuarioRecibido.getPassword()) ?
                usuarioRecuperado.getPassword() : usuarioRecibido.getPassword()
        );
        usuarioMl.setStatus(
                isNullOrBlank(usuarioRecibido.getStatus()) ?
                usuarioRecuperado.getStatus() : usuarioRecibido.getStatus()
        );

        return usuarioMl;
    }
    private static boolean isNullOrBlank(String str) {
        return (Objects.isNull(str) || str.isBlank());
    }

    public static UsuarioJpa toUsuarioJpa(UsuarioMl usuarioMl) throws ParseException {
        UsuarioJpa usuarioJpa = new UsuarioJpa();

        usuarioJpa.setIdUsuario(usuarioMl.getIdUsuario());
        usuarioJpa.setFolio(usuarioMl.getFolioId());
        usuarioJpa.setNombre(usuarioMl.getNombre());
        usuarioJpa.setApellidoPaterno(usuarioMl.getApellidoPaterno());
        usuarioJpa.setApellidoMaterno(usuarioMl.getApellidoMaterno());
        usuarioJpa.setFechaNacimiento(getFechaNacimiento(usuarioMl.getFechaNacimiento()));
        usuarioJpa.setUsername(usuarioMl.getUsername());
        usuarioJpa.setEmail(usuarioMl.getEmail());
        usuarioJpa.setPassword(usuarioMl.getPassword());
        usuarioJpa.setStatus(usuarioMl.getStatus());

        return usuarioJpa;
    }
    private static Date getFechaNacimiento(String fecha) throws ParseException {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(ConstantesUtil.DATE_FORMAT);
        return simpleDateFormat.parse(fecha);
    }

}
