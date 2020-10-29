function verProducto(id){
	var div=document.getElementById("divDetalles");
	div.removeAttribute("hidden");
	var nombre=document.getElementById("nombreCliente");
	var telefono=document.getElementById("telefonoCliente");
	var direccion=document.getElementById("direccionCliente");
	var fecha=document.getElementById("fechaCliente");
	var estado=document.getElementById("listaEstado");
	var numPedido=document.getElementById("pedidoCliente");
	
	$.ajax({
    	url : "getPedido",
    	type: "POST",
 		dataType: 'json',
		data: "id="+id,
     	success: function(data){		
			numPedido.value=id;
			nombre.value=data.nombre;
			telefono.value=data.telefono;
			direccion.value=data.direccion;
			fecha.value=data.fecha;
			estado.value=data.estado;
			var total=0;
			$('#tablaproductos tbody').remove();   
			$("#tablaproductos").append("<tbody></tbody>"); 
			for(i in data.productos){
				$("#tablaproductos tbody").append("<tr>" +
      				"<td>" + data.productos[i].nombre
 					+ "</td>" +
        			"<td>" + data.productos[i].cantidad + "</td>" +
					  "<td>" + data.productos[i].precioUnitario + "</td>" +
					  "<td>" + data.productos[i].precioTotal + "</td>" +
					  "</tr>");
			total=total+ data.productos[i].precioTotal ;
			}
			$("#tablaproductos tbody").append("<tr>" +
      				"<td>" + "Total"
 					+ "</td>" +
        			"<td>" +"</td>" +
					  "<td>" +  "</td>" +
					  "<td>" +total+ "</td>" +
					  "</tr>");
    	},
    	error: function (data){
 
    	}
	  });
	
	
	
}

function actualizar(){
	var numPedido=document.getElementById("pedidoCliente").value;
	var estado=document.getElementById("listaEstado").value;
	
	$.ajax({
    	url : "actualizarPedido",
    	type: "POST",
 		dataType: 'json',
		data: "id="+numPedido+"&"+"estado="+estado,
     	success: function(data){		
		document.getElementById('textoModal').value="Exito";
 		$('#errorModal').modal('show');
	setTimeout(() => {window.location.replace('/f2g/pedidos/');}, 1500);
    	},
    	error: function (data){
 			switch(data.status){
			case 418: 
			document.getElementById('textoModal').value="No es posible actualizarlo";
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