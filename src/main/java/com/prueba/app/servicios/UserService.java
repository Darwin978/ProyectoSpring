package com.prueba.app.servicios;


import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.prueba.app.entidades.Usuario;

public interface UserService {

	public Iterable<Usuario> findAll();
	
	public Page<Usuario> findAll(Pageable pageable);
	
	public Optional<Usuario> findbyId(Long id);
	
	public Usuario save(Usuario usuario);
	
	public void deletedBy(Long id);
}
