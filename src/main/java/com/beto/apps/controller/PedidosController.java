package com.beto.apps.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.beto.apps.models.Articulos;
import com.beto.apps.models.Pedidos;
import com.beto.apps.service.implement.Articulosimpl;
import com.beto.apps.service.implement.PedidosImpl;
import com.beto.apps.service.implement.ProductosImpl;

@Controller
@RequestMapping(value="/pedidos")
public class PedidosController {
	
	@Autowired
	private PedidosImpl pedidosDb;
	@Autowired
	private Articulosimpl artDb;
	@Autowired
	private ProductosImpl prodDb;
	
	@GetMapping("/")
	public ModelAndView index(ModelAndView modelAndView) {
		
		modelAndView.addObject("pedidos", pedidosDb.getAll());
		modelAndView.setViewName("pedidos");
		return modelAndView;
	}
	@PostMapping("getPedido")
	public ResponseEntity<String> getPedido(HttpServletRequest request){
		int id=Integer.parseInt(request.getParameter("id"));
		Pedidos pedido=pedidosDb.getById(id);
		List<Articulos> articulos= artDb.getArticulosByPedido(pedido.getId(), prodDb.getAllProductosMap());
		
		return new ResponseEntity<String>(pedido.toString().replace("kkkkk", articulos.toString()),HttpStatus.OK);
	}
	@PostMapping("actualizarPedido")
	public ResponseEntity<String> actualizarPedido(HttpServletRequest request){
		int id=Integer.parseInt(request.getParameter("id"));
		int estado=Integer.parseInt(request.getParameter("estado"));
		Pedidos pedido=pedidosDb.getById(id);
		HttpStatus status;
		if(pedidosDb.changeEstado(pedido, estado)) {
			status=HttpStatus.OK;
			if(pedido.getEstado()==3) {
				prodDb.addProductosArticulos(artDb.getArticulosByPedido(pedido.getId(), prodDb.getAllProductosMap()));
			}
			
		}
		else {
			status=HttpStatus.I_AM_A_TEAPOT;
		}
		return new ResponseEntity<String>("{}",status);
	}
	
}
