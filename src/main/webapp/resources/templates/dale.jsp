<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js" integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1" crossorigin="anonymous"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js" integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM" crossorigin="anonymous"></script>

<nav class="navbar navbar-expand-lg navbar-dark bg-dark">
  <a class="navbar-brand"  href="">Ferreteria Gamez</a>
  <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
    <span class="navbar-toggler-icon"></span>
  </button>

  <div class="collapse navbar-collapse" id="navbarSupportedContent">
    <ul class="navbar-nav mr-auto">
      <li id="catalogoHolder" class="nav-item">
        <a class="nav-link" href="/f2g">Catalogo <span class="sr-only"></span></a>
      </li>
       <li id="modificaHolder"  class="nav-item ">
        <a class="nav-link" href="/f2g/modificar">Modificación de catalogo <span class="sr-only"></span></a>
      </li>
       <li id="proveedorHolder" class="nav-item ">
        <a class="nav-link" href="/f2g/proveedores/">Administración de proveedores <span class="sr-only"></span></a>
      </li>
       <li id="abastecimientoHolder" class="nav-item ">
        <a class="nav-link" href="/f2g/abastecimiento/">Administración de abastecimiento <span class="sr-only"></span></a>
      </li>
       <li id="pedidosHolder" class="nav-item ">
        <a class="nav-link" href="/f2g/pedidos/">Pedidos <span class="sr-only"></span></a>
      </li>
    </ul>   
  </div>
 
</nav>
 <style>
 
  </style>
</head>
<body>
<div class="modal fade" id="errorModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalCenterTitle" aria-hidden="true">
  <div class="modal-dialog modal-dialog-centered" role="document">
    <div class="modal-content">
      <div class="modal-header">        
      </div>
      <div class="modal-body">
       <input type="text" id="textoModal" value="Ejemplo Modal" style="border:none" readOnly>
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
      </div>
    </div>
  </div>
</div>
<div class="modal fade" id="confirmarModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalCenterTitle" aria-hidden="true">
  <div class="modal-dialog modal-dialog-centered" role="document">
    <div class="modal-content">
      <div class="modal-header">        
      </div>
      <div class="modal-body">
       <input type="text" id="textoModal" value="Esta seguro de los datos?" style="border:none" readOnly>
      </div>
      <div class="modal-footer">
      <button type="button" id="aceptarModal"class="btn btn-primary"  >Aceptar</button>
        <button type="button" class="btn btn-secondary" data-dismiss="modal">Cerrar</button>
      </div>
    </div>
  </div>
</div>
<div class="modal fade" id="loadingModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalCenterTitle" data-keyboard="false" data-backdrop="static"aria-hidden="true">
  <div class="modal-dialog modal-dialog-centered" role="document">
    <div class="modal-content" >    
      <div class="modal-body">
      <img src="/f2g/loading.gif" style=" display: inline; float: none;height: 100%; width: 100%;">
      </div>
    </div>
  </div>
</div>
</body> 
</html>