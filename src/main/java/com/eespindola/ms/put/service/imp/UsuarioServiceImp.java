package com.eespindola.ms.put.service.imp;

import com.eespindola.ms.put.dao.UsuarioDao;
import com.eespindola.ms.put.jpa.UsuarioRepository;
import com.eespindola.ms.put.mapper.UsuarioMapper;
import com.eespindola.ms.put.models.UsuarioMl;
import com.eespindola.ms.put.models.dto.Result;
import com.eespindola.ms.put.models.dto.UsuarioDto;
import com.eespindola.ms.put.service.UsuarioService;
import com.eespindola.ms.put.utils.ConstantesUtils;
import com.eespindola.ms.put.utils.FolioUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.client.RestTemplate;

import java.util.Objects;

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

        Result<Void> response = new Result<>();
        response.setFolioRequest(Objects.requireNonNullElse(request.getFolioRequest(), FolioUtils.createFolioRequest()));
        try {
            UsuarioDto usuarioRecibido = request.getObject();
            UsuarioDto usuarioRecuperado = getByFolio(usuarioRecibido.getFolioId());
            UsuarioMl usuarioActualizado = UsuarioMapper.normalizar(usuarioRecibido, usuarioRecuperado);

//            UsuarioJpa usuarioJpa = UsuarioMapper.toUsuarioJpa(usuarioActualizado);
//            usuarioRepository.save(usuarioJpa);
//
//            response.setIsCorrect(true);
//            response.setMessage("Usuario actualizado correctamente");

            Integer resultDB = usuarioDAO.usuarioUpdate(usuarioActualizado);

            response.setIsCorrect((resultDB == 1));
            response.setMessage(switch (resultDB) {
                case 1 -> "Usuario actualizado correctamente";
                case 0 -> "No se logro actualizar el usuario";
                default -> "Error inesperado en base de datos";
            });
        } catch (Exception e) {
            response.setIsCorrect(false);
            response.setException(e);
            response.setMessage(e.getMessage());
        }
        return response;
    }

    private static UsuarioDto getByFolio(@PathVariable String folioId) {
        RestTemplate restTemplate = new RestTemplate();
        String endpoint = String.format(ConstantesUtils.GET_BY_FOLIO, folioId);

        ResponseEntity<Result<UsuarioDto>> response = restTemplate.exchange(
                endpoint,
                HttpMethod.POST,
                HttpEntity.EMPTY,
                new ParameterizedTypeReference<>() {
                }
        );
        return response.getBody().getObject();
    }

}
