package com.eespindola.ms.put.jpa;

import com.eespindola.ms.put.jpa.entities.UsuarioJPA;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioRepository extends JpaRepository<UsuarioJPA, Object> {
}
