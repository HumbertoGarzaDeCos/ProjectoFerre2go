function checkForm(){
	
}


function cambiarDetalles() {
		document.getElementById('altaDiv').setAttribute("hidden", true);
		document.getElementById('detallesDiv').removeAttribute("hidden");

		document.getElementById('detalleBoton').setAttribute("class",
				"font-weight-bold");
		document.getElementById('altaBoton').removeAttribute("class");
	}
	function cambiarAlta() {
		document.getElementById('detallesDiv').setAttribute("hidden", true);
		document.getElementById('altaDiv').removeAttribute("hidden");

		document.getElementById('altaBoton').setAttribute("class",
				"font-weight-bold");
		document.getElementById('detalleBoton').removeAttribute("class");
	}
function productosProveedor(){
	$('#tablaproductoselegidos tbody').remove(); 
	$("#tablaproductoselegidos").append("<tbody></tbody>"); 
	var proveedor=document.getElementById("listaProveedores").value;
	$.ajax({
    	url : "getProductosProveedor",
    	type: "POST",
 		dataType: 'json',
		data: "id="+proveedor,
     	success: function(data){		
			$('#listaProductos').empty();
			$('#listaProductos').append('<option selected value="0">Selecciona...</option>');
			for(var i=0;i<data.length;i++){
				$('#listaProductos').append('<option value="'+data[i].sku+'" ">'+data[i].nombre+'</option>');
			}
			
    	},
    	error: function (data){
 
    	}
	  });
	
}
function agregarProducto(){
	var id=document.getElementById("listaProductos").value;
	var nombreProducto=$( "#listaProductos option:selected" ).text();;
	var cantidad=document.getElementById("cantidadProducto").value;
	var precio=document.getElementById("precioProducto").value;
	var check=true;
	$('#tablaproductoselegidos tr').each(function() {
    var product= $(this).find("td:nth-child(1)").html();
	if(id==product){
		check=false;
	}    
});
	if(check&&cantidad!=""&&precio!=""&&nombreProducto!="Selecciona..."){
		$('#tablaproductoselegidos tbody').append(
			"<tr>"+
			"<td>"+id+"</td>"+
			"<td>"+nombreProducto+"</td>"
			+"<td>"+cantidad+"</td>"
			+"<td>"+precio+"</td>"
			+"<td>"+'<button onclick="productDelete(this)" type="button" class="btn btn-primary">	<svg width="1em" height="1em" viewBox="0 0 16 16" class="bi bi-trash-fill" fill="currentColor"	xmlns="http://www.w3.org/2000/svg">  <path fill-rule="evenodd"			d="M2.5 1a1 1 0 0 0-1 1v1a1 1 0 0 0 1 1H3v9a2 2 0 0 0 2 2h6a2 2 0 0 0 2-2V4h.5a1 1 0 0 0 1-1V2a1 1 0 0 0-1-1H10a1 1 0 0 0-1-1H7a1 1 0 0 0-1 1H2.5zm3 4a.5.5 0 0 1 .5.5v7a.5.5 0 0 1-1 0v-7a.5.5 0 0 1 .5-.5zM8 5a.5.5 0 0 1 .5.5v7a.5.5 0 0 1-1 0v-7A.5.5 0 0 1 8 5zm3 .5a.5.5 0 0 0-1 0v7a.5.5 0 0 0 1 0v-7z" /></svg>	</button>'
			+"</td>"
			+"</tr>"
		);
	}
	else{
		document.getElementById('textoModal').value="Faltan datos del producto";
 		$('#errorModal').modal('show');
	}
}
function precioProveedor(){
	var producto=document.getElementById("listaProductos").value;
	var proveedor=document.getElementById("listaProveedores").value;
	$.ajax({
    	url : "getPrecioProducto",
    	type: "POST",
 		dataType: 'json',
		data: "id="+proveedor+"&"+"producto="+producto,
     	success: function(data){		
			$('#listaPrecio').empty();
			$('#listaPrecio').append('<option>'+data.precio1+'</option>');
			$('#listaPrecio').append('<option>'+data.precio2+'</option>');
			$('#listaPrecio').append('<option>'+data.precio3+'</option>');
			
			
			
    	},
    	error: function (data){
 
    	}
	  });
}
function detallesSolicitud(id){
	var numPedido=document.getElementById("detallePedido");
	var proveedor=document.getElementById("detalleNombre");
	var telefono= document.getElementById("detalleTelefono");
	var estadoSolicitud=document.getElementById("estadosolicitudDetalles");
	$.ajax({
    	url : "getSolicitud",
    	type: "POST",
 		dataType: 'json',
		data: "id="+id,
     	success: function(data){		
			
			document.getElementById('detalleBoton').removeAttribute("hidden");
			cambiarDetalles();
			$('#tabladetallesol tbody').remove();   
			$("#tabladetallesol").append("<tbody></tbody>"); 
			numPedido.value=data.id;
			proveedor.value=data.nombre;
			telefono.value=data.telefono;
			estadoSolicitud.value=data.estado;
			for (var i=0;i<data.productos.length;i++){
				$('#tabladetallesol tbody').append(
				"<tr>"+
				"<td>"+data.productos[i].nombreProducto+"</td>"
				+"<td>"+data.productos[i].cantidad+"</td>"
				+"<td>"+data.productos[i].precio+"</td>"
				+"</tr>"
		);
			}
			
			
			
    	},
    	error: function (data){
 
    	}
	  });
	
	
}
function crearSolicitud(){
	var proveedor=document.getElementById("listaProveedores").value;
	var productos="";
	var cantidades="";
	var precios="";
	var estado=document.getElementById("estadoNuevo").value;
	$('#tablaproductoselegidos tr').each(function() {
		productos=productos+$(this).find("td:nth-child(1)").html()+"|";
     	cantidades=cantidades+$(this).find("td:nth-child(3)").html()+"|";
		precios=precios+$(this).find("td:nth-child(4)").html()+"|";
});
	if(productos!="undefined|"&&proveedor!="0"&&productos!=""){
		
		$('#confirmarModal').modal('show');
	document.getElementById("aceptarModal").onclick = function() {
		$('#confirmarModal').modal('hide');
		$('#loadingModal').modal('show');
		
		setTimeout(() => {$.ajax({
    	url : "crearSolicitud",
    	type: "POST",
 		dataType: 'json',
		data: "id="+proveedor+"&"+"productos="+productos+"&"+"estado="+estado
		+"&"+"cantidades="+cantidades
		+"&"+"precios="+precios,
     	success: function(data){		
			document.getElementById('textoModal').value="Exito";
 		$('#errorModal').modal('show');
	setTimeout(() => {window.location.replace('/f2g/abastecimiento/');}, 1500);
    	},
    	error: function (data){
 $('#loadingModal').modal('hide');
	document.getElementById('textoModal').value="Hubo un error en el servidor";
 		$('#errorModal').modal('show');   
    	}
	  });}, 1000);
 }


	}
	else{
		document.getElementById('textoModal').value="Faltan datos";
 		$('#errorModal').modal('show');
	}
	
}
function actualizarSolicitud(){
	var id=document.getElementById("detallePedido").value;
	var estado=document.getElementById("estadosolicitudDetalles").value;
	if(estado!="0"){
		$.ajax({
    	url : "actualizarSolicitud",
    	type: "POST",
 		dataType: 'json',
		data: "id="+id+"&"+"estado="+estado,
     	success: function(data){		
			document.getElementById('textoModal').value="Exito";
 		$('#errorModal').modal('show');
	setTimeout(() => {window.location.replace('/f2g/abastecimiento/');}, 1500);
    	},
    	error: function (data){
			switch(data.status){
				case 418: 
	document.getElementById('textoModal').value="No es posible actualizarla";
 		$('#errorModal').modal('show');    
				
				break;
				case 500:
				document.getElementById('textoModal').value="Hubo un error en el servidor";
 		$('#errorModal').modal('show');
				break;
				
			}   		

}
	  });
}
}
function productDelete(ctl) {
	
$(ctl).parents("tr").remove();
}