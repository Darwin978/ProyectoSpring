package com.prueba.app.controlador;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.prueba.app.entidades.Usuario;
import com.prueba.app.servicios.UserService;

@RestController
@RequestMapping("/api/users")
public class UserController {

	@Autowired
	private UserService userService;
	
	@PostMapping
	public ResponseEntity<?> create (@RequestBody Usuario usuario){
		return ResponseEntity.status(HttpStatus.CREATED).body(userService.save(usuario));
		
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<?> read(@PathVariable(value ="id") Long userId){
		Optional<Usuario> oUser=userService.findbyId(userId);
		
		if(!oUser.isPresent()) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(oUser);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<?>update(@RequestBody Usuario userDetails, @PathVariable(value="id") Long userId){
		Optional <Usuario> user= userService.findbyId(userId);
		if(!user.isPresent()) {
			return  ResponseEntity.notFound().build();
		}
		
		//BeanUtils.copyProperties(userDetails, user.get());
		user.get().setName(userDetails.getName());
		user.get().setApellido(userDetails.getApellido());
		user.get().setEmail(userDetails.getEmail());
		user.get().setEnable(userDetails.isEnable());
		
		return ResponseEntity.status(HttpStatus.CREATED).body(userService.save(user.get()));
	}
	
	
	@DeleteMapping("/{id}")
	public ResponseEntity<?> delete (@PathVariable(value="id") Long userId){
		
		if(!userService.findbyId(userId).isPresent()) {
			return ResponseEntity.notFound().build();
		}
		
		userService.deletedBy(userId);
		return ResponseEntity.ok().build();
	}
	
	
	@GetMapping
	public List<Usuario> readAll(){
		List<Usuario> users= StreamSupport.stream(userService.findAll().spliterator(), false).collect(Collectors.toList());
		
		return users;
	}
	
}
