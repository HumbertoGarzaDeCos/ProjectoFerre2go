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

<meta charset="UTF-8">
<title>Administrar Proveedores</title>
<style>
.btn-file {
	position: relative;
	overflow: hidden;
	background: #007bff;
	color: white;
}

.btn-file input[type=file] {
	position: absolute;
	top: 0;
	right: 0;
	min-width: 100%;
	min-height: 100%;
	font-size: 100px;
	text-align: right;
	filter: alpha(opacity = 0);
	opacity: 0;
	outline: none;
	background: white;
	cursor: inherit;
	display: block;
}

html, body {
	overflow-x: hidden;
}

#imguploadtext {
	background: white;
}

#imagenNueva {
	width: 100px;
	height: 100px;
}

#imagenVieja {
	width: 100px;
	height: 100px;
}
</style>
<script>

$('#proveedorHolder').addClass('active');</script>
<script src="/f2g/js/proveedor.js"></script>
</head>
<body>
	<form>
		<h4>Agregar/modificar información de proveedor</h4>
		<hr>
		<input type="text" id="proveedorId" name="pidProveedor" hidden=true readOnly>
		<div class="row">

			<div class="col-md">
				<div class="form-group">
					<label for="nombreProveedor">Nombre del proveedor</label> <input
						type="text" class="form-control" id="nombreProveedor"
						placeholder="Nombre del proveedor">
				</div>
			</div>
			<div class="col-md">
				<div class="form-group">
					<label for="telefonoProveedor">Telefono</label><input
						type="number" min=1000000000 class="form-control"
						id="telefonoProveedor"
						placeholder="Telefono a 10 digitos del proveedor">
				</div>
			</div>
		</div>
		<div class="row">
			<div class="col-md-4">
				<div class="form-group ">
					<label for="listaProductos">Lista de Productos</label> <select
						id="listaProductos" class="form-control">
						
						<c:forEach items="${productos }" var="p">
						
						<option selected value="<c:out value="${p.id}"/>"><c:out value="${p.nombre }"/></option>
						</c:forEach>
						
					</select>
				</div>
			</div>
			<div class="col-md-2">
				<div class="form-group">
					<label for="precio1">Precio 1</label> $<input type="number" min=0
						step="any" class="form-control" id="precio1"
						placeholder="Precio del producto">
				</div>
			</div>
			<div class="col-md-2">
				<div class="form-group">
					<label for="precio2">Precio 2</label> $<input type="number" min=0
						step="any" class="form-control" id="precio2"
						placeholder="Precio del producto">
				</div>
			</div>
			<div class="col-md-2">
				<div class="form-group">
					<label for="precio3">Precio 3</label> $<input type="number" min=0
						step="any" class="form-control" id="precio3"
						placeholder="Precio del producto">
				</div>
			</div>
<div class="col-md-2 align-self-end">
<button type="button" class="btn btn-primary" onClick="agregarProducto()">Agregar</button>
</div>


		</div>
		<div class="row">
		<div class="col-md-8 self-align-center">
			<table id="tablaproductos" class="table table-bordered table-sm">
		<thead class="thead-blue">
			<tr>
				<th scope="col">SKU</th>
				<th scope="col">Nombre</th>
				<th scope="col">Precio 1</th>
				<th scope="col">Precio 2</th>
				<th scope="col">Precio 3</th>
				<th scope="col">Activo</th>
				<th scope="col"></th>
			</tr>
		</thead>
		<tbody>
		
		</tbody>
	</table>
</div>
<div class="col-md-4 align-self-end">
<button type="button" class="btn btn-primary" onclick="chekcForm()">Guardar</button>
</div>
		</div>
		
	</form>
	<hr>
	<h4>Lista de proveedores</h4>
	<hr>
	<table id="tablaProveedores" class="table table-bordered table-sm">
		<thead class="thead-dark">
			<tr>
				<th scope="col">Nombre</th>
				<th scope="col">Telefono</th>
				<th scope="col">Productos</th>
				
				<th scope="col"></th>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${proveedoresProducto}" var="proveedor">
		<tr>
		<td><c:out value="${proveedor.nombre }"/></td>
		<td><c:out value="${proveedor.telefono }"/></td>
		<td><c:out value="${proveedor.productos }"/></td>
		<td>
					<button type="button" onclick="getProveedor('<c:out value="${proveedor.id }"/>')"class="btn btn-primary">
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