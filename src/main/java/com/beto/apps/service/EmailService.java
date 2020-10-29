package com.beto.apps.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.beto.apps.models.Productos;
import com.beto.apps.service.implement.ClientesImpl;
import com.beto.apps.service.implement.ProductosImpl;

@Component
@EnableAsync
public class EmailService {

	@Autowired
	private EmailAdapter email;
	
	@Autowired
	private ProductosImpl prodDb;
	
	@Autowired
	private ClientesImpl clienteDb;
	
	
	@Scheduled(cron = "0 */12 * * *")
	public void sendAlert() {
		StringBuilder sb= new StringBuilder();
	 for(Productos model:prodDb.getAll()) {
		 
		 if(model.getCantidad()<model.getAlerta()) {
			 sb.append(model.getSku());
			 sb.append(" ");
		 }
	 }
	  email.sendSimpleMessage("ferre2go@gmail.com", "Alerta de stock bajo", "Existe faltante de los siguientes productos", sb.toString());
	}
	
	
}
