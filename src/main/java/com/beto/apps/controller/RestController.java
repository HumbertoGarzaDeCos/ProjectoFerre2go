package com.beto.apps.controller;

import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import java.time.LocalDate;
import java.util.ArrayList;

import org.aspectj.apache.bcel.ExceptionConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.beto.apps.models.Articulos;
import com.beto.apps.models.Categorias;
import com.beto.apps.models.Clientes;
import com.beto.apps.models.Pedidos;
import com.beto.apps.models.ProductoCategoria;
import com.beto.apps.models.Productos;
import com.beto.apps.service.EmailAdapter;
import com.beto.apps.service.EmailService;
import com.beto.apps.service.implement.Articulosimpl;
import com.beto.apps.service.implement.CategoriasImpl;
import com.beto.apps.service.implement.ClientesImpl;
import com.beto.apps.service.implement.PedidosImpl;
import com.beto.apps.service.implement.ProductoCategoriaImpl;
import com.beto.apps.service.implement.ProductosImpl;

@Controller
@RequestMapping(method = RequestMethod.POST, value = "/restapi")
public class RestController {
	@Autowired
	private CategoriasImpl catDb;
	@Autowired
	private ProductosImpl prodDb;
	@Autowired
	private ProductoCategoriaImpl prodCatDb;
	@Autowired
	private Articulosimpl artDb;
	@Autowired
	private PedidosImpl pedidosDb;
	@Autowired
	private ClientesImpl clientesDb;
	@Autowired
	private EmailAdapter emailSender;

	@PostMapping(value = "/categorias")
	public ResponseEntity<String> test() {
		List<Categorias> result = new ArrayList<>();
		result = catDb.getAllActive();
		HttpHeaders headers = new HttpHeaders();
		headers.add("Content-Type", "application/json");
		return new ResponseEntity<>(result.toString(), headers, HttpStatus.OK);

	}

	@PostMapping(value = "/productos")
	public ResponseEntity<String> productos(HttpServletRequest request) {
		List<Productos> result = new ArrayList<>();
		Categorias cat = catDb.getCategoriaByName(request.getParameter("Categoria"));
		List<ProductoCategoria> prodCat = prodCatDb.getAllProductsByCategoria(cat.getId());
		for (ProductoCategoria model : prodCat) {
			Productos model2 = prodDb.getProductoById(model.getIdProd());
			model2.setCategoria(cat.getNombre());
			result.add(model2);

		}
		HttpHeaders headers = new HttpHeaders();
		headers.add("Content-Type", "application/json");
		return new ResponseEntity<>(result.toString(), headers, HttpStatus.OK);

	}

	@PostMapping(value = "/busqueda")
	public ResponseEntity<String> busqueda(HttpServletRequest request) {
		List<Productos> result = new ArrayList<>();
		String key = request.getParameter("keywords");

		for (Productos model : prodDb.getAll()) {
			if (model.getDetalles().contains(key) || model.getNombre().contains(key)) {
				result.add(model);
			}

		}
		HttpHeaders headers = new HttpHeaders();
		headers.add("Content-Type", "application/json");
		return new ResponseEntity<>(result.toString(), headers, HttpStatus.OK);

	}
	@PostMapping(value="/getPedidos")
	public ResponseEntity<String> getPedidos(HttpServletRequest request){
		String usuario=request.getParameter("usuario");
		Clientes cliente= clientesDb.getClienteByUsername(usuario);
		List<String>resultado=new ArrayList<>();
		List<Pedidos>pedidos=pedidosDb.getByIdCliente(cliente.getId());
		for(Pedidos pedido:pedidos) {
			List<Articulos>articulos=artDb.getArticulosByPedido(pedido.getId(), prodDb.getAllProductosMap());
			resultado.add(pedido.toString().replace("kkkkk", articulos.toString()));
		}
		
		return new ResponseEntity<String>(resultado.toString(),HttpStatus.OK);
	}

	@PostMapping(value = "/doPedido")
	@Transactional(rollbackFor = NullPointerException.class)
	public ResponseEntity<String> realizarPedido(HttpServletRequest request) {
		try {
			String carrito[] = request.getParameter("carrito").replace("[", "").replace("]", "").split(",");
			String nombre = request.getParameter("nombre");
			String direccion = request.getParameter("direccion");
			String telefono = request.getParameter("telefono");
			String username = request.getParameter("username");
			List<Articulos> articulos = new ArrayList<>();
			int cant = 0;
			double total = 0;
			Pedidos pedido = new Pedidos();
			pedido.setDireccion(direccion);
			pedido.setEstado(0);
			pedido.setFechaActualizacion(LocalDate.now());
			pedido.setFechaCreacion(LocalDate.now());
			pedido.setNombrecliente(nombre);

			pedido.setTelefono(Long.parseLong(telefono));
			pedido.setNumArticulos(0);
			pedido.setTotal(0);
			pedido.setIdCliente(0);
			if (username != null) {
				Clientes cliente = clientesDb.getClienteByUsername(username);
				if (cliente != null)
					pedido.setIdCliente(cliente.getId());
			}
			pedidosDb.saveOrUpdate(pedido);
			for (int i = 0; i < carrito.length; i++) {
				Articulos articulo = new Articulos();
				String contenido[] = carrito[i].split(":");
				Productos producto = prodDb.getProductoBySku(contenido[1]);
				articulo.setIdPedido(pedido.getId());
				articulo.setPrecio(producto.getPrecio());
				articulo.setCantidad(Integer.parseInt(contenido[0].trim()));
				articulo.setIdProducto(producto.getId());
				articulos.add(articulo);
				cant += articulo.getCantidad();
				total += (articulo.getCantidad() * articulo.getPrecio());
			}
			pedido.setNumArticulos(cant);
			pedido.setTotal(total);
			pedidosDb.saveOrUpdate(pedido);
			artDb.saveAll(articulos);
			prodDb.deleteProductosArticulos(articulos);

			HttpHeaders headers = new HttpHeaders();
			headers.add("Content-Type", "application/json");
			return new ResponseEntity<>("[]", headers, HttpStatus.OK);
		} catch (Exception e) {
			throw new NullPointerException();
		}
	}

