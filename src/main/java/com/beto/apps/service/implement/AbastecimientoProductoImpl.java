package com.beto.apps.service.implement;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.beto.apps.models.AbastecimientoProducto;
import com.beto.apps.models.Productos;
import com.beto.apps.service.repository.AbastecimientoProductoRepository;

@Component
public class AbastecimientoProductoImpl {
	@Autowired
	private AbastecimientoProductoRepository db;
	
	
	
	public List<AbastecimientoProducto> getAll() {
		
		return db.findAll();
	}
	
	public List<AbastecimientoProducto> getAbastecimintoProductoByAbastecimientoId(int id,Map<Integer,Productos>productos) {
		List <AbastecimientoProducto>result=new ArrayList<>();
		for(AbastecimientoProducto model:getAll()) {
			if(model.getIdAbastecimiento()==id) {
				model.setNombreProducto(productos.get(model.getIdProd()).getNombre());
			result.add(model);
			}
		}
		
		return result;
	}
	
	public void saveAll(List<AbastecimientoProducto> model) {
		db.saveAll(model);
		
	}
}
