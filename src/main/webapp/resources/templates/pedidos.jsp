<%@ page language="java" contentType="text/html; UTF-8"
	pageEncoding="UTF-8"%>
		<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<jsp:include page="dale.jsp" />

<meta charset="UTF-8">
<title>Insert title here</title>
<script
	src="https://cdn.datatables.net/1.10.22/js/jquery.dataTables.min.js"></script>
<script
	src="https://cdn.datatables.net/1.10.22/js/dataTables.bootstrap4.min.js "></script>
<link rel="stylesheet"
	href="https://cdn.datatables.net/1.10.22/css/dataTables.bootstrap4.min.css">
<style>


html, body {
	overflow-x: hidden;
}

#
</style>
<script>
$('#pedidosHolder').addClass('active');
	$(document).ready(
			function() {
				
				$('#tablapedidos').DataTable();
				
			
			});
</script>
<script src="/f2g/js/pedidos.js" ></script>
</head>
<body>
<div hidden=true id="divDetalles">
<div class="row">
		<div class="col-md-6">
			<h5>Numero de pedido</h5>
			 <input type="text"id="pedidoCliente" style="background:none; border:none;" readonly >
			

		</div>

	</div>
	<div class="row">
		<div class="col-md-6">
			<h5>Nombre del cliente:</h5>
			 <input type="text"id="nombreCliente" style="background:none; border:none; width:500px;" readonly >
			

		</div>
		<div class="col-md-6">
			<h5>Dirección:</h5>
			 <input type="text" id="direccionCliente"style="background:none; border:none; width:500px;" readonly >

		</div>
	</div>
<div class="row">
		<div class="col-md-6">
		<h5>Telefono</h5>
			 <input type="text" id="telefonoCliente" style="background:none; border:none;" readonly >
		</div>
		<div class="col-md-6">
		<h5>Fecha</h5>
			 <input type="text" id="fechaCliente"style="background:none; border:none;" readonly>
		</div>
	</div>
	<div class="row">
		<div class="col-md-4">
			<div class="form-group ">
					<label for="listaEstado">Cambiar Estado</label> <select
						id="listaEstado" class="form-control">
						<option value=0 selected>Pendiente</option>
						<option value=1>Aceptado</option>
						<option value=2>Entregado</option>
						<option value=3>Cancelado</option>
					</select>
				</div>
		</div>
		<div class="col-md-2 align-self-end">
		<button type="button" onClick="actualizar()" class="btn btn-primary" id="actualizarId">Actualizar</button>
		</div>
		<div class="col-md-6">
		<h5> Articulos</h5>
			<table id="tablaproductos" class="table table-bordered table-sm">
		<thead class="thead-blue">
			<tr>
				<th scope="col">Producto</th>
				<th scope="col">Cantidad</th>			
				<th scope="col">Precio Unitario</th>
				<th scope="col">Precio Total</th>
				
			</tr>
		</thead>
		<tbody>
			
			
		</tbody>
	</table>
		
	</div>
	</div>
	</div>
	<div class="row">
		<div class="col-md-6"></div>
		<div class="col-md-6"></div>
	</div>
	<hr>
	<h4>Lista de pedidos</h4>
	<hr>
	<table id="tablapedidos" class="table table-bordered table-sm">
		<thead class="thead-dark">
			<tr>
				<th scope="col">No. Pedido</th>
				<th scope="col">Articulos</th>
				<th scope="col">Total</th>
				<th scope="col">Fecha</th>
				<th scope="col">Estado</th>
				<th scope="col">Actualizado</th>
				<th scope="col"></th>
			</tr>
		</thead>
		<tbody>

		<c:forEach items="${pedidos }" var="pedido">
				<tr>
		<td><c:out value="${pedido.id}"/></td>
		<td><c:out value="${pedido.numArticulos}"/></td>
		<td><c:out value="${pedido.total}"/></td>
		<td><c:out value="${pedido.fechaCreacion}"/></td>
		<c:choose>
		<c:when test="${pedido.estado==0}">
		<td>Pendiente</td>
		</c:when>
		<c:when test="${pedido.estado==1 }">
		<td>Aceptado</td>
		</c:when>
		<c:when test="${ pedido.estado==2}">
		<td>Entregado</td>
		</c:when>
		<c:otherwise>
		<td>Cancelado</td>
		</c:otherwise>
		</c:choose>
		<td><c:out value="${pedido.fechaActualizacion}"/></td>
		<td>
		<button type="button" onclick="verProducto('<c:out value="${pedido.id}"/>')"class="btn btn-primary">
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