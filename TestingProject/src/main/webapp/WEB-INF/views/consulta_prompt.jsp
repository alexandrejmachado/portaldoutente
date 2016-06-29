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
        
        <link href='https://fonts.googleapis.com/css?family=Roboto:400,300,100' rel='stylesheet' type='text/css'>
        <link href= <c:url value="/resources/css/bootstrap.min.css" ></c:url> rel="stylesheet"/>
    	<link href= <c:url value="/resources/css/bootstrap-select.css" ></c:url> rel="stylesheet"/>
        <link href= <c:url value="/resources/css/main.css" ></c:url> rel="stylesheet"/>
        
        <script src='<c:url value="/resources/js/jquery.js"></c:url>'></script>
        
        <title>Portal do Utente</title>
    </head>
   
    
    <body style="background-color: #f9f9f9">
    
    
    <div id="caixaMarcacao" style="text-align:center;">   
    
    
     <p id="pMarc">Confirmar marcação da consulta para: ${data}</p>
     
     
	    <label for="hora">Selecione a hora:</label><br>
	    <select style="padding:10px; border-radius: 5px; color: grey;" id="hora">
	    	<c:forEach items="${lista}" var="slot">
	    		<option value="${slot}"><c:out value="${slot}"/></option>
	    	</c:forEach>	
	    </select>
	    
	    <br><br>
	    
		    <label for="texto">Observações:</label><br>
		    <textarea rows="4" cols="50" id="texto"></textarea>
	    <br>
	    <button id="botao_med" onclick="marcarConsulta()">
	        Sim
	    </button>
	    
	    <button id="botao_med" onclick="parent.$.fancybox.close()">
	        Não
	    </button>
        
     </div>   
    
    
   

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
    	 $.post("https://"+ window.location.host+"/calendario/persistirConsulta",{data:data,obs:$("textarea").val()});
    	 parent.window.location.reload();
    	 
     }
     
    </script>
    </body>
    
</html>