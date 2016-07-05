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

<title>Portal do Médico</title>
</head>
<body background=<c:url value="/resources/gfx/Final2.png"/> />
<div class= "container">    <!-- ROW -->
	<div id="divlogout"></div>
        <div class= "rowMajor">
            <div class="col-md-11">Portal do Médico</div>
		</div>
			
	
	<div class= "container">   
        
  	<div class="col-lg-12 doctor"> 
			<div class = "row">
			
				<div class="col-xs-1 col-md-offset-4"> <img src=<c:url value="/resources/gfx/mini5.png" />  style="width:50px; margin-top:8%">
				</div>
				
				<div class="col-xs-6">
					<form action="/medico/entrar" method="post" id="login">
						<input id="username" type="text" name="username" placeholder="ID Médico" required class="caixaMedico"><br>
						<input id="username" type="text" name="pass" placeholder="Password" required class="caixaMedico">
						<input type="submit" value="Entrar">
					</form>
				</div>
				
			</div>
	</div>
	</div>
	</div>
	
</body>
</html>