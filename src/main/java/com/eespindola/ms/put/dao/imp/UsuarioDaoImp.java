package com.eespindola.ms.put.dao.imp;

import com.eespindola.ms.put.dao.UsuarioDao;
import com.eespindola.ms.put.models.UsuarioMl;
import com.eespindola.ms.put.utils.ConstantesUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.CallableStatementCallback;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.Types;

@Repository
public class UsuarioDaoImp implements UsuarioDao {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    UsuarioDaoImp(
            @Qualifier(ConstantesUtil.HIKARI_DATA_SOURCE) DataSource dataSource
//            @Qualifier(ConstantesUtil.HIKARI_CONNECTION) JdbcTemplate jdbc
    ) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
//        this.jdbcTemplate = jdbc;
    }

    @Override
    public Integer usuarioUpdate(UsuarioMl usuarioMl) {

        String query = ConstantesUtil.USUARIO_UPDATE;

        return jdbcTemplate.execute(query, (CallableStatementCallback<Integer>) callableStatementCallback -> {

            callableStatementCallback.setString("pFolio", usuarioMl.getFolioId());
            callableStatementCallback.setString("pNombre", usuarioMl.getNombre());
            callableStatementCallback.setString("pApellidoPaterno", usuarioMl.getApellidoPaterno());
            callableStatementCallback.setString("pApellidoMaterno", usuarioMl.getApellidoMaterno());
            callableStatementCallback.setString("pFechaNacimiento", usuarioMl.getFechaNacimiento());
            callableStatementCallback.setString("pUsername", usuarioMl.getUsername());
            callableStatementCallback.setString("pEmail", usuarioMl.getEmail());
            callableStatementCallback.setString("pPassword", usuarioMl.getPassword());
            callableStatementCallback.setString("pStatus", usuarioMl.getStatus());

            callableStatementCallback.registerOutParameter("pResultado", Types.INTEGER);

            callableStatementCallback.execute();
            return callableStatementCallback.getInt("pResultado");
        });
    }
}
