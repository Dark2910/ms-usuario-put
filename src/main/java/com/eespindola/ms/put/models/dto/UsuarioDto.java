package com.eespindola.ms.put.models.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UsuarioDto {

    @JsonProperty("idUsuario")
    private Integer idUsuario;

    @JsonProperty("folioId")
    private String folioId;

    @JsonProperty("nombre")
    private String nombre;

    @JsonProperty("apellidoPaterno")
    private String apellidoPaterno;

    @JsonProperty("apellidoMaterno")
    private String apellidoMaterno;

    @JsonProperty("fechaNacimiento")
    private String fechaNacimiento;

    @JsonProperty("username")
    private String username;

    @JsonProperty("email")
    private String email;

    @JsonProperty("password")
    private String password;

    @JsonProperty("status")
    private String status;

}
