<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<html>

<head>
     <title>Portal do Utente</title>

    <link href='https://fonts.googleapis.com/css?family=Roboto:400,300,100' rel='stylesheet' type='text/css'>

    <!-- Bootstrap Core CSS -->
    <link href="css/bootstrap.min.css" rel="stylesheet">

    <!-- Custom CSS -->
   
    <title>SpringMVC Starter Application</title>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <link href=<c:url value="/resources/css/main.css" ></c:url> rel="stylesheet">
    
    <script src='<c:url value="/resources/js/jquery.js"></c:url>'></script>
</head>

<style>
.ui-autocomplete { max-height: 200px; overflow-y: auto; overflow-x: hidden;}

</style>

<body style="background-color: #f9f9f9">

<div id= "confConsulta" style="background-color: #f9f9f9">
  <p>${confirmada}</p>
 
	<p>Centro de Saúde: ${centroSaude}</p>
	<p>Médico: ${medico}</p>
	<p>Sala: ${sala}</p>
	<c:set var="dataTratada" value="${fn:substring(data, 0, 16)}" />
	<p>Data: ${dataTratada}</p>
	<p> Deseja desmarcar esta consulta?</p>
 <button id="botao_med" onclick="removerConsulta()">SIM</button> <button id="botao_med" onclick="parent.$.fancybox.close()">NÃO</button>
</div>
  <script>
	function removerConsulta(){
		var id = ${consultaId};
		console.log("stuffs");
		console.log(id);
		$.post("https://"+ window.location.host+"/calendario/removerConsulta",{consultaId:id});
		$("body").html("<h1>Consulta Desmarcada</h1>");
		setTimeout(function(){
		}, 2000);
		parent.window.location.reload();
		}
 </script>
</body>
</html>