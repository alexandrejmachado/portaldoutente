<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<script src="https://code.jquery.com/jquery-1.10.2.js"></script>
	<link href=<c:url value="/resources/css/bootstrap.min.css" ></c:url> rel="stylesheet">
	<link href='https://fonts.googleapis.com/css?family=Roboto:400,300,100' rel='stylesheet' type='text/css'>
	<link href=<c:url value="/resources/css/main.css" ></c:url> rel="stylesheet">
	<script src='<c:url value="/resources/js/jquery.js"></c:url>'></script>
<title>Medições - Médico</title>
</head>
<body background=<c:url value="/resources/gfx/Final2.png"/> />
	<div class="container">
	            <div class="col-lg-12"> 
	                <div class= "rowMajor">
	                    
	                    <div class="col-md-10">Número Médico: ${username}</div>
	                    <div class="col-md-1" id="entrar"><a style="color:white; text-decoration:none;" href="/medico/main">Voltar</a></div>
	                    <div class="col-md-1" id="entrar"><a style="color:white; text-decoration:none;" href="/medico/logout">Sair</a></div>
	               </div>
	             </div>
	 </div>
	             
	      	<div id="page-content-wrapper">
				<div class="container-fluid" style = "height: 600px;">
			        <select style="padding:10px; border-radius: 5px; color: grey;" id="hora">
				    	<c:forEach items="${medidas}" var="slot">
				    		<option value="${slot}"><c:out value="${slot}"/></option>
				    	</c:forEach>
				    </select>
				    <button id="botao_med" onClick="verMedida(${utente})">Ver gráfico</button>
			    
			    <div id="medidas" class="panel-body">
				       
				</div>
		        </div>
		    </div>
	   </div>
	   
	   <style>
	   
	   object {
	   	height: 500px;
	   }
	   
	   </style>
	   
   <script type="text/javascript">
    	 function verMedida(utente){
    		var medida = $( "#hora" ).val();
			$("#medidas").html('<object data="https://'+window.location.host+'/medico/visualizar/'+medida+'/'+utente+'" style="width:100%"/>');
			}
    	 
    </script>
</body>
</html>