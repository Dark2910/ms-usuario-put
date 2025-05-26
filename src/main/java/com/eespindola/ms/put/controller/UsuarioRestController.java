package com.eespindola.ms.put.controller;

import com.eespindola.ms.put.dao.UsuarioDAO;
import com.eespindola.ms.put.service.UsuarioService;
import com.eespindola.ms.put.utils.Constantes;
import com.eespindola.ms.put.models.dto.Result;
import com.eespindola.ms.put.models.UsuarioML;
import com.eespindola.ms.put.jpa.UsuarioRepository;
import com.eespindola.ms.put.utils.FolioRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.text.MessageFormat;

@RestController
@RequestMapping("/usuarioAPI")
public class UsuarioRestController {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private UsuarioDAO usuarioDAO;

    @PostMapping("/put")
    public Result<?> Put(@RequestHeader(value = "folioRequest", required = false) String folioRequest , @RequestBody Result<UsuarioML> request){

        Result<?> result = new Result<>();

//        folioRequest = (folioRequest == null || folioRequest.isEmpty() || folioRequest.isBlank())? FolioRequest.CrearFolioRequest() : folioRequest;

        try {
            UsuarioML usuarioRecuperado = GetByFolio(request.getObject().getFolio());

//            UsuarioJPA usuarioActualizado =  usuarioServiceImplementation.Normalizar(usuario, usuarioRecuperado);
//            usuarioRepository.save( usuarioActualizado);

            UsuarioML usuarioNormalizado = usuarioService.Normalizar(request.getObject(), usuarioRecuperado);

            result = usuarioDAO.UsuarioUpdate(usuarioNormalizado);
            result.setFolio(request.getFolio());
            result.setIsCorrect(true);
//            result.message = MessageFormat.format("Folio: {0}",folioRequest);

        } catch (Exception e){
            result.setIsCorrect(false);
            result.setException(e);
            result.setMessage(e.getLocalizedMessage());
        }
        return result;
    }

    public UsuarioML GetByFolio(@PathVariable String folioId){
        RestTemplate restTemplate = new RestTemplate();
//        String endpoint = MessageFormat.format("http://localhost:8082/usuarioAPI/{0}", folioId);
        String endpoint = String.format(Constantes.GET_BY_FOLIO, folioId);

        ResponseEntity<Result<UsuarioML>> response = restTemplate.exchange(
                endpoint,
                HttpMethod.POST,
                HttpEntity.EMPTY,
                new ParameterizedTypeReference<>() {
                }
        );
        Result result = response.getBody();

        return (UsuarioML) result.getObject();
    }
}
