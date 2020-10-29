package com.beto.apps.service.implement;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.beto.apps.models.Clientes;
import com.beto.apps.models.Pedidos;
import com.beto.apps.service.EmailAdapter;
import com.beto.apps.service.repository.PedidosRepository;

@Component
public class PedidosImpl {
@Autowired
private PedidosRepository db;
@Autowired
private EmailAdapter email;
@Autowired
private ClientesImpl clientesDb;

public void saveOrUpdate(Pedidos model) {
	db.save(model);
	
}



public List<Pedidos> getAll(){
	
	return db.findAll();
}
public List<Pedidos> getByIdCliente(int id) {
	List<Pedidos> result = new ArrayList<>();
	for(Pedidos model: db.findAll()) {
		if(model.getIdCliente()==id) {
		result.add(model);
		}
	}
	return result;
}

public Boolean changeEstado(Pedidos model, int estado) {
	if(model.getEstado()<2) {
		model.setEstado(estado);
		db.save(model);
		if(model.getIdCliente()!=0) {
			String titulo="";
			String contenido="";
			switch(model.getEstado()) {
			case 1: 
				titulo="Tu pedido esta en camino!";
				contenido="Tu pedido numero "+model.getArticulosId()+" ha sido aprobado y esta en camino!";
				break;
			case 2: 
				titulo="Tu pedido fue entregado!";
				contenido="Tu pedido numero "+model.getArticulosId()+" ha sido entregado con exito!";
				break;
			default: break;
			}
			Clientes cliente=clientesDb.getClienteById(model.getIdCliente());
			email.sendSimpleMessage(cliente.getEmail(), "Estado de tu pedido"+model.getId(),titulo, contenido);
		}
		
		return true;
	}
	else {
		return false;
		}
}

public Pedidos getById(int id) {
	return db.findById(id).orElse(null);
}

}