	@PostMapping(value = "/authenticateUser")
	public ResponseEntity<String> autenticarCliente(HttpServletRequest request) {

		String username = request.getParameter("username");
		String password = request.getParameter("password");
		Clientes cliente = clientesDb.getClienteByUsername(username);
		HttpHeaders headers = new HttpHeaders();
		headers.add("Content-Type", "application/json");

		if (cliente.getContraseña().equals(password)) {
			return new ResponseEntity<>(cliente.toString(), headers, HttpStatus.OK);
		}

		else {
			return new ResponseEntity<>("[]", headers, HttpStatus.FORBIDDEN);
		}

	}

	@PostMapping(value = "/reset")
	@Transactional(rollbackFor = NullPointerException.class)
	public ResponseEntity<String> resetContraseña(HttpServletRequest request) {
		try {
			String usuario = request.getParameter("usuario");
			Clientes cliente = clientesDb.getClienteByCorreo(usuario);
			if (cliente == null) {
				System.out.println("con correo nulo");
				cliente = clientesDb.getClienteByUsername(usuario);
			}
			if (cliente != null) {
				System.out.println("entro");
				String newPass = UUID.randomUUID().toString();
				cliente.setContraseña(newPass);
				clientesDb.saveOrUpdate(cliente);
				emailSender.sendSimpleMessage(cliente.getEmail(), "Cambio de contraseña",
						"Se realizo un cambio de contraseña", "Su nueva contraseña es " + cliente.getContraseña()
								+ ", le recomendamos cambiarla a una nueva");
			}
			return new ResponseEntity<String>("[]", HttpStatus.OK);
		} catch (Exception e) {
			throw new NullPointerException();
		}
	}

	@PostMapping(value = "/update")
	@Transactional(rollbackFor = NullPointerException.class)
	public ResponseEntity<String> actualizarDatos(HttpServletRequest request) {
		try {
			String usuario = request.getParameter("usuario"), contraseña = request.getParameter("contraseña"),
					telefono = request.getParameter("telefono"), direccion = request.getParameter("direccion");
			Clientes clientes = clientesDb.getClienteByUsername(usuario);
			if (clientes != null && clientes.getContraseña().equals(contraseña)) {
				clientes.setTelefono(Long.parseLong(telefono));
				clientes.setDireccion(direccion);
				clientesDb.saveOrUpdate(clientes);
				emailSender.sendSimpleMessage(clientes.getEmail(), "Actualizacion de datos",
						"Se actualizaron los datos", "Su direccion y telefono se han actualizado");
				return new ResponseEntity<String>("[]", HttpStatus.OK);
			} else {

				return new ResponseEntity<String>("[]", HttpStatus.FORBIDDEN);
			}
		} catch (Exception e) {
			throw new NullPointerException();
		}
	}

	@PostMapping(value = "/change")
	@Transactional(rollbackFor = NullPointerException.class)
	public ResponseEntity<String> cambiarContraseña(HttpServletRequest request) {
		try {
			String contraseña = request.getParameter("contraseñaVieja");
			String nuevaContraseña = request.getParameter("contraseña");
			String usuario = request.getParameter("usuario");
			Clientes clientes = clientesDb.getClienteByUsername(usuario);
			if (clientes != null && clientes.getContraseña().equals(contraseña)) {
				clientes.setContraseña(nuevaContraseña);
				clientesDb.saveOrUpdate(clientes);
				emailSender.sendSimpleMessage(clientes.getEmail(), "Cambio de contraseña", "Se cambio su contraseña!",
						"Se realizo un cambio de contraseña.");
				return new ResponseEntity<String>("[]", HttpStatus.OK);
			} else {

				return new ResponseEntity<String>("[]", HttpStatus.FORBIDDEN);
			}
		} catch (Exception e) {
			throw new NullPointerException();
		}
	}

	@PostMapping(value = "/register")
	@Transactional(rollbackFor = NullPointerException.class)
	public ResponseEntity<String> registrarUsuario(HttpServletRequest request) {
		try {
			Clientes cliente = new Clientes();
			String nombre = request.getParameter("nombre"), usuario = request.getParameter("usuario"),
					contraseña = request.getParameter("contraseña"), email = request.getParameter("email"),
					telefono = request.getParameter("telefono"), direccion = request.getParameter("direccion");
			Clientes correo = clientesDb.getClienteByCorreo(email);
			Clientes username = clientesDb.getClienteByUsername(usuario);
			if (username == null && correo == null) {
				cliente.setNombre(nombre);
				cliente.setUsuario(usuario);
				cliente.setContraseña(contraseña);
				cliente.setEmail(email);
				cliente.setTelefono(Long.parseLong(telefono));
				cliente.setDireccion(direccion);
				clientesDb.saveOrUpdate(cliente);
				return new ResponseEntity<String>("[]", HttpStatus.OK);
			} else {
				return new ResponseEntity<String>("[]", HttpStatus.FORBIDDEN);
			}
		} catch (Exception e) {
			throw new NullPointerException();
		}

	}

}
