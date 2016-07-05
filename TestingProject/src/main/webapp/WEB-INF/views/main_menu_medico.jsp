<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<script src="https://code.jquery.com/jquery-1.10.2.js"></script>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Início - Médico</title>
<link href='https://fonts.googleapis.com/css?family=Roboto:400,300,100' rel='stylesheet' type='text/css'>
<!-- Bootstrap Core CSS -->
    <link href=<c:url value="/resources/css/bootstrap.min.css" ></c:url> rel="stylesheet">

    <!-- Custom CSS -->
    <link href=<c:url value="/resources/css/main.css" ></c:url> rel="stylesheet">
    <script src='<c:url value="/resources/js/jquery.js"></c:url>'></script>
</head>
<body background=<c:url value="/resources/gfx/Final2.png"/> />
	
		<div class="container">
            
            <div class="col-lg-12"> 
                <div class= "rowMajor">
                    <div id="divisento"></div>
                    <div class="col-md-11">Número Médico: ${username}</div>
                    <div class="col-md-1" id="entrar"><a style="color:white; text-decoration:none;" href="/medico/logout">Sair </a></div>
                </div>
            
            </div>
    	</div>
    	
    	<div id = "medico" style="text-align: center; width:90%; font-family:'Roboto',Arial, sans-serif; margin-left:5%;">
<ul class="tab">
  <li style="width: 33%;"><a href="#" class="tablinks active" onclick="change(event, 'ConsultaMed')">Confirmação de Consultas</a></li>
  <li style="width: 33%;"><a href="#" class="tablinks" onclick="change(event, 'DadosMed')">Consultar dados do Utente</a></li>
  <li style="width: 33%;"><a href="#" class="tablinks" onclick="change(event, 'MedicacaoMed')">Renovação de Medicacao</a></li>
</ul>

	<div id="ConsultaMed" class="tabcontent" style="display: block;">
	   <table style="width: 100%;">
			<tr style="width:100%; margin-top: 10px; background-color:#ddd;">
				<td style="width:16%; text-align: center;">Nome</td>
				<td style="width:16%; text-align: center;">Número do Utente</td>
				<td style="width:16%; text-align: center;">Data</td>
				<td style="width:16%; text-align: center;">Número da Instituição</td>
				<td style="width:16%; text-align: center;">Estado</td>
				<td style="width:16%; text-align: center;">Acção</td>
			</tr>
			<c:forEach items="${listaParaTratar}" var="mu">
		   		<tr id = "texto_tab" style="width:100%;">
		   			<c:set var="dataTratada" value="${fn:substring(mu.getData(), 0, 16)}" />
		   			<td id="texto7" style="width:16%; text-align: center;"> <c:out value="${mu.getNomeUtente()}"/> </td>
		    		<td id="texto7" style="width:16%; text-align: center;"> <c:out value="${mu.getNumUtente()}"/> </td>
		    		<td id="texto7" style="width:16%; text-align: center;"> <c:out value="${dataTratada}"/> </td>
		    		<td id="texto7" style="width:16%; text-align: center;"> <c:out value="${mu.getIdInstituicao()}"/> </td>
		    		<c:if test="${mu.isConfirmada() == 'false'}">
						<td id="texto7" style="width:16%; text-align: center;"> <c:out value="Pendente"/> </td>
		  				<td id="texto7" style="width:16%; text-align: center;"><button id="botao_med" onclick="confirmar(${mu.getId()})"> <c:out value="Confirmar"></c:out> </button> <button id="botao_med" onclick="rejeitar(${mu.getId()})"> <c:out value="Rejeitar"></c:out> </button></td>
		    		</c:if>
		    		
		    		<c:if test="${mu.isConfirmada() == 'true'}">
		    			<td id="texto7" style="width:16%; text-align: center;"> <c:out value="Confirmada"/> </td>
		    			<td id="texto7" style="width:16%; text-align: center;"><button id="botao_med" onclick="rejeitar(${mu.getId()})"> <c:out value="Rejeitar"></c:out> </button></td>
		    		</c:if>
		  
		    	</tr>
			</c:forEach>
		</table>
	</div>

	<div id="DadosMed" class="tabcontent">
	   <table style="width: 100%;">
			<tr style="width:100%; margin-top: 10px;  background-color:#ddd;">
				<td style="width:16%; text-align: center;">Nome</td>
				<td style="width:16%; text-align: center;">Número do Utente</td>
				<td style="width:16%; text-align: center;">Email</td>
				<td style="width:16%; text-align: center;">Número de Telemóvel</td>
				<td style="width:16%; text-align: center;">Acção</td>		
			</tr>
			<c:forEach items="${listaUtentes}" var="ut">
				<tr id = "texto_tab" style="width:100%;">
					<td id="texto7" style="width:16%; text-align: center;"> <c:out value="${ut.getNome()}"/> </td>
					<td id="texto7" style="width:16%; text-align: center;"> <c:out value="${ut.getNumUtente()}"/> </td>
					<td id="texto7" style="width:16%; text-align: center;"> <c:out value="${ut.getEmail()}"/> </td>
					<td id="texto7" style="width:16%; text-align: center;"> <c:out value="${ut.getTelemovel()}"/> </td>
					<td id="texto7" style="width:16%; text-align: center;"><button id="botao_med" onclick="verDados(${ut.getNumUtente()})"> <c:out value="Ver Dados"></c:out> </button></td>
				</tr>
			</c:forEach>
		</table>
	</div>
	
	<div id="MedicacaoMed" class="tabcontent">
	   	   <table style="width: 100%;">
			<tr style="width:100%; margin-top: 10px;  background-color:#ddd;">
				<td style="width:20%; text-align: center;">Nome</td>
				<td style="width:20%; text-align: center;">Número do Utente</td>
				<td style="width:20%; text-align: center;">Medicamento</td>
				<td style="width:20%; text-align: center;">Dose</td>
				<td style="width:20%; text-align: center;">Acção</td>		
			</tr>
			<c:forEach items="${medicacao}" var="md">
				<tr id = "texto_tab" style="width:100%;">
					<td id="texto7" style="width:16%; text-align: center;"> <c:out value="${md.getNomeUtente()}"/> </td>
					<td id="texto7" style="width:16%; text-align: center;"> <c:out value="${md.getNumUtente()}"/> </td>
					<td id="texto7" style="width:16%; text-align: center;"> <c:out value="${md.getNomeMedicamento()}"/> </td>
					<td id="texto7" style="width:16%; text-align: center;"> <c:out value="${md.getDose()}"/> </td>
					<td id="texto7" style="width:16%; text-align: center;"><button id="botao_med" onclick="aceitarMedicacao(${md.getId()})"> <c:out value="Aceitar"> </c:out> </button> <button id="botao_med" onclick="rejeitarMedicacao(${ut.getId()})"> <c:out value="Rejeitar"> </c:out> </button></td>
				</tr>
			</c:forEach>
		</table>
	</div>
