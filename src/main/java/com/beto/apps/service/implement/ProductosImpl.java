package com.beto.apps.service.implement;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.beto.apps.models.AbastecimientoProducto;
import com.beto.apps.models.Articulos;
import com.beto.apps.models.Categorias;
import com.beto.apps.models.ProductoCategoria;
import com.beto.apps.models.Productos;
import com.beto.apps.service.repository.ProductosRepository;

@Component
public class ProductosImpl {
@Autowired
private ProductosRepository db;

public List<Productos> getAll(){
	return db.findAll();
}

public void saveOrUpdate(Productos model) {
	
	db.save(model);
}
public Productos getProductoById(int id) {
	
	return db.findById(id).orElse(null);
}
public List<Productos> getAllMatches(String key){
	List<Productos> result = new ArrayList<>();
	for(Productos model: db.findAll()) {
		if(model.getDetalles().contains(key)||model.getNombre().contains(key)) {
			result.add(model);
		}
		
	}
	return result;
}

public Productos getProductoByName(String nombre) {
	for(Productos model: db.findAll()) {
		if(model.getNombre().equals(nombre)) {
			return model;
		}
		
	}
	return null;
}
public Productos getProductoBySku(String sku) {
	for(Productos model:db.findAll()) {
		if(model.getSku().equals(sku)) {
			return model;
		}
	}
	return null;
}
public List<Productos> getAllProductsByCategoria(int categoria, String catNombre,Map<Integer,ProductoCategoria> prodCat){
	
	return null;
	
}
public List<Productos> getAllProductsWithCategoria(Map<Integer,Categorias> categorias, Map<Integer,ProductoCategoria> prodCat) {
	List<Productos> result= new ArrayList<>();
	for(Productos model: db.findAll()) {
		ProductoCategoria pc=prodCat.get(model.getId());
		if(pc==null) {
			model.setCategoria("Otros");
		}
		else {
		Categorias cat=categorias.get(pc.getIdCat());
		model.setCategoria(cat.getNombre());
		}
		result.add(model);
	}
	return result;
}
public void addProductosAbastecimiento(List<AbastecimientoProducto> productos) {
	for(AbastecimientoProducto producto:productos) {
		Productos prod=db.findById(producto.getIdProd()).orElse(null);
		prod.setCantidad(prod.getCantidad()+producto.getCantidad());
		db.save(prod);
	}
	
}
public void addProductosArticulos(List<Articulos> productos) {
	for(Articulos producto:productos) {
		Productos prod=db.findById(producto.getIdProducto()).orElse(null);
		prod.setCantidad(prod.getCantidad()+producto.getCantidad());
		db.save(prod);
	}
	
}
public void deleteProductosArticulos(List<Articulos> productos) {
	for(Articulos producto:productos) {
		Productos prod=db.findById(producto.getIdProducto()).orElse(null);
		prod.setCantidad(prod.getCantidad()-producto.getCantidad());
		db.save(prod);
	}
	
}
public Map<Integer,Productos> getAllProductosMap(){
	Map<Integer,Productos> result= new HashMap<>();
	for(Productos model: db.findAll()) {
		result.put(model.getId(), model);
		
		
	}
	
	return result;
}
}
