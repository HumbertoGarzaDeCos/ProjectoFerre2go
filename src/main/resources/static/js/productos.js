function getProducto(id){
	
	var precio=document.getElementById("precioProducto");
	var sku=document.getElementById("skuProducto");
	
	var idProducto= document.getElementById("idProducto");
	var imagen=document.getElementById("imagenVieja");
	var cantidad=document.getElementById("cantidadProducto");
	var detalles=document.getElementById("detalleProducto");
	var nombre=document.getElementById("nombreProducto");
	var categoria=document.getElementById("listaCategoria");
	var stockMinimo=document.getElementById("stockMinimo");
	$.ajax({
    url : "getProducto",
    type: "POST",
 dataType: 'json',
	data: "id="+id,
     success: function(data)
    {		idProducto.value=id;
			nombre.value=data.nombre;
			sku.value=data.sku;
			removeImage();
			imagen.src="data:image/gif;base64,"+data.imagen;
			cantidad.value=data.cantidad;
			detalles.value=data.detalles;
			precio.value=data.precio;
			stockMinimo.value=data.minimo;
			if(data.activo==1){
				document.getElementById("idActivo").checked=true
			}
			else{
				document.getElementById("idDesactivado").checked=true
				
			}
			categoria.value=data.categoria;
    },
    error: function (data)
    {
 
    }
});
}

function checkForm(){
	
	var precio=document.getElementById("precioProducto");
	var sku=document.getElementById("skuProducto");
	var activo=document.getElementById("idActivo");
	var desactivado=document.getElementById("idDesactivado");
	var imagen=document.getElementById("imagenNueva");
	var cantidad=document.getElementById("cantidadProducto");
	var detalles=document.getElementById("detalleProducto");
	var nombre=document.getElementById("nombreProducto");
	var categoria=document.getElementById("listaCategoria");
	var stockMinimo=document.getElementById("stockMinimo");
	var check=false;
	
	if(precio.value==""||sku.value==""||(activo.value.checked==false&&desactivado.value.checked==false)||
	imagen.src==""||cantidad.value==""||detalles.value==""||nombre.value==""||categoria.value==""||
	stockMinimo.value==""){
		check=true;
	}
	if(check){
		
		document.getElementById('textoModal').value="Faltan datos";
 		$('#errorModal').modal('show');
	}
	else{
		
		checkProducto();
	}
	
	
}

function sendFormProducto(){
	$('#confirmarModal').modal('hide');
		$('#loadingModal').modal('show');
		
	var formData = new FormData($('#sendProducto')[0]);
	setTimeout(() => {  
		$.ajax({
    url : "crearProducto",
    type: "POST",
	data: formData,
	processData: false,
contentType: false,
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
function removeImage(){
	var imagenVieja=document.getElementById("imagenVieja");
	var imagenNueva=document.getElementById("imagenNueva");
	var imagenTexto=document.getElementById("imguploadtext");
	var cancelButton=document.getElementById("eliminarImagen");
	cancelButton.setAttribute("hidden",true);
	imagenTexto.value="";
	imagenNueva.src="";
	imagenNueva.setAttribute("hidden",true);
	imagenVieja.removeAttribute("hidden");
}

function guardarProducto(){
	checkForm();
}
function checkProducto(){
	var sku=document.getElementById("skuProducto").value;
	
	var id= document.getElementById("idProducto").value;
		$.ajax({
    url : "checkProducto",
    type: "POST",
	data: "sku="+sku+"&"+"id="+id,
     success: function(data)
    {
	$('#confirmarModal').modal('show');
	document.getElementById("aceptarModal").onclick = function() {sendFormProducto(); }
			
    },
    error: function (data)
    {

	document.getElementById('textoModal').value="El SKU ya existe";
 		$('#errorModal').modal('show');
    }
});
}