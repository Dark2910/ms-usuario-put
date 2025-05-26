package com.eespindola.ms.put.dao;

import com.eespindola.ms.put.models.UsuarioML;
import com.eespindola.ms.put.models.dto.Result;

import java.sql.SQLException;

public interface UsuarioDAO {

    Result UsuarioUpdate(UsuarioML usuario) throws SQLException;

}
