package com.beto.apps.controller;

import java.time.LocalDate;
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

import com.beto.apps.models.Abastecimiento;
import com.beto.apps.models.AbastecimientoProducto;
import com.beto.apps.models.Productos;
import com.beto.apps.models.Proveedores;
import com.beto.apps.models.ProveedoresProducto;
import com.beto.apps.service.implement.AbastecimientoImpl;
import com.beto.apps.service.implement.AbastecimientoProductoImpl;
import com.beto.apps.service.implement.ProductosImpl;
import com.beto.apps.service.implement.ProveedoresImpl;
import com.beto.apps.service.implement.ProveedoresProductoImpl;

@Controller
@RequestMapping(value="/abastecimiento")
public class AbastecimientoController {
@Autowired
private ProveedoresImpl provDb;
@Autowired
private ProveedoresProductoImpl proProdDb;
@Autowired
private AbastecimientoImpl abasDb;
@Autowired 
private AbastecimientoProductoImpl abasProdDb;
@Autowired 
private ProductosImpl prodDb;
	
	@GetMapping("/")
	public ModelAndView index(ModelAndView modelAndView) {
		modelAndView.addObject("proveedores",provDb.getAll());
		modelAndView.addObject("solicitudes",abasDb.getAllWithProveedor(provDb.getMap()));
		modelAndView.setViewName("abastecimiento");
		return modelAndView;
	}
	
	@PostMapping("/getProductosProveedor")
	public ResponseEntity<String> getProductosProv(HttpServletRequest request) {
		List<ProveedoresProducto> prov=proProdDb.getProductosListByProveedorId(prodDb.getAllProductosMap(),Integer.parseInt(request.getParameter("id")));
		return new ResponseEntity<String>(prov.toString(),HttpStatus.OK);
	}
	@PostMapping("/getPrecioProducto")
	public ResponseEntity<String> getPrecioProducto(HttpServletRequest request) {
		List<ProveedoresProducto> prov=proProdDb.getProductosListByProveedorId(prodDb.getAllProductosMap(),Integer.parseInt(request.getParameter("id")));
		StringBuilder sb= new StringBuilder();
		for(ProveedoresProducto model:prov) {
			if(model.getSku().equals(request.getParameter("producto"))) {
				sb.append("{");
				sb.append("\"precio1\":");
				sb.append("\""+model.getPrecio1()+"\"");
				sb.append(",");
				sb.append("\"precio2\":");
				sb.append("\""+model.getPrecio2()+"\"");
				sb.append(",");
				sb.append("\"precio3\":");
				sb.append("\""+model.getPrecio3()+"\"");
						
				sb.append("}");
				return new ResponseEntity<String>(sb.toString(),HttpStatus.OK);
			}
		}
		
		return new ResponseEntity<String>("",HttpStatus.INTERNAL_SERVER_ERROR);
		
	}
	@PostMapping("/getSolicitud")
	public ResponseEntity<String> getSolicitud(HttpServletRequest request){
		int id=Integer.parseInt(request.getParameter("id"));
		Abastecimiento abastecimiento=abasDb.getAbastecimientoById(id);
		Proveedores prov= provDb.getProveedorById(abastecimiento.getIdProveedor());
		abastecimiento.setNombre(prov.getNombre());
		abastecimiento.setTelefono(prov.getTelefono());
		
		return new ResponseEntity<String>(abastecimiento.toString().replace("kkkkk", abasProdDb.getAbastecimintoProductoByAbastecimientoId(prov.getId(), prodDb.getAllProductosMap()).toString()),HttpStatus.OK);
	}
	@PostMapping("/crearSolicitud")
	public ResponseEntity<String> crearSolicitud(HttpServletRequest request){
		int id= Integer.parseInt(request.getParameter("id"));
		int estado=Integer.parseInt(request.getParameter("estado"));
		String precios[]=request.getParameter("precios").split("\\|");
		String cantidades[]=request.getParameter("cantidades").split("\\|");
		String productos[]=request.getParameter("productos").split("\\|");
		List<AbastecimientoProducto> productosSol= new ArrayList<>();
		Abastecimiento sol= new Abastecimiento();
		sol.setIdProveedor(id);
		sol.setFecha(LocalDate.now());
		sol.setCantidad(productos.length-1);
		sol.setEstado(estado);
		sol.setPrecioTotal(0);
		sol.setIdProveedor(id);
		double total=0d;
		abasDb.saveOrUpdate(sol);
		for(int i=1;i<productos.length;i++) {
			AbastecimientoProducto prodSol=new AbastecimientoProducto();
			Productos producto= prodDb.getProductoBySku(productos[i]);
			prodSol.setIdProd(producto.getId());
			prodSol.setPrecio(Double.parseDouble(precios[i]));
			prodSol.setCantidad(Integer.parseInt(cantidades[i]));
			prodSol.setIdAbastecimiento(sol.getId());
			total=total+(prodSol.getPrecio()*prodSol.getCantidad());
			productosSol.add(prodSol);
		}
		sol.setPrecioTotal(total);
		abasDb.saveOrUpdate(sol);
		abasProdDb.saveAll(productosSol);
		if(sol.getEstado()==1) {
			prodDb.addProductosAbastecimiento(productosSol);
		}
		return new ResponseEntity<String>("{}",HttpStatus.OK);
	}
	@PostMapping("/actualizarSolicitud")
	public ResponseEntity<String> actualizarSolicitud(HttpServletRequest request){
		int id=Integer.parseInt(request.getParameter("id"));
		int estado= Integer.parseInt(request.getParameter("estado"));
		Abastecimiento abastecimiento=abasDb.getAbastecimientoById(id);
HttpStatus status=HttpStatus.OK;
		if(abasDb.changeEstado(abastecimiento, estado)) {
			if(abastecimiento.getEstado()==1) {
				prodDb.addProductosAbastecimiento(abasProdDb.getAbastecimintoProductoByAbastecimientoId(abastecimiento.getId(), prodDb.getAllProductosMap()));
			}
		}
		else {
			status=HttpStatus.I_AM_A_TEAPOT;
		}
		return new ResponseEntity<String>("{}",status);
	}
}
