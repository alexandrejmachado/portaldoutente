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
    <link href='https://fonts.googleapis.com/css?family=Roboto:400,300,100' rel='stylesheet' type='text/css'>   
    <link rel="stylesheet" href="https://code.jquery.com/ui/1.11.4/themes/smoothness/jquery-ui.css">
  	<script src="https://code.jquery.com/jquery-1.10.2.js"></script>
  	<script src="/resources/js/jquery.flexText.js"></script>
  	<script src="/resources/js/jquery.flexText.min.js"></script>
  	<script src="https://code.jquery.com/ui/1.11.4/jquery-ui.js"></script>

</head>
<style>
.ui-autocomplete { max-height: 200px; overflow-y: auto; overflow-x: hidden;}
</style>

<body background=<c:url value="/resources/gfx/Final2.png"/> />

 <div class="container">
            
            <div class="col-lg-12"> 
                <div class= "rowMajor">
					<div class="col-md-6"><a id = "PortalDoUtente" href="/" style="text-decoration:none; color: white; font-weight:100">Portal do Utente</a></div>
					<div class="col-md-6" id="entrar" style="text-align: right;">${username}</div>
				</div>
            
            </div>
        <nav>
          <ul>
            <li id="login">
              <a id="login-trigger" href="#">
                Opções <span>▼</span>
              </a>
              <div id="login-content">
                <a button href= "/perfil/dados" id = "aaa">Ver Perfil</button></a><br>
                <a button href= "/perfil/verPrivacidades" id = "aaa">Privacidade das Medições</button></a><br>
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
                        
                    <li id="consulta">
                        <a href="/calendario/view">Marcação de Consultas</a>
                    </li>
                    <li id="medicacao2">
                        <a href="/medicacao/view">Registar Medicação</a>
                    </li>
                    <li id="medicao">
                        <a href="/medicoes">Medições</a>
                    </li>
                    <li id="cirurgia">
                        <a href="/cirurgia">Cirurgia</a>
                    </li>
                    <li id="isencao">
                        <a href="/isencao">Pedido de Isenção</a>
                    </li>
                    <li id="upload">
						<a href="/upload">Submeter Exames</a>
					</li>
                    <li style="opacity:0">
						<a></a>
					</li>
					<li>
						<a href="/">Voltar para o Menu</a>
					</li>
                </ul>
            </div>
            
            
    <div id="page-content-wrapper">
    <div  class="container-fluid">
			<form method="post" id="medform">
			<div id="automed">
			<label for="autocomplete">Escolha um Medicamento: </label> <br>
			<input id="autocomplete" name = "nome" style="width: 80%; display:block; font-size: 13px; margin: auto;">
			</div>
			Dose Diária: <br>
			<input id="quant" type="text" name="dosagem" style="display: block; margin: auto;"><br>
			Indicações: <br>
			<textarea id="indicacoes" rows="10" cols="40" name="indicacoes" style="display:block; margin: auto; border: 1px solid #DCDCDC; "></textarea><br>
			</form>
			<input id="medicacao" type="submit" onclick="medicamentos()" style = "margin:auto;">
			
			
					<p id="texto7">Consulte abaixo as suas medicações</p>
			
			        <table id="tabela_med">
			
			            <tr id = "texto7">
			                <td>NOME</td>
			                <td>DOSE</td> 
			                <td>INDICAÇÕES</td>
			                <td>Renovar</td>
			                <td>Apagar</td>
			                <td>Download</td>
			            </tr>
			
			         <c:forEach items="${lista}" var="medicacao">
			    <tr id = "texto7">
			    	<td> <c:out value="${medicacao.nomeMedicamento }"/> </td>
			        <td><c:out value="${medicacao.dose}"/></td>
			        <td><c:out value="${medicacao.indicacoes}"/></td>
			        <td><c:choose>
    <c:when test="${medicacao.checkMedicacao()=='aceite'}">
        <p>Renovado</p> 
    </c:when>    
    <c:otherwise>
    <c:choose>
    <c:when test="${medicacao.checkMedicacao()=='caducado' }">
    	<button id = "renovar" value="${medicacao.getId()}" onclick="renovar(${medicacao.id})"> Validar </button> 
    </c:when>
    <c:otherwise>
    <c:if test="${medicacao.estado=='true'}">
        <button id = "renovar" value="${medicacao.getId()}" onclick="renovar(${medicacao.id})"> Pedir Renovacao </button> 
    </c:if>
    <c:if test="${medicacao.estado=='false' }">
        <p>À espera do Médico</p>
    </c:if>
    </c:otherwise>
    </c:choose>
    </c:otherwise>
</c:choose></td>
			        <td><button id = "apagar" value="${medicacao.getId() }" onclick="apagar(${medicacao.id})"> Apagar </button></td>
			        <td>
		    		
			    	<c:if test="${medicacao.checkMedicacao()=='aceite'}">
        				<form action="verReceita" method="POST">
			    		<input type="text" name="medicacaoID" style="display:none" value="${medicacao.id}">
			    		<input type="submit" value="Obter Receita"></form>
    				</c:if>
		    		</td>
		    		
			        
			        
			    </tr>
				</c:forEach>
			    </table>
			    
	</div> <!--  containerfluid -->
	</div>  <!--  page content wrapper -->
	</div> <!--  wrapper -->
    
    

      


<script>
$(document).ready(function(){
	var link="<c:url value="/resources/meds3.json"> </c:url>";
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
		$.post(path, $("form").serialize()).done(function( data ) {
			if (data==true){window.location.reload();}
			
			else {alert(data)}}

		);

}

function renovar(id)
{
	path="https://" + window.location.host + "/medicacao/renovar";
	$.post(path, {'id':id}).done(function( data ) {
		if (data=="True"){window.location.reload();}
		else {
		if (data=="Pendente") {alert("O seu médico foi notificado para renovar a sua medicacao"); window.location.reload();}
		
		else {alert("renovacao falhada, ainda e muito cedo para pedir")}}}

	);
	
	}
function apagar(id)
{
	path="https://" + window.location.host + "/medicacao/apagar";
	$.post(path, {'id':id}).done(function( data ) {
		if (data==true){window.location.reload();}
		
		else {alert("falha algures, culpe o Justino")}}

	);
	}
</script>

    <!-- Menu Toggle Script -->
<script>
     $(document).ready(function(){
    	 $(function () {
    		    $('textarea').flexText();
    		});
   $('#login-trigger').click(function(){
     $(this).next('#login-content').slideToggle();
     $(this).toggleClass('active');          
    
     if ($(this).hasClass('active')) $(this).find('span').html('&#x25B2;')
       else $(this).find('span').html('&#x25BC;')
     })
 });
 </script>
