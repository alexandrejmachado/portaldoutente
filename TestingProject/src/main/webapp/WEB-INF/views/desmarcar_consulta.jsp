<%--
    JBoss, Home of Professional Open Source
    Copyright 2013, Red Hat, Inc. and/or its affiliates, and individual
    contributors by the @authors tag. See the copyright.txt in the
    distribution for a full listing of individual contributors.

    Licensed under the Apache License, Version 2.0 (the "License");
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at
    http://www.apache.org/licenses/LICENSE-2.0
    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<html>

<head>
    <title>SpringMVC Starter Application</title>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <link rel="stylesheet" type="text/css" href="<c:url value="/static/resources/css/screen.css"/>"/>
    <script src='<c:url value="/resources/js/jquery.js"></c:url>'></script>
</head>

<body>
  <h1>Dados da Consulta</h1>

  <p>Centro de Saúde: ${centroSaude}</p>
  <p>Médico: ${medico}</p>
  <p>Sala: ${sala}</p>
  <c:set var="dataTratada" value="${fn:substring(data, 0, 16)}" />
  <p>Data: ${dataTratada}</p>
 <p> Deseja desmarcar esta consulta?</p>
 <button onclick="removerConsulta()">SIM</button> <button onclick="parent.$.fancybox.close()">NÃO</button>
 
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
