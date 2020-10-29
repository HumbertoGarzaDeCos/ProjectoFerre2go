package com.beto.apps.service.implement;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.HashMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.beto.apps.models.Categorias;
import com.beto.apps.service.repository.CategoriasRepository;

@Component
public class CategoriasImpl {
@Autowired
private CategoriasRepository db;


public List<Categorias> getAll() {
	return db.findAll();
	
}
public void saveOrUpdate(Categorias model) {
	db.save(model);
}

public Categorias getCategoriaByName(String nombre) {
	for(Categorias model: db.findAll()) {
		if(model.getNombre().equals(nombre)) {
			return model;
		}
	}
	return null;
	
}
public Categorias getCategoriaById(int id) {
	return db.findById(id).orElse(null);
}
public List<Categorias> getAllActive(){
	List<Categorias> result=new ArrayList<>();
	for(Categorias model:db.findAll()) {
		if(model.getActivo()==1) {
			result.add(model);
		}
	}
	return result;
}

public void changeEstado(Categorias model, int estado) {
	model.setActivo(estado);
	db.save(model);
	
}

public Map<Integer,Categorias> getAllAsMap(){
	Map<Integer,Categorias> result= new HashMap<>();
	for(Categorias model: db.findAll()) {
		result.put(model.getId(),model);
	}
	return result;
	}
}
