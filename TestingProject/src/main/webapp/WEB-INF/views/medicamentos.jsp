<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="pt">

<head>

	 <title>Portal do Utente</title>

	<!-- Bootstrap Core CSS -->
    <link href="/resources/css/bootstrap.min.css" rel="stylesheet">

    <!-- Custom CSS -->
    <link href="/resources/css/main.css" rel="stylesheet">
    
    <link rel="stylesheet" href="https://code.jquery.com/ui/1.11.4/themes/smoothness/jquery-ui.css">
  	<script src="https://code.jquery.com/jquery-1.10.2.js"></script>
  	<script src="https://code.jquery.com/ui/1.11.4/jquery-ui.js"></script>

</head>
<style>
.ui-autocomplete { max-height: 200px; overflow-y: auto; overflow-x: hidden;}
</style>
<body background="/resources/gfx/Final2.png">

 <div class="container">
            
            <div class="col-lg-12"> 
                <div class= "rowMajor">
                	<div id="divisento"></div>
					<div class="col-md-11">Portal do Utente</div>
					<div class="col-md-1" id="entrar">${username}</div>
				</div>
            
            </div>
        <nav>
          <ul>
            <li id="login">
              <a id="login-trigger" href="#">
                Opções <span>▼</span>
              </a>
              <div id="login-content">
                <a button href= "/perfil" id = "aaa">Ver Perfil</button></a><br>
                <a button href= "/logout" id ="aaa">Logout</button></a>
              </div>                     
            </li>
          </ul>
        </nav>
        <div class="col-lg-12" id="caixaGig">  <!--  -->
	
		<div id="wrapper">

			<!-- Sidebar -->
			<div id="sidebar-wrapper">
				<ul class="barra">
						
					<li>
						<a href="/calendario">Marcação de Consultas</a>
					</li>
					<li>
						<a href="#">Marcações Confirmadas</a>
					</li>
					<li>
						<a href="#">Renovar Receita</a>
					</li>
					<li>
						<a href="/medicacao/view">Registar Medicação</a>
					</li>
					<li>
						<a href="/medicoes">Medições</a>
					</li>
					<li>
						<a href="#">Agregado Familiar</a>
					</li>
					<li>
						<a href="/isencao">Pedido de Isenção</a>
					</li>
				</ul>
			</div>
    </div>
<form method="post" id="medform" >
<div id="automed" style="display:flex;align-items:center;">
<label for="autocomplete">Escolha um Medicamento: </label>
<input id="autocomplete" name = "nome" style="width: 40%;">
</div>
Quantidade: 
<input id="quant" type="text" name="dosagem"><br>
Indicacoes: <br>
<textarea id="indicacoes" rows="10" cols="60" name="indicacoes"></textarea><br>
</form>
<input id="medicacao" type="submit" onclick="medicamentos()">


<script>
$(document).ready(function(){
	var link="<c:url value="/resources/meds.json"> </c:url>";
$.get(link).done(function( tags ) {
  $( "#autocomplete" ).autocomplete({
    source: function( request, response ) {
            var matcher = new RegExp( "^" + $.ui.autocomplete.escapeRegex( request.term ), "i" );
            response( $.grep( tags, function( item ){
                return matcher.test( item );
            }) );
        }
  });
  });
});
</script>
<script>
function medicamentos()
{
		path="https://" + window.location.host + "/medicacao/inserir";
		$('.registo').append($('<img>',{id:'theImg',src:'resources/gfx/loadingGif.gif',width: '50', height: '50'}));
		$.post(path, $("form").serialize()).done(function( data ) {
			if (data[0]==true){window.location.replace(path);}
			else {alert(data[0]);$("#theImg").remove();}}
		);

}
</script>
