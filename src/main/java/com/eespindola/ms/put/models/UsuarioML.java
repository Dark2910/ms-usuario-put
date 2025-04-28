package com.eespindola.ms.put.models;

import lombok.Data;

@Data
public class UsuarioML {

    private int idUsuario;
    private String folio;
    private String nombre;
    private String apellidoPaterno;
    private String apellidoMaterno;
    private String fechaNacimiento;
    private String username;
    private String email;
    private String password;
    private String status;

}
