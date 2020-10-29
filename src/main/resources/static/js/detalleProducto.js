function cambiarCategoria(){
	document.getElementById('productoDiv').setAttribute("hidden",true);
	document.getElementById('categoriaDiv').removeAttribute("hidden");

	document.getElementById('categoriaBoton').setAttribute("class","font-weight-bold");
	document.getElementById('productoBoton').removeAttribute("class");
}
function cambiarProducto(){
	document.getElementById('categoriaDiv').setAttribute("hidden",true);
	document.getElementById('productoDiv').removeAttribute("hidden");
	document.getElementById('productoBoton').setAttribute("class","font-weight-bold");
	document.getElementById('categoriaBoton').removeAttribute("class");
}


	$(document).ready(
			function() {
				$(document).on(
						'change',
						'.btn-file :file',
						function() {
							var input = $(this), label = input.val().replace(
									/\\/g, '/').replace(/.*\//, '');
							input.trigger('fileselect', [ label ]);
						});
				$('#tablaproductos').DataTable();
				
				$('#listaCategoria').trigger("chosen:updated");
				$('.btn-file :file').on(
						'fileselect',
						function(event, label) {

							var input = $(this).parents('.input-group').find(
									':text'), log = label;

							if (input.length) {
								input.val(log);
							} else {
								if (log)
									alert(log);
							}

						});
				function readURL(input) {
					if (input.files && input.files[0]) {
						var reader = new FileReader();

						reader.onload = function(e) {
							$('#imagenNueva').attr('src', e.target.result);
						}

						reader.readAsDataURL(input.files[0]);
					}
				}

				$("#imgInp").change(
						function() {
							readURL(this);
							document.getElementById('imagenVieja')
									.setAttribute("hidden", true);
							document.getElementById('imagenNueva')
									.removeAttribute("hidden");
							document.getElementById('eliminarImagen')
									.removeAttribute("hidden")
						});
			});