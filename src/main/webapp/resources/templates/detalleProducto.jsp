<%@ page language="java" contentType="text/html; charset=UTF-8"
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
<title>Modificar Catalogo</title>
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
$('#modificaHolder').addClass('active');
</script>
<script src="js/detalleProducto.js" ></script>

<script src="js/categorias.js" ></script>
<script src="js/productos.js" ></script>
</head>
<body>
	<button style="border:none; background:none" class="font-weight-bold" id="productoBoton"onClick="cambiarProducto()">Productos</button>
	<button style="border:none; background:none" id="categoriaBoton" onClick="cambiarCategoria()">Categorias</button>
	<div id="productoDiv" >
		<form id="sendProducto">
			<input type="text" id="idProducto" name="pidProducto" value="0" hidden readOnly>
			<h4>Agregar/modificar Producto</h4>
			<hr>
			<div class="row">
				<div class="col-md">
					<div class="form-group">
						<label>Imagen del producto</label>
						<div class="row">

							<div class="col-md-9 align-self-end">
								<div class="input-group">
									<span class="input-group-btn"> <span
										class="btn btn-default btn-file"> Elegir Imagen <input
											type="file" name="imagen" id="imgInp">
									</span>
									</span> <input type="text" id="imguploadtext" class="form-control"
										readonly> <span> <input type="button"
										class="btn btn-primary" onClick="removeImage()"value="X" id="eliminarImagen"
										hidden=true></span>
								</div>
							</div>
							<div class="col-md">
							
								<img id='imagenNueva'  hidden=true /> 
								<img id='imagenVieja'src="data:image/gif;base64,<c:out value='${producto.dimensiones}'/>" alt="No hay imagen" />
							</div>
						</div>




					</div>
				</div>
				<div class="col-md">




					<div class="form-group">
						<label for="nombreProducto">Nombre</label> <input type="text"
							class="form-control" name="productoNombre" id="nombreProducto"
							placeholder="Nombre Producto" value="<c:out value="${producto.nombre }"/>">
					</div>
				</div>

			</div>
			<div class="row">

				<div class="col-md">
					<div class="form-group">
						<label for="precioProducto">Precio</label> $<input type="number"
							min=0 step="any" class="form-control" name="productoPrecio" id="precioProducto"
							placeholder="Precio del producto" value="<c:out value="${producto.precio }"/>">
					</div>
				</div>
				<div class="col-md">
					<div class="form-group">
						<label for="cantidadProducto">Cantidad</label> <input
							type="number" min=0 class="form-control" id="cantidadProducto"
							placeholder="cantidad" name="productoCantidad"value="<c:out value="${producto.cantidad }"/>">
					</div>
				</div>
	
			</div>
			<div class="row">
				<div class="col-md-6">
					<div class="form-group">
						<label for="skuProducto">SKU</label> <input type="text" name="productoSku"
							class="form-control" id="skuProducto"
							placeholder="sku del producto" value="<c:out value="${producto.sku }"/>">
					</div>
				</div>
				<div class="col-md-6 align-self-end">
					<label for="productoActivo">Activo</label>
					<div id="productoActivo" class="form-check form-check-inline">

						<div class="form-check">
							<input class="form-check-input" type="radio" name="productoActivo"
								id="idActivo" value="1" checked> <label
								class="form-check-label" for="idActivo" 
								<c:if test="${producto.activo==1}">checked</c:if>> Si </label>
						</div>
						<div class="form-check">
							<input class="form-check-input" type="radio" name="productoActivo"
								id="idDesactivado" value="0"> <label
								class="form-check-label" for="idDesactivado"
								<c:if test="${producto.activo==0}">checked</c:if>> No </label>
						</div>
					</div>
				</div>

			</div>
			<div class="row">

				<div class="col-md-6">
					<label for="stockMinimo">Alerta de stock bajo</label> <input
						type="number" min=0 class="form-control" id="stockMinimo" name="productoMinimo"
						placeholder="Minimo de stock para activar alerta"
						 value="<c:out value="${producto.alerta }"/>" />
				</div>
				<div class="col-md-6">
					<div class="form-group">
						<label for="detalleProducto">Detalles</label>
						<textarea class="form-control" id="detalleProducto" name="productoDetalles" rows="3"
							placeholder=" detalle producto" ><c:out value="${producto.detalles }"/></textarea>
					</div>

				</div>
			</div>
			<div class="row ">
				<div class="col-md-6 ">
				<div class="col-md-4">
				<div class="form-group ">
					<label for="listaCategorias">Elegir Categoria</label> <select
						id="listaCategoria" class="form-control" name="productoCategoria">
						<option value=0>Otros</option>
						<c:forEach items="${categorias}" var="categoria">
						<option value='<c:out value="${categoria.id }"/>'<c:if test="${seleccionada==categoria.id}">selected</c:if>><c:out value="${categoria.nombre }"/></option>
						</c:forEach>
						
					</select>
				</div>
			</div>
				</div>
				<div class="col-md-6  ">

					<button type="button" onclick="guardarProducto()"class="btn btn-primary">Guardar</button>
				</div>

			</div>
		</form>
	</div>
	<div id="categoriaDiv" hidden=true>
		<form>
			<h4>Agregar/modificar Categoria</h4>
			<hr>
			<div class="row">

				<div class="col-md-6">

					<div class="form-group">
						<label for="nombreCategoria">Nombre</label>
						 <input type="text"	class="form-control"  id="nombreCategoria"
							placeholder="Nombre Categoria"  onChange="getCategoria()" list="listaCate">
							<datalist id="listaCate">
							<c:forEach items="${categorias }" var="cate">
							<option value="<c:out value="${cate.nombre}"/>">
							</c:forEach>
    
  </datalist>
					</div>
				</div>

			</div>
			<div class="row">
			<div class="col-md-6 align-self-end">
					<label for="productoActivo">Activo</label>
					<div id="productoActivo" class="form-check form-check-inline">

						<div class="form-check">
							<input class="form-check-input" type="radio" name="categoriaActivo"
								id="idCatActivo" value="1" checked> <label
								class="form-check-label" for="idActivo" 
								<c:if test="${producto.activo==1}">checked</c:if>> Si </label>
						</div>
						<div class="form-check">
							<input class="form-check-input" type="radio" name="categoriaActivo"
								id="idCatInactivo" value="0"> <label
								class="form-check-label" for="idDesactivado"
								<c:if test="${producto.activo==0}">checked</c:if>> No </label>
						</div>
					</div>
				</div>
				</div>
			<div class="row">
			<div class="col-md-4">
				<div class="form-group ">
					<label for="listaProductos">Lista de Productos</label> <select
						id="listaProductos" name="productoElegido" class="form-control">
						<c:forEach items="${productos}" var="producto">
						<option selected value="<c:out value="${producto.id}"/>"><c:out value="${producto.nombre}"/></option>
						</c:forEach>
					
					</select>
				</div>
			</div>
			<div class="col-md-2 align-self-end">
			<button type="button" class="btn btn-primary" onclick="agregarProducto()">Agregar</button>
			
			</div>
			</div>
			
			<div class="row">
			<div class="col-md-6">
				<table id="tablaproductoscat" class="table table-bordered table-sm">
					<thead class="thead-blue">
						<tr>
							<th scope="col"style="display:none;"></th>
							<th scope="col">Nombre</th>
							
							<th scope="col">Activo</th>
							<th scope="col"></th>
						</tr>
					</thead>
					<tbody>
					
					</tbody>
				</table>