</div>
	<script type="text/javascript">

	function confirmar(id){
		path="https://" + window.location.host + "/medico/confirmarConsulta";
		$.post(path, {'idConsulta':id}).done(function( data ) {
			if (data==true){window.location.reload();}
			
			else {alert("Erro a confirmar consulta!")}}

		);
		}

	function aceitarMedicacao(id){
		path="https://" + window.location.host + "/medico/aceitarMedicacao";
		$.post(path, {'idMedicacao':id}).done(function( data ) {
			if (data==true){window.location.reload();}
			
			else {alert("Erro a aceitar Medicacao!")}}

		);
		}

	function rejeitarMedicacao(id){
		path="https://" + window.location.host + "/medico/rejeitarMedicacao";
		$.post(path, {'idMedicacao':id}).done(function( data ) {
			if (data==true){window.location.reload();}
			
			else {alert("Erro a rejeitar Medicacao!")}}

		);
		}

	function rejeitar(id){
		path="https://" + window.location.host + "/medico/removerConsulta";
		$.post(path, {'idConsulta':id}).done(function( data ) {
			if (data==true){window.location.reload();}
			
			else {alert("Erro a rejeitar consulta!")}}

		);
		}

	function verDados(id){
		path="https://" + window.location.host + "/medico/consultarDados?utente="+id;
		window.location.assign(path);
		}

function change(evt, tabs) {
    // Declare all variables
    var i, tabcontent, tablinks;

    // Get all elements with class="tabcontent" and hide them
    tabcontent = document.getElementsByClassName("tabcontent");
    for (i = 0; i < tabcontent.length; i++) {
        tabcontent[i].style.display = "none";
    }

    // Get all elements with class="tablinks" and remove the class "active"
    tablinks = document.getElementsByClassName("tablinks");
    for (i = 0; i < tablinks.length; i++) {
        tablinks[i].className = tablinks[i].className.replace(" active", "");
    }

    // Show the current tab, and add an "active" class to the link that opened the tab
    document.getElementById(tabs).style.display = "block";
    evt.currentTarget.className += " active";
}

</script>
</body>
</html>