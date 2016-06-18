.<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="pt">

<head>

	 <title>Portal do Utente</title>

	<link href= <c:url value="/resources/css/bootstrap.min.css" ></c:url> rel="stylesheet"/>
    
    <link href= <c:url value="/resources/css/main.css" ></c:url> rel="stylesheet"/>

</head>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.0/jquery.min.js"></script>
<body background=<c:url value="/resources/gfx/Final2.png"/> />

<div class= "container">    <!-- ROW -->
        <div class= "rowMajor">
            <div class="col-md-11">Portal do Utente</div>
</div>
</div>

<div class = "registo">

	<form method="post">
  		Nome:*
  		<input id = "campo" type="text" name="nome" required><br>
  		Numero do Utente:*
  		<input id = "campo" type="text" name="num_utente" required><br>
  		Numero do CC:*
  		<input id = "campo" type="text" name="cc" required><br>
  		Morada:
  		<input id = "campo" type="text" name="morada"><br>
  		E-Mail *:
  		<input id = "campo" type="text" name="mail" required><br>
  		Password:*
  		<input id = "campo" type="password" name="pass" required><br>
  		Comfirmar Password:*
  		<input id = "campo" type="password" name="passConfirm" required><br>
  		Numero de Telemovel:
  		<input id = "campo" type="text" name="telemovel"><br>
  		Contacto de Emergencia:
  		<input id = "campo" type="text" name="emergencia"><br>
  		Numero de Identificacao Fiscal:*
  		<input id = "campo" type="text" name="nif"><br>
  		Hospital:
  		<input id = "campo" type="text" name="hospital"><br>
  		Centro de Saúde:
  		<input id = "campo" type="text" name="centro_saude"><br>
  		
  		<p id="legenda"> * (campos obrigatorios) </p>
  		
	</form> 
	<input id = "botao_reg" type="submit" onclick="registar()"/>
</div>
		
<script>
function registar()
{
		path="https://" + window.location.host + "/";
		$('.registo').append($('<img>',{id:'theImg',src:'resources/gfx/loadingGif.gif',width: '50', height: '50'}));
		$.post(path + 'registoUtente', $("form").serialize()).done(function( data ) {
			if (data[0]=="true"){window.location.replace(path + "index");}
			else {alert(data[0]);$("#theImg").remove();}}
		);

}
</script>

