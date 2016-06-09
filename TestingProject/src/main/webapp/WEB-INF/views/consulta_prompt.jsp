<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
        
<!DOCTYPE HTML>
<html lang="pt">

    <head>
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <meta name="description" content="">
        <meta name="author" content="">
        <meta charset="utf-8">
        
        <link href= <c:url value="/resources/css/bootstrap.min.css" ></c:url> rel="stylesheet"/>
    
        <link href= <c:url value="/resources/css/main.css" ></c:url> rel="stylesheet"/>
        
        <script src='<c:url value="/resources/js/jquery.js"></c:url>'></script>
        
        <title>Portal do Utente</title>
    </head>
    
    <body background=<c:url value="/resources/gfx/Final2.png"/>>
         
    <select>
    	<c:forEach items="${lista}" var="slot">
    		<option value="${slot}"><c:out value="${slot}"/></option>
    	</c:forEach>   		
    </select>
    
    <textarea rows="4" cols="50">Observações</textarea>
    
    <p>Confirmar marcação da consulta para: ${data}</p>
    
    <button onclick="marcarConsulta()">
        Sim
    </button>
    
    <button onclick="parent.$.fancybox.close()">
        Não
    </button>
        
        
    
    
        <div id="wrapper">

			<!-- Sidebar -->
    <script>
     var data=new Date('${data}');
     function marcarConsulta()
     {
    	 var temp=$("select").val();
    	 temp=temp.split(":");
    	 data.setHours(temp[0]);
    	 data.setMinutes(temp[1]);
    	 data.setSeconds(0);
    	 console.log(data);
    	 $.post("https://"+ window.location.host+"/testCalendar/persistirConsulta",{data:data,obs:$("textarea").val(),instituicao:"1"});
    	 parent.window.location.reload();
    	 
     }
     
    </script>
    </body>
    
</html>