<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<jsp:include page="dale.jsp" />
<meta charset="UTF-8">
<title>Catalogo de productos</title>
<script src="js/index.js" ></script>
<script>
$('#catalogoHolder').addClass('active');
</script>
</head>
<body>
	<c:forEach items="${catalogo}" var="categoria">
		<c:set var="cat" value="${categoria.key}" />
		<h4>
			<c:out value="${cat.nombre}" />
			<c:if test="${cat.faltantes==true}">
				<span class="badge badge-pill badge-danger">!</span>
			</c:if>
			<button id="open<c:out value='${cat.id}'/>" type="button" class="btn btn-outline-light" onClick="openDiv('<c:out value='${cat.id}'/>')">
				<svg width="1em" height="1em" viewBox="0 0 16 16" class="bi bi-patch-plus-fll" fill="black" xmlns="http://www.w3.org/2000/svg">
  <path fill-rule="evenodd" d="M10.067.87a2.89 2.89 0 0 0-4.134 0l-.622.638-.89-.011a2.89 2.89 0 0 0-2.924 2.924l.01.89-.636.622a2.89 2.89 0 0 0 0 4.134l.637.622-.011.89a2.89 2.89 0 0 0 2.924 2.924l.89-.01.622.636a2.89 2.89 0 0 0 4.134 0l.622-.637.89.011a2.89 2.89 0 0 0 2.924-2.924l-.01-.89.636-.622a2.89 2.89 0 0 0 0-4.134l-.637-.622.011-.89a2.89 2.89 0 0 0-2.924-2.924l-.89.01-.622-.636zM8.5 6a.5.5 0 0 0-1 0v1.5H6a.5.5 0 0 0 0 1h1.5V10a.5.5 0 0 0 1 0V8.5H10a.5.5 0 0 0 0-1H8.5V6z"/>
</svg>
			</button>
			<button id="close<c:out value='${cat.id}'/>" type="button" class="btn btn-outline-light" hidden onClick="closeDiv('<c:out value='${cat.id}'/>')">
				<svg width="1em" height="1em" viewBox="0 0 16 16" class="bi bi-patch-minus-fll" fill="black" xmlns="http://www.w3.org/2000/svg">
  <path fill-rule="evenodd" d="M10.067.87a2.89 2.89 0 0 0-4.134 0l-.622.638-.89-.011a2.89 2.89 0 0 0-2.924 2.924l.01.89-.636.622a2.89 2.89 0 0 0 0 4.134l.637.622-.011.89a2.89 2.89 0 0 0 2.924 2.924l.89-.01.622.636a2.89 2.89 0 0 0 4.134 0l.622-.637.89.011a2.89 2.89 0 0 0 2.924-2.924l-.01-.89.636-.622a2.89 2.89 0 0 0 0-4.134l-.637-.622.011-.89a2.89 2.89 0 0 0-2.924-2.924l-.89.01-.622-.636zM6 7.5a.5.5 0 0 0 0 1h4a.5.5 0 0 0 0-1H6z"/>
</svg>
			</button>
		</h4>
		<div hidden id="div<c:out value='${ cat.id}'/>" class="container">
			<div class="row">
				<c:set var="count" value="0" />
				<c:forEach items="${categoria.value}" var="producto">
					<c:if test="${count%3==0}">
					</div>
					<div class="row">
					</c:if>
					<c:if test="${producto.activo==1}">
				<div class="col-sm" align="center">
				<h5>
					<c:out value="${producto.nombre }" />
					<c:if test="${producto.alerta>=producto.cantidad}">
						<span class="badge badge-pill badge-danger">!</span>
					</c:if>
				</h5>
				<img src="data:image/gif;base64,<c:out value='${producto.dimensiones}'/>"
					style='height: 100px; width: 100px;' />
				<br> 
				Precio:	<c:out value="${producto.precio}" />
				<br>
				Cantidad: <c:out value="${producto.cantidad}" />
				<br> <input type="button" class="btn btn-dark btn-sm"
					value="ver/editar" onClick="sendForm('<c:out value="${producto.id }"/>')">
			  </div>
			  <c:set var="count" value="${count+1}" />
			  </c:if>
			  </c:forEach>
			</div>
		</div>
	</c:forEach>
	<form id="dId"method="POST" action="detallesProducto">
	<input id="fid" type="text" name="id" hidden readOnly/>
	</form>


</body>




</html>