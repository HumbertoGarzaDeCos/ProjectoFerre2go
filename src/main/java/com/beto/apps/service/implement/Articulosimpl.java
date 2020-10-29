package com.beto.apps.service.implement;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.beto.apps.models.Articulos;
import com.beto.apps.models.Productos;
import com.beto.apps.service.repository.ArticulosRepository;

@Component
public class Articulosimpl {
@Autowired
private ArticulosRepository db;


public List<Articulos> getArticulosByPedido(int id,Map<Integer,Productos> productos) {
	List <Articulos> result= new ArrayList<>();
	 for(Articulos model: db.findAll()) {
		if(model.getIdPedido()==id) {
			model.setNombre(productos.get(model.getIdProducto()).getNombre());
			model.setTotal(model.getCantidad()*model.getPrecio());
			result.add(model);
		}
	}
	 return result;
}
public void saveAll(List<Articulos> model ) {
	db.saveAll(model);
	
}

}
