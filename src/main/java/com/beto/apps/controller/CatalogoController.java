package com.beto.apps.controller;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.beto.apps.models.Categorias;
import com.beto.apps.models.ProductoCategoria;
import com.beto.apps.models.Productos;
import com.beto.apps.service.ImageConverter;
import com.beto.apps.service.implement.CategoriasImpl;
import com.beto.apps.service.implement.ProductoCategoriaImpl;
import com.beto.apps.service.implement.ProductosImpl;

@Controller
@RequestMapping(value="/")
public class CatalogoController {
	@Autowired
	private CategoriasImpl catDb;
	@Autowired
	private ProductosImpl prodDb;
	@Autowired
	private ProductoCategoriaImpl prodCatDb;
	@GetMapping(value="/")
	public ModelAndView index(ModelAndView modelAndView) {
		modelAndView.setViewName("index");
		
		Map<Categorias, List<Productos>> catalogo=new HashMap<>();
		for(Categorias model:catDb.getAllActive()) {
			model.setFaltantes(false);
			List<Productos> productos= new ArrayList<>();
			for(ProductoCategoria model2:prodCatDb.getAllProductsByCategoria(model.getId())) {
				
				Productos model3=new Productos();
				model3=prodDb.getProductoById(model2.getIdProd());
				if(model.getFaltantes()!=true&&model3.getCantidad()<=model3.getAlerta()) {
					model.setFaltantes(true);
				}
				productos.add(model3);
			} 
			catalogo.put(model, productos);
		}		
		modelAndView.addObject("catalogo",catalogo);
		return modelAndView;
	}
	@PostMapping("/getProdCat")
	public ResponseEntity<String> getProdCat(HttpServletRequest request){
		Categorias cate=catDb.getCategoriaByName(request.getParameter("nombre"));
		List<ProductoCategoria>productos=prodCatDb.getAllProductsByCategoria(cate.getId());
		
		List<Productos> result= new ArrayList<>();
		for(ProductoCategoria prod:productos) {
			result.add(prodDb.getProductoById(prod.getIdProd()));
		}
		String resulta=cate.toString().replace("kkkkk", result.toString());
		return new ResponseEntity<String>(resulta,HttpStatus.OK);
	}
	@GetMapping("/modificar")
	public ModelAndView modificarCatalogo(HttpServletRequest request) {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("categorias",catDb.getAll());
		modelAndView.addObject("productos", prodDb.getAll());
		modelAndView.addObject("catalogo",prodDb.getAllProductsWithCategoria(catDb.getAllAsMap(), prodCatDb.getAllAsMap()));
		modelAndView.setViewName("detalleProducto");
		
		return modelAndView;
	}
	@PostMapping("/getProducto")
	public ResponseEntity<String> getProducto(HttpServletRequest request) {
		int id= Integer.parseInt(request.getParameter("id"));
		Productos producto= prodDb.getProductoById(id);
		int prodCat=prodCatDb.getCategoriaByProducto(producto.getId());
	
		producto.setCategoria(""+prodCat);
		return new ResponseEntity<>(producto.toString(),HttpStatus.OK);
		
		
	}
	@PostMapping("/checkProducto")
	public ResponseEntity<String> checkProducto(HttpServletRequest request) {
		String sku= request.getParameter("sku");		
		String id=request.getParameter("id");
		Productos productoId=prodDb.getProductoById(Integer.parseInt(id));		
		Productos productoSku= prodDb.getProductoBySku(sku);
	
		HttpStatus response;
		if(productoSku!=null) {
			if(productoId!=null&&productoId.getId()==productoSku.getId()) {
				response=HttpStatus.OK;
			}
			else {
				response=HttpStatus.INTERNAL_SERVER_ERROR;
			}
		}
		else {
			response=HttpStatus.OK;
		}
		
		 return new ResponseEntity<>("{}",response);
		
	}
	@PostMapping("/crearProducto")
	public ResponseEntity<String> crearProducto(@RequestParam("imagen")MultipartFile file, HttpServletRequest request) {
		Productos nuevoProducto= new Productos();
		
		String id= request.getParameter("pidProducto");
		if(id!=null&&!id.equals("")) {
			System.out.println("Entro a ver");
			nuevoProducto=prodDb.getProductoById(Integer.parseInt(id));
		}
		
		nuevoProducto.setActivo(Integer.parseInt(request.getParameter("productoActivo")));
		nuevoProducto.setAlerta(Integer.parseInt(request.getParameter("productoMinimo")));
		nuevoProducto.setCantidad(Integer.parseInt(request.getParameter("productoCantidad")));
		String idCat=request.getParameter("productoCategoria");
		nuevoProducto.setDetalles(request.getParameter("productoDetalles"));
		if(file!=null&&!file.isEmpty()) {
		nuevoProducto.setDimensiones(ImageConverter.imageConverter(file));
		}
		nuevoProducto.setNombre(request.getParameter("productoNombre"));
		nuevoProducto.setPrecio(Double.parseDouble(request.getParameter("productoPrecio")));
		nuevoProducto.setSku(request.getParameter("productoSku"));
		if((file!=null&&!file.isEmpty())||(id!=null&&!id.equals(""))) {
			if(id!=null) {
				ProductoCategoria prev=prodCatDb.getByProductoId(Integer.parseInt(id));
				if(prev!=null) {
					prodCatDb.delete(prev);
				}
			}
			prodDb.saveOrUpdate(nuevoProducto);
			Categorias cat=catDb.getCategoriaById(Integer.parseInt(idCat));
			ProductoCategoria prodCat=new ProductoCategoria();
			prodCat.setIdCat(cat.getId());
			prodCat.setIdProd(nuevoProducto.getId());
			List<ProductoCategoria> lista=new ArrayList<>();
			lista.add(prodCat);
			prodCatDb.saveAll(lista);
		}
		return new ResponseEntity<String>("{}",HttpStatus.OK);
	}
	@PostMapping("/crearCategoria")
	public ResponseEntity<String> crearCategoria(HttpServletRequest request) {
	
		String nombre=request.getParameter("nombreCategoria");
		String activo=request.getParameter("activoCategoria");
		String productos=request.getParameter("productosCategoria");
		Categorias cat=catDb.getCategoriaByName(nombre);
		
		Categorias categoriaNueva= new Categorias();
		categoriaNueva.setNombre(nombre);
		if(cat!=null) {
			categoriaNueva=cat;
		}
		categoriaNueva.setActivo(Integer.parseInt(activo));
		catDb.saveOrUpdate(categoriaNueva);
		if(productos!=null) {
			prodCatDb.deleteAll(categoriaNueva.getId());
			List<ProductoCategoria> allProd=new ArrayList<>();
		String idProductos[]=productos.split("\\|");
		for(int i=1;idProductos.length>i;i++) {
			
			Productos pr=prodDb.getProductoBySku(idProductos[i]);
			ProductoCategoria prev=prodCatDb.getByProductoId(pr.getId());
			if(prev!=null) {prodCatDb.delete(prev);}
			ProductoCategoria prodCat=new ProductoCategoria();
			prodCat.setIdCat(categoriaNueva.getId());
			prodCat.setIdProd(pr.getId());
			allProd.add(prodCat);
		}
		prodCatDb.saveAll(allProd);
		}
		return new ResponseEntity<String>("{}",HttpStatus.OK);
	}
	@PostMapping("/detallesProducto")
	public ModelAndView detalle(HttpServletRequest request) {
		ModelAndView modelAndView= new ModelAndView();
		int id=Integer.parseInt(request.getParameter("id"));
		modelAndView.addObject("producto", prodDb.getProductoById(id));
		modelAndView.addObject("seleccionada", prodCatDb.getCategoriaByProducto(id));
		modelAndView.addObject("categorias",catDb.getAll());
		modelAndView.addObject("productos", prodDb.getAll());
		modelAndView.addObject("catalogo",prodDb.getAllProductsWithCategoria(catDb.getAllAsMap(), prodCatDb.getAllAsMap()));		
		modelAndView.setViewName("detalleProducto");
		return modelAndView;
	}
	
	
}
