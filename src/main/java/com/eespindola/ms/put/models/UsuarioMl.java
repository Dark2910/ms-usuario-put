package com.eespindola.ms.put.models;

import com.eespindola.ms.put.mapper.UsuarioMapper;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;


@Slf4j
@Data
@NoArgsConstructor
public class UsuarioMl implements UsuarioMapper.GenericUser {

    private Integer idUsuario;
    private String folioId;
    private String nombre;
    private String apellidoPaterno;
    private String apellidoMaterno;
    private String fechaNacimiento;
    private String username;
    private String email;
    private String password;
    private String status;

    @Override
    public void setFechaNacimiento(Object fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento.toString();
    }
}
