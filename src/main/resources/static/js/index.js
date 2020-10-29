function sendForm(id){
	var forma= document.getElementById('dId');
	var ins= document.getElementById('fid');
	ins.value=id;
	forma.submit();
	/*$.ajax({
    url : "detalles",
    type: "POST",
	data: "id="+id,
     success: function(data)
    {
	$(this).html( data );
    },
    error: function (data)
    {
 
    }
});*/



}

function openDiv(id){
	var div=document.getElementById('div'+id);
	var open=document.getElementById('open'+id);
	var close=document.getElementById('close'+id);
	
	div.removeAttribute("hidden");
	close.removeAttribute("hidden");
	open.setAttribute("hidden",true);	
}
function closeDiv(id){
	var div=document.getElementById('div'+id);
	var open=document.getElementById('open'+id);
	var close=document.getElementById('close'+id);
	
	div.setAttribute("hidden",true);
	open.removeAttribute("hidden");
	close.setAttribute("hidden",true);	
}