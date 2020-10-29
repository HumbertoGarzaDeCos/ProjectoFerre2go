package com.beto.apps.service.implement;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.beto.apps.models.Clientes;
import com.beto.apps.service.repository.ClientesRepository;

@Component
public class ClientesImpl {
@Autowired
private ClientesRepository db;

public List<Clientes> getAll(){
	
	return db.findAll();
}

public void saveOrUpdate(Clientes model) {
	db.save(model);
}

public Clientes getClienteByUsername(String username) {
	for(Clientes model:db.findAll()) {
		if(model.getUsuario().equals(username)) {
			return model;
		}
	}
	return null;
}
public Clientes getClienteByCorreo(String correo) {
	for(Clientes model:db.findAll()) {
		if(model.getEmail().equals(correo)) {
			return model;
		}
	}
	return null;
}
public Clientes getClienteById(int id) {
	for(Clientes model:db.findAll()) {
		if(model.getId()==id) {
			return model;
			
		}		
	}
	return null;
}
}
