<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<script src="https://code.jquery.com/jquery-1.10.2.js"></script>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Médico</title>
<link href='https://fonts.googleapis.com/css?family=Roboto:400,300,100' rel='stylesheet' type='text/css'>
<!-- Bootstrap Core CSS -->
    <link href=<c:url value="/resources/css/bootstrap.min.css" ></c:url> rel="stylesheet">

    <!-- Custom CSS -->
    <link href=<c:url value="/resources/css/main.css" ></c:url> rel="stylesheet">
</head>
<body background=<c:url value="/resources/gfx/Final2.png"/> />
	
		<div class="container">
            
            <div class="col-lg-12"> 
                <div class= "rowMajor">
                    <div id="divisento"></div>
                    <div class="col-md-11">Número Médico:</div>
                    <div class="col-md-1" id="entrar">${username}</div>
                </div>
            
            </div>
    	</div>
    	
    	<div id = "medico" style="text-align: center; width:60%; font-family:'Roboto',Arial, sans-serif; margin-left:24%;">
<ul class="tab">
  <li><a href="#" class="tablinks" onclick="change(event, 'ConsultaMed')">Confirmacao de Consultas</a></li>
  <li><a href="#" class="tablinks" onclick="change(event, 'DadosMed')">Consultar dados do Utente</a></li>
  <li><a href="#" class="tablinks" onclick="change(event, 'MedicacaoMed')">Renovacao de Medicacao</a></li>
</ul>

	<div id="ConsultaMed" class="tabcontent">
	   <table>
			<tr style="width:100%;">
				<td style="width:20%; text-align: center;">Número do Utente</td>
				<td style="width:20%; text-align: center;">Data</td>
				<td style="width:20%; text-align: center;">Número da Instituição</td>
				<td style="width:20%; text-align: center;">Estado</td>
				<td style="width:20%; text-align: center;">Acção</td>
			</tr>
			<c:forEach items="${listaParaTratar}" var="mu">
		   		<tr id = "texto_tab" style="width:100%;">
		   			<c:set var="dataTratada" value="${fn:substring(mu.getData(), 0, 16)}" />
		    		<td id="texto7" style="width:20%; text-align: center;"> <c:out value="${mu.getNumUtente()}"/> </td>
		    		<td id="texto7" style="width:20%; text-align: center;"> <c:out value="${dataTratada}"/> </td>
		    		<td id="texto7" style="width:20%; text-align: center;"> <c:out value="${mu.getIdInstituicao()}"/> </td>
		    		<td id="texto7" style="width:20%; text-align: center;"> <c:out value="${mu.isConfirmada()}"/> </td>
		    		<td id="texto7" style="width:20%; text-align: center;"><button onclick=""> <c:out value="Confirmar Consulta"></c:out> </button></td>
		    	</tr>
			</c:forEach>
		</table>
	</div>

	<div id="DadosMed" class="tabcontent">
	   
	</div>
	
	<div id="MedicacaoMed" class="tabcontent">
	   
	</div>
</div>
	<script type="text/javascript">

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