</div>
<div class="col-md-6 align-self-end">

					<button type="button" onClick="checkNombre()"class="btn btn-primary">Guardar</button>
				</div>


			</div>
		</form>


	</div>
	<hr>
	<h4>Lista de productos</h4>
	<hr>
	<table id="tablaproductos" class="table table-bordered table-sm">
		<thead class="thead-dark">
			<tr>
				<th scope="col">SKU</th>
				<th scope="col">Nombre</th>
				<th scope="col">Categoria</th>
				<th scope="col">Cantidad</th>
				<th scope="col">Precio</th>
				<th scope="col">Activo</th>
				<th scope="col">Stock bajo</th>
				<th scope="col"></th>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${catalogo}" var="producto">
			<tr>
				
				<td><c:out value="${producto.sku}"/></td>
				<td><c:out value="${producto.nombre}"/></td>
				<td><c:out value="${producto.categoria}"/></td>
	<td><c:out value="${producto.cantidad}"/></td>
				<td><c:out value="${producto.precio}"/></td>
				<td><c:out value="${producto.activo}"/></td>
				<td><c:if test="${producto.alerta>=producto.cantidad}">Si</c:if></td>
			
				
				<td>
					<button type="button" onClick="getProducto('<c:out value="${producto.id}"/>')" class="btn btn-primary">
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
<!-- Modal -->

</html>