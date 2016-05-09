<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="pt">

<head>

	 <title>Portal do Utente</title>

	<!-- Bootstrap Core CSS -->
    <link href="resources/css/bootstrap.min.css" rel="stylesheet">

    <!-- Custom CSS -->
    <link href="resources/css/main.css" rel="stylesheet">

</head>

<body background="resources/gfx/Final2.png">

<div class= "container">    <!-- ROW -->
        <div class= "rowMajor">
            <div class="col-md-11">Portal do Utente</div>
</div>
</div>
 <p id = "frase">Preencha aqui os dados que pretende inserir no Sistema</p>

<div class="registo">
	Escolha a Medição:<br>
  <select id="medidas" onclick="testing()">

 </select>
	<form id="campos" method="post">
  		
	</form>
	<input id = "botao_reg" type="submit">
  	<button id = "botao_voltar">Voltar</button>
</div>
<script src='/resources/js/jquery.js'></script>

<script>
path=window.location.href;
$("#medidas").change(function(){testing();});
	function testing(){
		var e = document.getElementById("medidas");
		var medida=e.options[e.selectedIndex].value;
	$.getJSON( path + "/daoInputs/" + medida, function( data ) {
  var items = [];
  $("#campos").html("");
  $.each( data, function( key, val ) {
    items.push( key + ":\n <input id='campo'" + " value=" + val + "><br>" );
  });
  $("#campos").append(items);
  })
  }
  
  function getthem(){
	$.getJSON( path + "/avaliableMeasures", function( val ) {
  var items = [];
  console.log(val);
   for(i=0;i<val.length;i++){
	   ceas=val[i];
    items.push( "<option id = 'opcao' value=" + ceas + ">" + ceas + "</option>" );
 	}
  $("#medidas").append(items);
  }) 
  }
  
  getthem();
</script>