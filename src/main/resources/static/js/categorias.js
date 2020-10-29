function getCategoria(){
	var nombre=document.getElementById("nombreCategoria");
	var tablaProductos=document.getElementById("tablaproductoscat");
	
	$('#tablaproductoscat tbody').remove();
	$.ajax({
    url : "getProdCat",
    type: "POST",
 dataType: 'json',
	data: "nombre="+nombre.value,
     success: function(data)
    {		
	
	if(data.activo==1){
		document.getElementById("idCatActivo").checked=true;
	}
	else{document.getElementById("idCatInactivo").checked=true;}
		$('#tablaproductoscat tbody').remove();   
$("#tablaproductoscat").append("<tbody></tbody>"); 

for(i in data.productos){
	var activo="inactivo";
	if (data.productos[i].activo==1){activo="activo"}
	$("#tablaproductoscat tbody").append("<tr>" +
	"<td style='display:none;'>" + data.productos[i].sku
 + "</td>" +
        "<td>" + data.productos[i].nombre + "</td>" +
        "<td>" + activo
 + "</td>" +
 
        "<td>" + 
"<button type='button' onClick='productDelete(this)' class='btn btn-primary'><svg width='1em' height='1em' viewBox='0 0 16 16'	class='bi bi-trash-fill' fill='currentColor'xmlns='http://www.w3.org/2000/svg'>  <path fill-rule='evenodd'd='M2.5 1a1 1 0 0 0-1 1v1a1 1 0 0 0 1 1H3v9a2 2 0 0 0 2 2h6a2 2 0 0 0 2-2V4h.5a1 1 0 0 0 1-1V2a1 1 0 0 0-1-1H10a1 1 0 0 0-1-1H7a1 1 0 0 0-1 1H2.5zm3 4a.5.5 0 0 1 .5.5v7a.5.5 0 0 1-1 0v-7a.5.5 0 0 1 .5-.5zM8 5a.5.5 0 0 1 .5.5v7a.5.5 0 0 1-1 0v-7A.5.5 0 0 1 8 5zm3 .5a.5.5 0 0 0-1 0v7a.5.5 0 0 0 1 0v-7z' /></svg></button>"+ "</td>" +

        "</tr>");
}
			
    },
    error: function (data)
    {
 $("#tablaproductoscat").append("<tbody></tbody>"); 
    }
});
}
function agregarProducto(){
	var id=document.getElementById("listaProductos").value;
	var nombreProducto=$( "#listaProductos option:selected" ).text();;
	var check=true;
	$('#tablaproductoscat tr').each(function() {
    var product= $(this).find("td:nth-child(2)").html();
	if(nombreProducto==product){
		check=false;
	}    
});
if(check){
	$.ajax({
    url : "getProducto",
    type: "POST",
 dataType: 'json',
	data: "id="+id,
     success: function(data)
    {		
	var activo="inactivo";
	if (data.activo==1){activo="activo"}
	$("#tablaproductoscat tbody").append("<tr>" +
	"<td style='display:none;'>" + data.sku
 + "</td>" +
        "<td>" + data.nombre + "</td>" +
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
}
function sendCategoria(){
	$('#confirmarModal').modal('hide');
		$('#loadingModal').modal('show');
	var nombre=document.getElementById('nombreCategoria').value;
	var activo;
	if(document.getElementById('idCatInactivo').checked==true){
		activo=0;
	}
	
	else{
		activo=1;	
	}
	var productos="";
	$('#tablaproductoscat tr').each(function() {
   	 var product= $(this).find("td:first").html();
	productos=productos+product+"|"
	});
	var form="nombreCategoria="+nombre+"&"+"activoCategoria="+activo+"&"+"productosCategoria="+productos;
	setTimeout(() => {  
	$.ajax({
    url : "crearCategoria",
    type: "POST",
 dataType: 'json',
	data: form,
     success: function(data)
    {		
	$('#loadingModal').modal('hide');
document.getElementById('textoModal').value="Exito";
 		$('#errorModal').modal('show');
	setTimeout(() => {window.location.replace('/f2g/modificar');}, 1500);
    },
    error: function (data)
    {
 $('#loadingModal').modal('hide');
	document.getElementById('textoModal').value="Hubo un error en el servidor";
 		$('#errorModal').modal('show');  
    }
});}, 1000);
}
function checkNombre(){
	var nombre=document.getElementById('nombreCategoria').value;
	if(nombre!=""){
		$('#confirmarModal').modal('show');
	document.getElementById("aceptarModal").onclick = function() {sendCategoria(); }
	}
	else{
			document.getElementById('textoModal').value="Falta el nombre";
 		$('#errorModal').modal('show');
	}
	
}
function productDelete(ctl) {
	
$(ctl).parents("tr").remove();
}