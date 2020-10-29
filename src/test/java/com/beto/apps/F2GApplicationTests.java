package com.beto.apps;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.beto.apps.controller.RestController;
import com.beto.apps.models.Clientes;
import com.beto.apps.service.implement.ClientesImpl;

@SpringBootTest
class F2GApplicationTests {

	@Test
	void contextLoads() {
	}
	@Autowired
	private RestController rest;
	@Autowired
	private ClientesImpl clienteDb;
	
	@Test
	public void testRestController() {
		
		assertThat(check(rest.test(),new ResponseEntity<String>("[]", HttpStatus.OK))).isEqualTo(true);

		assertThat(check(rest.test(),new ResponseEntity<String>("", HttpStatus.OK))).isEqualTo(false);
		Clientes cliente1= new Clientes();
		cliente1.setNombre("Nombre");
		cliente1.setTelefono(8112817151L);
		cliente1.setUsuario("Tangaban");
		cliente1.setDireccion("Direccion");
		cliente1.setContraseña("Password");
		cliente1.setEmail("email@hihi.com");
		clienteDb.saveOrUpdate(cliente1);
		Clientes cliente2= clienteDb.getClienteById(1);
		assertThat(cliente1.getNombre()).isEqualTo(cliente2.getNombre());
	}

	private boolean check(ResponseEntity<String> test, ResponseEntity<String> responseEntity) {
		if(test.getBody().equals(responseEntity.getBody())&&
		test.getStatusCode().equals(responseEntity.getStatusCode())) {
			return true;
		}
		return false;
	}
}
