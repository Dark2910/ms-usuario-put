package com.eespindola.ms.put.controller;

import com.eespindola.ms.put.models.dto.UsuarioDto;
import com.eespindola.ms.put.service.UsuarioService;
import com.eespindola.ms.put.models.dto.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
    public Result<Void> put(@RequestHeader(value = "folioRequest", required = false) String folioRequest , @RequestBody Result<UsuarioDto> request){
        return usuarioService.actualizarUsuario(request);
    }

}
