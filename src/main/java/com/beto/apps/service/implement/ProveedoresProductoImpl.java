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
import com.beto.apps.service.repository.ProveedoresProductoRepository;

@Component
public class ProveedoresProductoImpl {

	
	@Autowired
	private ProveedoresProductoRepository db;
	
	
	public List<ProveedoresProducto> getAll(){
		
		return db.findAll();
	}
	public List<ProveedoresProducto> getAllWithProducto(Map<Integer,Productos> productos){
		List<ProveedoresProducto>result=new ArrayList<>();
		for(ProveedoresProducto model:db.findAll()) {
			Productos producto=productos.get(model.getIdProd());
			model.setNombre(producto.getNombre());
			model.setSku(producto.getSku());
			model.setActivo((producto.getActivo()==0?"Inactivo":"Activo"));
			result.add(model);
		}
		return result;
	}
	public List<ProveedoresProducto> getProductosListByProveedorId(Map<Integer,Productos> productos, int proveedorId){
		List<ProveedoresProducto>result=new ArrayList<>();
		for(ProveedoresProducto model:db.findAll()) {
			if(model.getIdProv()==proveedorId) {
				Productos producto=productos.get(model.getIdProd());
				model.setNombre(producto.getNombre());
				model.setSku(producto.getSku());
				model.setActivo((producto.getActivo()==0?"Inactivo":"Activo"));
				result.add(model);
			}
		}
		return result;
	}
	public void saveAll(List<ProveedoresProducto> model) {
		db.saveAll(model);
		
	}
	
	public List<ProveedoresProducto> getAllByProveedor(int id) {
		List<ProveedoresProducto> result= new ArrayList<>();
		for(ProveedoresProducto model: db.findAll() ) {
			if(model.getIdProv()==id) {
				result.add(model);
			}
		}
		return result;
	}
	public void deleteAll(int id) {
		for(ProveedoresProducto model: getAllByProveedor(id)) {

			db.deleteById(model.getId());	
		}
	}
	
	public Map<Integer,int[]> getProductosIdProveedorMap(List<Proveedores>proveedores){
		Map<Integer,int[]>result= new HashMap<>();
		for(Proveedores model:proveedores) {
			List<ProveedoresProducto>proveedoresProducto=getAllByProveedor(model.getId());
			int []idProductos= new int[proveedoresProducto.size()];
			for(int i=0;i<idProductos.length;i++) {
				idProductos[i]=proveedoresProducto.get(i).getIdProd();
			}
			result.put(model.getId(), idProductos);
		}
		return result;
	}
}
