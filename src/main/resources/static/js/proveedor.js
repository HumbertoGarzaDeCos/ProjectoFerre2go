function getProveedor(id){
	var nombre= document.getElementById('nombreProveedor');
	var telefono=document.getElementById('telefonoProveedor');
	
	$.ajax({
    url : "getProveedor",
    type: "POST",
 dataType: 'json',
	data: "id="+id,
     success: function(data)
    {		
		nombre.value=data.nombre;
		telefono.value=data.telefono;
		$('#tablaproductos tbody').remove();   
	$("#tablaproductos").append("<tbody></tbody>"); 

for(i in data.productos){
	
	$("#tablaproductos tbody").append("<tr>" +
      
	"<td>" + data.productos[i].sku
 + "</td>" +
        "<td>" + data.productos[i].nombre + "</td>" +
  "<td>" + data.productos[i].precio1 + "</td>" +
  "<td>" + data.productos[i].precio2 + "</td>" +
  "<td>" + data.productos[i].precio3 + "</td>" +
        "<td>" + data.productos[i].activo
 + "</td>" +
 
        "<td>" + 
"<button type='button' onClick='productDelete(this)' class='btn btn-primary'><svg width='1em' height='1em' viewBox='0 0 16 16'	class='bi bi-trash-fill' fill='currentColor'xmlns='http://www.w3.org/2000/svg'>  <path fill-rule='evenodd'd='M2.5 1a1 1 0 0 0-1 1v1a1 1 0 0 0 1 1H3v9a2 2 0 0 0 2 2h6a2 2 0 0 0 2-2V4h.5a1 1 0 0 0 1-1V2a1 1 0 0 0-1-1H10a1 1 0 0 0-1-1H7a1 1 0 0 0-1 1H2.5zm3 4a.5.5 0 0 1 .5.5v7a.5.5 0 0 1-1 0v-7a.5.5 0 0 1 .5-.5zM8 5a.5.5 0 0 1 .5.5v7a.5.5 0 0 1-1 0v-7A.5.5 0 0 1 8 5zm3 .5a.5.5 0 0 0-1 0v7a.5.5 0 0 0 1 0v-7z' /></svg></button>"+ "</td>" +

        "</tr>");
}
    },
    error: function (data)
    {
 
    }
});
	
}
function productDelete(ctl) {
	
$(ctl).parents("tr").remove();
}

function agregarProducto(){
	var precio1=document.getElementById("precio1").value;
	var precio2=document.getElementById("precio2").value;
	var precio3=document.getElementById("precio3").value;
	var id=document.getElementById("listaProductos").value;
	var nombreProducto=$( "#listaProductos option:selected" ).text();;
	var check=true;
	$('#tablaproductos tr').each(function() {
    var product= $(this).find("td:nth-child(2)").html();
	if(nombreProducto==product||(precio1==""&&precio2==""&&precio3=="")){
		check=false;
	}    
});
if(check){
	$.ajax({
    url : "/f2g/getProducto",
    type: "POST",
 dataType: 'json',
	data: "id="+id,
     success: function(data)
    {		
	var activo="inactivo";
	if (data.activo==1){activo="activo"}
	$("#tablaproductos tbody").append("<tr>" +
      
	"<td>" + data.sku
 + "</td>" +
        "<td>" + data.nombre + "</td>" +
  "<td>" + precio1 + "</td>" +
  "<td>" + precio2 + "</td>" +
  "<td>" + precio3 + "</td>" +
        "<td>" + activo
 + "</td>" +
 
        "<td>" + 
"<button type='button' onClick='productDelete(this)' class='btn btn-primary'><svg width='1em' height='1em' viewBox='0 0 16 16'	class='bi bi-trash-fill' fill='currentColor'xmlns='http://www.w3.org/2000/svg'>  <path fill-rule='evenodd'd='M2.5 1a1 1 0 0 0-1 1v1a1 1 0 0 0 1 1H3v9a2 2 0 0 0 2 2h6a2 2 0 0 0 2-2V4h.5a1 1 0 0 0 1-1V2a1 1 0 0 0-1-1H10a1 1 0 0 0-1-1H7a1 1 0 0 0-1 1H2.5zm3 4a.5.5 0 0 1 .5.5v7a.5.5 0 0 1-1 0v-7a.5.5 0 0 1 .5-.5zM8 5a.5.5 0 0 1 .5.5v7a.5.5 0 0 1-1 0v-7A.5.5 0 0 1 8 5zm3 .5a.5.5 0 0 0-1 0v7a.5.5 0 0 0 1 0v-7z' /></svg></button>"+ "</td>" +

        "</tr>");

    },
    error: function (data)
    {
 
    }
});
	}
	else{
		document.getElementById('textoModal').value="No ingresaste precio";
 		$('#errorModal').modal('show');
	}
}
function sendProveedor(){
		$('#confirmarModal').modal('hide');
		$('#loadingModal').modal('show');
	var id=document.getElementById('proveedorId').value;
	var nombre=document.getElementById('nombreProveedor').value;
	var telefono=document.getElementById('telefonoProveedor').value;
	var productos="";
	var precio1="";
	var precio2="";
	var precio3="";
	$('#tablaproductos tr').each(function() {
 	 	productos=productos+$(this).find("td:nth-child(1)").html()+"|";
		if($(this).find("td:nth-child(3)").html()!=""){
			precio1=precio1+$(this).find("td:nth-child(3)").html()+"|";
		}
		
		else{
			precio1=precio1+"0"+"|";
		}
		if($(this).find("td:nth-child(4)").html()!=""){
			precio2=precio2+$(this).find("td:nth-child(4)").html()+"|";
		}
		
		else{
			precio2=precio2+"0"+"|";
		}
		if($(this).find("td:nth-child(5)").html()!=""){
			precio3=precio3+$(this).find("td:nth-child(5)").html()+"|";
		}
		
		else{
			precio3=precio3+"0"+"|";
		}
});
		setTimeout(() => {$.ajax({
    url : "crearProveedor",
    type: "POST",
 dataType: 'json',
	data: "nombre="+nombre+"&"+"telefono="+telefono+"&"+"productos="+productos+"&"+"precio1="+precio1+"&"+"precio2="+precio2+"&"+"precio3="+precio3,
     success: function(data)
    {		
	$('#loadingModal').modal('hide');
document.getElementById('textoModal').value="Exito";
 		$('#errorModal').modal('show');
	setTimeout(() => {window.location.replace('/f2g/proveedores/');}, 1500);
	
}, 
	error: function (data)
    {
 $('#loadingModal').modal('hide');
	document.getElementById('textoModal').value="Hubo un error en el servidor";
 		$('#errorModal').modal('show');    
    }
});}, 1000);
	
}
function chekcForm(){
	var nombre=document.getElementById('nombreProveedor').value;
	var telefono=document.getElementById('telefonoProveedor').value;
	if(nombre!=""&&telefono!=""){
		$('#confirmarModal').modal('show');
	document.getElementById("aceptarModal").onclick = function() { sendProveedor(); }
	}
	else{
			document.getElementById('textoModal').value="Faltan datos";
 		$('#errorModal').modal('show');
	}
	
	
}
