package com.eespindola.ms.put.jpa.entities;

import com.eespindola.ms.put.mapper.UsuarioMapper;
import com.eespindola.ms.put.utils.ConstantesUtils;
import jakarta.persistence.*;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@Slf4j
@Data
@Entity
@Table(name = "Usuario")
public class UsuarioJpa implements UsuarioMapper.GenericUser {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idusuario")
    private Integer idUsuario;

    @Column(name = "folio")
    private String folio;

    @Column(name = "nombre")
    private String nombre;

    @Column(name = "apellidopaterno")
    private String apellidoPaterno;

    @Column(name = "apellidomaterno")
    private String apellidoMaterno;

    @Column(name = "fechanacimiento")
    private Date fechaNacimiento;

    @Column(name = "username")
    private String username;

    @Column(name = "email")
    private String email;

    @Column(name = "password")
    private String password;

    @Column(name = "status")
    private String status;

    @Override
    public void setFechaNacimiento(Object fechaNacimiento) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(ConstantesUtils.DATE_FORMAT);
        Date date = new Date();
        try{
            date = simpleDateFormat.parse(fechaNacimiento.toString());
            this.fechaNacimiento = date;
        }catch (ParseException e){
            log.info("Error al parsear fecha de nacimiento: {}", e.getMessage());
        }
    }
}
