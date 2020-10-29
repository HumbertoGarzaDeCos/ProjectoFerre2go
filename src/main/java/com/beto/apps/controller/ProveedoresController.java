package com.beto.apps.controller;

import java.util.ArrayList;
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

import com.beto.apps.models.Proveedores;
import com.beto.apps.models.ProveedoresProducto;
import com.beto.apps.service.implement.ProductosImpl;
import com.beto.apps.service.implement.ProveedoresImpl;
import com.beto.apps.service.implement.ProveedoresProductoImpl;

@Controller
@RequestMapping(value="/proveedores")
public class ProveedoresController {

	@Autowired
	private ProveedoresImpl proDb;
	@Autowired
	private ProveedoresProductoImpl proProdDb;
	@Autowired
	private ProductosImpl prodDb;
	@GetMapping("/")
	public ModelAndView indexProveedores(ModelAndView modelAndView) {
		
		modelAndView.addObject("productos",prodDb.getAll());
		modelAndView.addObject("proveedoresProducto",proDb.getAllWithProductos(prodDb.getAllProductosMap()));
		modelAndView.setViewName("detalleProveedor");
		return modelAndView;
	}
	@PostMapping("/getProveedor")
	public ResponseEntity<String> getProveedor(HttpServletRequest request) {
		int id=Integer.parseInt(request.getParameter("id"));
		Proveedores prov= proDb.getProveedorById(id);
		String respuesta=prov.toString().replace("kkkkk",proProdDb.getProductosListByProveedorId(prodDb.getAllProductosMap(), prov.getId()).toString());
		return new ResponseEntity<String>(respuesta,HttpStatus.OK);
	}
	@PostMapping("/crearProveedor")
	public ResponseEntity<String> crearProveedor(HttpServletRequest request){
		String nombre=request.getParameter("nombre");
		String telefono=request.getParameter("telefono");
		String []precio1,precio2,precio3,productos;
		List<ProveedoresProducto> proveedorProductos=new ArrayList<>();
		productos=request.getParameter("productos").split("\\|");
		precio1=request.getParameter("precio1").split("\\|");
		precio2=request.getParameter("precio2").split("\\|");
		precio3=request.getParameter("precio3").split("\\|");
		Proveedores prov= proDb.getProveedorByName(nombre);
		Proveedores nuevo= new Proveedores();
		if(prov!=null) {
			nuevo=prov;
		}
		nuevo.setNombre(nombre);
		nuevo.setTelefono(Long.parseLong(telefono));
		proDb.saveOrUpdate(nuevo);
		proProdDb.deleteAll(nuevo.getId());
		for(int i=1;i<productos.length;i++) {
			ProveedoresProducto provProd= new ProveedoresProducto();
			provProd.setPrecio1(Double.parseDouble(precio1[i]));
			provProd.setPrecio2(Double.parseDouble(precio2[i]));
			provProd.setPrecio3(Double.parseDouble(precio3[i]));
			provProd.setIdProd(prodDb.getProductoBySku(productos[i]).getId());
			provProd.setIdProv(nuevo.getId());
			proveedorProductos.add(provProd);
		}
		proProdDb.saveAll(proveedorProductos);
		
		
		
		
		
		return new ResponseEntity<String>("{}",HttpStatus.OK);
	}
}
