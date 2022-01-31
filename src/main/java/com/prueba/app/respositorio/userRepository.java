package com.prueba.app.respositorio;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.prueba.app.entidades.Usuario;

@Repository
public interface userRepository extends JpaRepository<Usuario, Long> {

	
}
