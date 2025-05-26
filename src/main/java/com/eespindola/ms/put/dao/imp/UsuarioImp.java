package com.eespindola.ms.put.dao.imp;

import com.eespindola.ms.put.dao.UsuarioDAO;
import com.eespindola.ms.put.models.UsuarioML;
import com.eespindola.ms.put.utils.Constantes;
import com.eespindola.ms.put.models.dto.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.CallableStatementCallback;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.SQLException;

@Repository
public class UsuarioImp implements UsuarioDAO {

    @Autowired
    @Qualifier(Constantes.HIKARI_CONNECTION)
    private JdbcTemplate jdbcTemplate;

    @Override
    public Result<?> UsuarioUpdate(UsuarioML usuario) throws SQLException {

        Result<?> result = new Result<>();

        String query = Constantes.USUARIO_UPDATE;

        Integer rowAffected = jdbcTemplate.execute(query, (CallableStatementCallback<Integer>) callableStatementCallback -> {

            callableStatementCallback.setString("pFolio", usuario.getFolio());
            callableStatementCallback.setString("pNombre", usuario.getNombre());
            callableStatementCallback.setString("pApellidoPaterno", usuario.getApellidoPaterno());
            callableStatementCallback.setString("pApellidoMaterno", usuario.getApellidoMaterno());
            callableStatementCallback.setString("pFechaNacimiento", usuario.getFechaNacimiento());
            callableStatementCallback.setString("pUsername", usuario.getUsername());
            callableStatementCallback.setString("pEmail", usuario.getEmail());
            callableStatementCallback.setString("pPassword", usuario.getPassword());
            callableStatementCallback.setString("pStatus", usuario.getStatus());

            callableStatementCallback.execute();

            return callableStatementCallback.getUpdateCount();
        });

        if(rowAffected != null && rowAffected != 0){
            result.setIsCorrect(true);
        } else {
            throw new SQLException();
        }

        return result;
    }
}
