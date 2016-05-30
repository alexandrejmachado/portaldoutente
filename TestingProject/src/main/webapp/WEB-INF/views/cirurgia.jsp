<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">

	 <title>Portal do Utente</title>

	<!-- Bootstrap Core CSS -->
    <link href="css/bootstrap.min.css" rel="stylesheet">

    <!-- Custom CSS -->
    <link href="/resources/css/main.css" rel="stylesheet">

</head>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.0/jquery.min.js"></script>
<body background="/resources/gfx/Final2.png">

<div class= "container">    <!-- ROW -->
        <div class= "rowMajor">
            <div class="col-md-11">Portal do Utente</div>
</div>
</div>
<p id = "frase">Cirurgias pendentes</p>
<div class = "registo">
<table id = "tabela_ciru">
  <c:forEach items="${lista}" var="cirurgia">
    <tr>
    	<td> <c:out value="${cirurgia.tipo }"/> </td>
        <td>Médico: <c:out value="${cirurgia.idMedico}"/></td>
        <td>Data: <c:out value="${cirurgia.data}"/></td>
        <td><button id = "confirmar" value="${cirurgia.getId() }" onclick="confirmar(${cirurgia.getId() })"> Confirmar </button></td>
        <td><button id = "cancelar" value="${cirurgia.getId() }" onclick="cancelar(${cirurgia.getId() })"> Remarcar </button></td>
        
    </tr>
</c:forEach>
</table>
	
<script>
function confirmar(ID) { 
	path="https://" + window.location.host + "/";
	console.log(ID);
	$.post(path+"confirmarCirurgia", {"id":ID},function(data){
		if (data==true){window.location.replace(path + "index");}
	else {alert("Falhou a Confirmar a Cirurgia!");}
})}

</script>
	
</div>
</body>
</html>