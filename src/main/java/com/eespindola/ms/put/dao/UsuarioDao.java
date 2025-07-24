package com.eespindola.ms.put.dao;

import com.eespindola.ms.put.models.UsuarioMl;
import com.eespindola.ms.put.models.dto.Result;

import java.sql.SQLException;

public interface UsuarioDao {

    Integer usuarioUpdate(UsuarioMl usuarioMl);

}
