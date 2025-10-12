package com.eespindola.ms.put.controller;

import com.eespindola.ms.put.models.dto.UsuarioDto;
import com.eespindola.ms.put.service.UsuarioService;
import com.eespindola.ms.put.models.dto.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/usuarioAPI")
public class UsuarioRestController {

    private final UsuarioService usuarioService;

    @Autowired
    public UsuarioRestController(
            UsuarioService service
    ) {
        this.usuarioService = service;
    }

    @PostMapping("/put")
    public Result<Void> usuarioPut(
            @RequestHeader(value = "folioRequest", required = false) String folioRequest,
            @RequestBody Result<UsuarioDto> request
    ) {
        log.info("Entrando a usuarioPut");
        log.info("request: {}", request);
        return usuarioService.actualizarUsuario(request);
    }

    @PostMapping("/jpa/put")
    public Result<Void> usuarioPutJpa(
            @RequestHeader(value = "folioRequest", required = false) String folioRequest,
            @RequestBody Result<UsuarioDto> request
    ) {
        log.info("Entrando a usuarioPutJpa");
        log.info("request: {}", request);
        return usuarioService.actualizarUsuarioJpa(request);
    }

}
