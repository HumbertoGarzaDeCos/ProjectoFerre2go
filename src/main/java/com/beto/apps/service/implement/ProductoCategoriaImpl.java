package com.beto.apps.service.implement;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.beto.apps.models.ProductoCategoria;
import com.beto.apps.service.repository.ProductoCategoriaRepository;

@Component

public class ProductoCategoriaImpl {
@Autowired
private ProductoCategoriaRepository db;


public List<ProductoCategoria> getAll(){
	
	return db.findAll();
}

public List<ProductoCategoria> getAllProductsByCategoria(int id){
	List<ProductoCategoria>result = new ArrayList<>();
	for(ProductoCategoria model:db.findAll()) {
		if(model.getIdCat()==id) {
			result.add(model);
		}
		
		
	}
	return result;
}
public int getCategoriaByProducto(int id) {
	
	for(ProductoCategoria model:db.findAll()) {
		if(model.getIdProd()==id) {
			return model.getIdCat();
		}
	}
	return 0;
}
public void save(ProductoCategoria model) {
	db.save(model);
	
}
public void saveAll(List<ProductoCategoria> model) {
	
		
	db.saveAll(model);

}
public void delete(ProductoCategoria model) {
	db.delete(model);
}
public ProductoCategoria getByProductoId(int id) {
	for (ProductoCategoria model: db.findAll()) {
		if(model.getIdProd()==id) {
			return model;
		}
	}
	return null;
}
public void deleteAll(int id) {
	for(ProductoCategoria model: getAllProductsByCategoria(id)) {
		db.deleteById(model.getId());
		
	}
	
}
public Map<Integer,ProductoCategoria> getAllAsMap(){
	Map<Integer,ProductoCategoria> result= new HashMap<>();
	for(ProductoCategoria model: db.findAll()) {
		result.put(model.getIdProd(), model);
	}
	return result;
}

}
