package com.eespindola.ms.put.service.imp;

import com.eespindola.ms.put.dao.UsuarioDao;
import com.eespindola.ms.put.jpa.UsuarioRepository;
import com.eespindola.ms.put.jpa.entities.UsuarioJpa;
import com.eespindola.ms.put.mapper.UsuarioMapper;
import com.eespindola.ms.put.models.UsuarioMl;
import com.eespindola.ms.put.models.dto.Result;
import com.eespindola.ms.put.models.dto.UsuarioDto;
import com.eespindola.ms.put.service.UsuarioService;
import com.eespindola.ms.put.utils.ConstantesUtils;
import com.eespindola.ms.put.utils.FolioUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.client.RestTemplate;

import java.util.Objects;

@Slf4j
@Service
public class UsuarioServiceImp implements UsuarioService {

    private final UsuarioRepository usuarioRepository;
    private final UsuarioDao usuarioDAO;

    @Autowired
    public UsuarioServiceImp(
            UsuarioRepository repository,
            UsuarioDao dao
    ) {
        this.usuarioRepository = repository;
        this.usuarioDAO = dao;
    }

    @Override
    public Result<Void> actualizarUsuario(Result<UsuarioDto> request) {
        log.info("Entrando a actualizarUsuario");
        log.info("request: {}", request);

        String folioResponse = Objects.requireNonNullElse(request.getFolioRequest(), FolioUtils.createFolioRequest());
        log.info("folioResponse: {}", folioResponse);

        Result<Void> response = new Result<>();
        response.setFolioRequest(folioResponse);
        try {
            UsuarioDto usuarioRecibido = request.getObject();
            UsuarioDto usuarioRecuperado = getByFolio(usuarioRecibido.getFolioId());
            UsuarioMl usuarioActualizado = UsuarioMapper.normalizarToMl(usuarioRecibido, usuarioRecuperado);

            Integer resultDB = usuarioDAO.usuarioUpdate(usuarioActualizado);
            log.info("Usuario actualizado correctamente");

            response.setIsCorrect((resultDB == 1));
            response.setMessage(switch (resultDB) {
                case 1 -> "Usuario actualizado";
                case 0 -> "No se logro actualizar el usuario";
                default -> "Error inesperado en base de datos";
            });
        } catch (Exception e) {
            log.info("Error al actualizar el usuario: {}", e.getMessage());
            response.setIsCorrect(false);
            response.setException(e);
            response.setMessage(e.getMessage());
        }
        log.info("response: {}", response);
        return response;
    }

    @Override
    public Result<Void> actualizarUsuarioJpa(Result<UsuarioDto> request) {

        Result<Void> response = new Result<>();
        response.setFolioRequest(Objects.requireNonNullElse(request.getFolioRequest(), FolioUtils.createFolioRequest()));
        try {
            UsuarioDto usuarioRecibido = request.getObject();
            UsuarioDto usuarioRecuperado = getByFolio(usuarioRecibido.getFolioId());
            UsuarioJpa usuarioJpa = UsuarioMapper.normalizarToJpa(usuarioRecibido, usuarioRecuperado);

            log.info("Lanzando peticion...");
            usuarioRepository.save(usuarioJpa);
            log.info("Usuario actualizado correctamente");

            response.setIsCorrect(true);
            response.setMessage("Usuario actualizado");

        } catch (Exception e) {
            log.info("Error al actualizar el usuario: {}", e.getMessage());
            response.setIsCorrect(false);
            response.setException(e);
            response.setMessage(e.getMessage());
        }
        log.info("response: {}", response);
        return response;
    }

    private static UsuarioDto getByFolio(@PathVariable String folioId) {
        log.info("Entrando a getByFolio");
        log.info("folioId: {}", folioId);

        RestTemplate restTemplate = new RestTemplate();
        String endpoint = String.format(ConstantesUtils.GET_BY_FOLIO, folioId);

        log.info("Lanzando consulta de usuario por folioId");
        ResponseEntity<Result<UsuarioDto>> response = restTemplate.exchange(
                endpoint,
                HttpMethod.POST,
                HttpEntity.EMPTY,
                new ParameterizedTypeReference<>() {
                }
        );
        UsuarioDto usuarioDto = response.getBody().getObject();
        log.info("respuesta busqueda: {}", usuarioDto);
        return usuarioDto;
    }

}
