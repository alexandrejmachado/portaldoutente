<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<script src="https://code.jquery.com/jquery-1.10.2.js"></script>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Médico</title>
</head>
<body>
	<h1>MÉDICO NUMERO: ${username}</h1>
	
	<c:forEach items="${listaUtentes}" var="utente">
   		<tr id = "texto_tab">
    		<td> <c:out value="${utente.getNome()}"/> </td>
    		<td> <c:out value="${utente.getNumUtente()}"/> </td>
    		<td> <button onclick="consultasPendentes(${utente.getNumUtente()})"> Consultas Pendentes</button> </td>
    	</tr>
	</c:forEach>
	
</body>
</html>