package com.beto.apps.service.implement;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.beto.apps.models.Productos;
import com.beto.apps.models.Proveedores;
import com.beto.apps.models.ProveedoresProducto;
import com.beto.apps.service.repository.ProveedoresRepository;

@Component
public class ProveedoresImpl {
@Autowired
private ProveedoresRepository db;
@Autowired
private ProveedoresProductoImpl proProdDb;
public List<Proveedores> getAll(){
	
	return db.findAll();
}


public Proveedores getProveedorById(int id) {
	
	return db.findById(id).orElse(null);
}

public void saveOrUpdate(Proveedores model) {
	db.save(model);
}

public Proveedores getProveedorByName(String nombre) {
	for(Proveedores model:db.findAll()) {
		if(model.getNombre().equals(nombre)) {
			return model;
		}
	}
	return null;
}
public List<Proveedores> getAllWithProductos(Map<Integer,Productos>productos){
	List<Proveedores> result= new ArrayList<>();
	for (Proveedores model:db.findAll()) {
		String r="";
		for(ProveedoresProducto i:proProdDb.getAllByProveedor(model.getId())) {
			r=r+productos.get(i.getIdProd()).getNombre()+",";
		}
		model.setProductos(r);
		result.add(model);
	}
	return result;
}
public Map<Integer,Proveedores> getMap(){
	Map<Integer,Proveedores>result= new HashMap<>();
	for(Proveedores model:db.findAll()) {
		result.put(model.getId(), model);
	}
	return result;
}
}
