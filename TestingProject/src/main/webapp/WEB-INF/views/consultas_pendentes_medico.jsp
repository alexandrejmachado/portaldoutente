<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<!-- Bootstrap Core CSS -->
    <link href=<c:url value="/resources/css/bootstrap.min.css" ></c:url> rel="stylesheet">

	<link href='https://fonts.googleapis.com/css?family=Roboto:400,300,100' rel='stylesheet' type='text/css'>

    <!-- Custom CSS -->
    <link href=<c:url value="/resources/css/main.css" ></c:url> rel="stylesheet">
<title>Consultas Pendentes - Médico</title>
</head>
<body>
	<table>
		<tr>
			<td>Número do Utente</td>
			<td>Data</td>
			<td>Número da Instituição</td>
			<td>Acção</td>
		</tr>
		<c:forEach items="${listaParaTratar}" var="mu">
	   		<tr id = "texto_tab">
	    		<td> <c:out value="${mu.getNumUtente()}"/> </td>
	    		<td> <c:out value="${mu.getData()}"/> </td>
	    		<td> <c:out value="${mu.getIdInstituicao()}"/> </td>
	    		<td><button onclick=""> <c:out value="Confirmar Consulta"></c:out> </button></td>
	    	</tr>
		</c:forEach>
	</table>
</body>
</html>