package com.eespindola.ms.put.mapper;

import com.eespindola.ms.put.jpa.entities.UsuarioJpa;
import com.eespindola.ms.put.models.UsuarioMl;
import com.eespindola.ms.put.models.dto.UsuarioDto;
import com.eespindola.ms.put.utils.ConstantesUtils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Objects;

public class UsuarioMapper {

    public interface GenericUser{
//        void setIdUsuario(Integer idUsuario);
//        void setFolioId(String folioId);
        void setNombre(String nombre);
        void setApellidoPaterno(String apellidoPaterno);
        void setApellidoMaterno(String apellidoMaterno);
        void setFechaNacimiento(Object fechaNacimiento);
        void setUsername(String username);
        void setEmail(String email);
        void setPassword(String password);
        void setStatus(String status);
    }

    private static boolean isNullOrBlank(String str) {
        return (Objects.isNull(str) || str.isBlank());
    }

    private static String valorPrioritario(String valorNuevo, String valorExistente){
        return isNullOrBlank(valorNuevo)? valorExistente : valorNuevo;
    }

    private static Date getFechaNacimiento(String fecha) throws ParseException {
        if (isNullOrBlank(fecha)) return null;
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(ConstantesUtils.DATE_FORMAT);
        return simpleDateFormat.parse(fecha);
    }

    private static void normalizarUsuario(GenericUser genericUser, UsuarioDto usuarioRecibido, UsuarioDto usuarioRecuperado){

        genericUser.setNombre(
                valorPrioritario(usuarioRecibido.getNombre(), usuarioRecuperado.getNombre())
        );
        genericUser.setEmail(
                valorPrioritario(usuarioRecibido.getEmail(), usuarioRecuperado.getEmail())
        );
        genericUser.setApellidoPaterno(
                valorPrioritario(usuarioRecibido.getApellidoPaterno(), usuarioRecuperado.getApellidoPaterno())
        );
        genericUser.setApellidoMaterno(
                valorPrioritario(usuarioRecibido.getApellidoMaterno(), usuarioRecuperado.getApellidoMaterno())
        );
        genericUser.setFechaNacimiento(
                valorPrioritario(usuarioRecibido.getFechaNacimiento(), usuarioRecuperado.getFechaNacimiento())
        );
        genericUser.setUsername(
                valorPrioritario(usuarioRecibido.getUsername(), usuarioRecuperado.getUsername())
        );
        genericUser.setPassword(
                valorPrioritario(usuarioRecibido.getPassword(), usuarioRecuperado.getPassword())
        );
        genericUser.setStatus(
                valorPrioritario(usuarioRecibido.getStatus(), usuarioRecuperado.getStatus())
        );

    }

    public static UsuarioMl normalizarToMl(UsuarioDto usuarioRecibido, UsuarioDto usuarioRecuperado) {
        UsuarioMl usuarioMl = new UsuarioMl();

        usuarioMl.setIdUsuario(usuarioRecuperado.getIdUsuario());
        usuarioMl.setFolioId(usuarioRecuperado.getFolioId());

        normalizarUsuario(usuarioMl, usuarioRecibido, usuarioRecuperado);

        return usuarioMl;
    }

    public static UsuarioJpa normalizarToJpa(UsuarioDto usuarioRecibido, UsuarioDto usuarioRecuperado) {

        UsuarioJpa usuarioJpa = new UsuarioJpa();

        usuarioJpa.setIdUsuario(usuarioRecuperado.getIdUsuario());
        usuarioJpa.setFolio(usuarioRecuperado.getFolioId());

        normalizarUsuario(usuarioJpa, usuarioRecibido, usuarioRecuperado);

        return usuarioJpa;
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

}
