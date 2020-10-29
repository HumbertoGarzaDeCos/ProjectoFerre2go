package com.beto.apps.service.implement;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.beto.apps.service.repository.AbastecimientoRepository;
import com.beto.apps.models.Abastecimiento;
import com.beto.apps.models.Proveedores;
@Component
public class AbastecimientoImpl {
@Autowired
private AbastecimientoRepository db;




public List<Abastecimiento> getAll(){
	
	return db.findAll();
}
public List<Abastecimiento> getAllWithProveedor(Map<Integer,Proveedores> proveedores){
	List<Abastecimiento> result=new ArrayList<>();
	for(Abastecimiento model:db.findAll()) {
		String nombre=proveedores.get(model.getIdProveedor()).getNombre();
		model.setNombre(nombre);
		model.setTelefono(proveedores.get(model.getIdProveedor()).getTelefono());		
		result.add(model);
	}
	return result;
}

public void saveOrUpdate(Abastecimiento model) {
	db.save(model);
}
public Boolean changeEstado(Abastecimiento model, int estado) {
	if(model.getEstado()==0) {
		model.setEstado(estado);
		saveOrUpdate(model);
		return true;
	}
	else {		
		return false;
	}
}
public Abastecimiento getAbastecimientoById(int id) {
	return db.findById(id).orElse(null);
}
}
