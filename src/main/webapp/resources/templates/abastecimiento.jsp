<%@ page language="java" contentType="text/html; UTF-8"
	pageEncoding="UTF-8"%>
	<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<jsp:include page="dale.jsp" />
<script
	src="https://cdn.datatables.net/1.10.22/js/jquery.dataTables.min.js"></script>
<script
	src="https://cdn.datatables.net/1.10.22/js/dataTables.bootstrap4.min.js "></script>
<link rel="stylesheet"
	href="https://cdn.datatables.net/1.10.22/css/dataTables.bootstrap4.min.css">
<script src="/f2g/js/abastecimiento.js"></script>
<meta charset="UTF-8">
<title>Insert title here</title>
<style>


html, body {
	overflow-x: hidden;
}


</style>
<script>
$('#abastecimientoHolder').addClass('active');

	$(document).ready(
			function() {
				
				$('#tablaproductos').DataTable();
				$('#tablaproductoscat').DataTable();
			
			});
</script>
</head>
<body>
	<button style="border: none; background: none" class="font-weight-bold"
		id="altaBoton" onClick="cambiarAlta()">Alta de
		Solicitud</button>
	<button style="border: none; background: none" id="detalleBoton"
		onClick="cambiarDetalles()" hidden=true>Detalles de solicitud</button>
	<div id="altaDiv">
		<form>
			<h4>Registrar solicitud de abastecimiento</h4>
			<hr>
			<div class="row">
				<div class="col-md-6">
					<div class="form-group ">
						<label for="listaProveedores">Proveedor</label> <select
							id="listaProveedores" onChange="productosProveedor()" class="form-control">
							<option selected value="0">Selecciona...</option>
							<c:forEach items="${proveedores}" var="prov">
							<option value="<c:out value="${prov.id}"/>">
							<c:out value="${prov.nombre}"/>
							</option>
							</c:forEach>
						</select>
					</div>
				</div>


			</div>
			<div class="row">
				<div class="col-md-2">
					<div class="form-group ">
						<label for="listaProductos">Producto</label> <select
							id="listaProductos" onChange="precioProveedor()" class="form-control">
							
						</select>
					</div>
				</div>
				<div class="col-md-2">
					<div class="form-group">
						<label for="cantidadProducto">Cantidad</label> <input
							type="number" min=0 class="form-control" id="cantidadProducto"
							placeholder="cantidad">
					</div>
				</div>
				<div class="col-md-2">
					<div class="form-group">
						<label for="precioProducto">Precio final unitario</label> $<input
							type="number" min=0 step="any" class="form-control"
							id="precioProducto" list="listaPrecio" placeholder="Precio del producto">
							<datalist id="listaPrecio">
							</datalist>
					</div>
				</div>
				<div class="col-md-2 align-self-end">
					<div class="form-group">
						<button type="button" onclick="agregarProducto()" class="btn btn-primary">Agregar</button>
					</div>
				</div>


			</div>
			<div class="row">
				<div class="col-md-6">
					<table id="tablaproductoselegidos" class="table table-bordered table-sm">
						<thead class="thead-blue">
							<tr>
							<th scope="col">SKU</th>
								<th scope="col">Producto</th>
								<th scope="col">Cantidad</th>
								<th scope="col">Precio</th>
								<th scope="col"></th>
							</tr>
						</thead>
						<tbody>
							<tr>

							
							</tr>
							
						</tbody>
					</table>

				</div>

			</div>


			<div class="row ">

				<div class="col-md-4">
					<div class="form-group ">
						<label for="listaCategorias">Estado de la solicitud</label> <select
							id="estadoNuevo" class="form-control">
							<option value="0" selected>Pedido</option>
							<option value="1">Entregado</option>
						</select>
					</div>
				</div>
				<div class="col-md-2 align-self-end">
					<div class="form-group ">
						<button type="button" onclick="crearSolicitud()" class="btn btn-primary">Solicitar</button>
					</div>
				</div>



			</div>
		</form>
	</div>
	<div id="detallesDiv" hidden=true>
	<div class="row">
		<div class="col-md-6">
			<h5>Numero de pedido</h5>
			 <input type="text"id="detallePedido" style="background:none; border:none;" readonly >
			

		</div>

	</div>
	<div class="row">
		<div class="col-md-6">
			<h5>Proveedor:</h5>
			 <input type="text"id="detalleNombre" style="background:none; border:none;" readonly >
			

		</div>
		<div class="col-md-6">
			<h5>Telefono:</h5>
			 <input type="text" id="detalleTelefono"style="background:none; border:none;" readonly >

		</div>
	</div>
		<div class="row">
				<div class="col-md-6">
					<table id="tabladetallesol" class="table table-bordered table-sm">
						<thead class="thead-blue">
							<tr>
								<th scope="col">Producto</th>
								<th scope="col">Cantidad</th>
								<th scope="col">Precio</th>
								
							</tr>
						</thead>
						<tbody>
							
						
						</tbody>
					</table>

				</div>

			</div>
			<div class="row ">

				<div class="col-md-4">
					<div class="form-group ">
						<label for="listaCategorias">Estado de la solicitud</label> <select
							id="estadosolicitudDetalles" class="form-control">
							<option value="0" selected>Pedido</option>
							<option value="1">Entregado</option>
							<option value="2">Cancelado</option>
						</select>
					</div>
				</div>
				<div class="col-md-2 align-self-end">
					<div class="form-group ">
						<button type="button" onclick="actualizarSolicitud()" class="btn btn-primary">Actualizar</button>
					</div>
				</div>



			</div>
	</div>
	<hr>
	<h4>Solicitudes</h4>
	<hr>
	<table id="tablaSolicitudes" class="table table-bordered table-sm">
		<thead class="thead-dark">
			<tr>
				<th scope="col">Solicitud</th>
				<th scope="col">Proveedor</th>
				<th scope="col">Productos</th>
				<th scope="col">Fecha</th>
				<th scope="col">Estado</th>
				<th scope="col"></th>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${solicitudes }"  var="solicitud">
		<tr>

				<td><c:out value="${solicitud.id}"/></td>
				<td><c:out value="${solicitud.nombre}"/></td>
			<td><c:out value="${solicitud.cantidad}"/></td>
				<td><c:out value="${solicitud.fecha}"/></td>
			<td><c:out value="${solicitud.estado}"/></td>
				<td>
					<button onClick="detallesSolicitud('<c:out value="${solicitud.id}"/>')" type="button" class="btn btn-primary">
						<svg width="1em" height="1em" viewBox="0 0 16 16"
							class="bi bi-pencil" fill="currentColor"
							xmlns="http://www.w3.org/2000/svg">
  <path fill-rule="evenodd"
								d="M12.146.146a.5.5 0 0 1 .708 0l3 3a.5.5 0 0 1 0 .708l-10 10a.5.5 0 0 1-.168.11l-5 2a.5.5 0 0 1-.65-.65l2-5a.5.5 0 0 1 .11-.168l10-10zM11.207 2.5L13.5 4.793 14.793 3.5 12.5 1.207 11.207 2.5zm1.586 3L10.5 3.207 4 9.707V10h.5a.5.5 0 0 1 .5.5v.5h.5a.5.5 0 0 1 .5.5v.5h.293l6.5-6.5zm-9.761 5.175l-.106.106-1.528 3.821 3.821-1.528.106-.106A.5.5 0 0 1 5 12.5V12h-.5a.5.5 0 0 1-.5-.5V11h-.5a.5.5 0 0 1-.468-.325z" />
</svg>
					</button>
				</td>
			</tr>
		</c:forEach>
			
		
		</tbody>
	</table>

</body>
</html>