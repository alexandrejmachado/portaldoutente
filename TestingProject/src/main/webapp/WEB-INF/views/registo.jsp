<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="pt">

<head>

	 <title>Portal do Utente</title>

	<link href= <c:url value="/resources/css/bootstrap.min.css" ></c:url> rel="stylesheet"/>
    
    <link href= <c:url value="/resources/css/registo.css" ></c:url> rel="stylesheet"/>
	<meta charset="UTF-8">
	
</head>
    <link rel="stylesheet" href="https://code.jquery.com/ui/1.11.4/themes/smoothness/jquery-ui.css">
  	<script src="https://code.jquery.com/jquery-1.10.2.js"></script>
  	<script src="https://code.jquery.com/ui/1.11.4/jquery-ui.js"></script>
  	
  	<!-- Font -->
  	<link href='https://fonts.googleapis.com/css?family=Roboto:400,300,100' rel='stylesheet' type='text/css'>

<body background=<c:url value="/resources/gfx/Final2.png"/> />

<div class= "container">    <!-- ROW -->
        <div class= "rowMajor">
            <div class="col-md-11">Portal do Utente</div>
		</div>
</div>


<div class="row" style="background:none">

<div class="col-xs-2"></div> <!--empty-->

	<div class= "registo1 col-sm-8">
		<div class="colunas col-xs-8 col-sm-6" style="text-align:right" > <!--1a coluna cenas-->

		
				<form method="post">
				<table>
					<tr>
						<td>Nome* </td>
						<td> <input id = "campo" type="text" name="nome" required></td>
						<td><div id="id1" class= "warning"></div> </td>
	
					</tr>
					<tr>
						<td>Número do Utente* </td>
						<td> <input id = "campo" type="text" name="num_utente" onkeypress='return event.charCode >= 48 && event.charCode <= 57' maxlength="9" required></td>
						<td> </td>
						
					</tr>
					<tr>
						<td>Número do CC* </td>
						<td><input id = "campo" type="text" name="cc"  onkeypress='return event.charCode >= 48 && event.charCode <= 57' maxlength="8" required> </td>
						<td> </td>
					</tr>
					<tr>
						<td>Morada </td>
						<td><input id = "campo" type="text" name="morada"> </td>
						<td> </td>
					</tr>
					<tr>
						<td>Localidade*</td>
						<td>
  							<input style="color:black;padding-left: 5%;color: black;font-size: 15px;" required id="tags" name="localidade"></td>
						<td> </td>
					</tr>
					
				</table>	
		</div>
		<div class="colunas col-xs-8 col-sm-6" style="text-align:right"> <!--2a coluna cenas-->
				<table>
					<tr>
						<td>E-Mail*</td>
						<td><input id = "campo" type="text" required name="mail"></td>
						<td> </td>
					</tr>
					<tr>
						<td>Password* </td>
						<td><input id = "campo" type="password" name="pass" required></td> 
						<td> </td>
					</tr>
					<tr>
						<td>Confirmar Password* </td>
						<td><input id = "campo" type="password" name="passConfirm" required></td> 
						<td> </td>
					</tr>
					<tr>
						<td>Número de Telemóvel* </td>
						<td><input id = "campo" type="text" onkeypress='return event.charCode >= 48 && event.charCode <= 57' name="telemovel" required></td> 
						<td> </td>
					</tr>
					<tr>
						<td>Contacto de Emergência </td>
						<td><input id = "campo" type="text" onkeypress='return event.charCode >= 48 && event.charCode <= 57' name="emergencia"></td> 
						<td> </td>
						
					</tr>	
					<tr>
						<td>Número de Identificação Fiscal*</td>
						<td><input id = "campo" type="text" name="nif"  onkeypress='return event.charCode >= 48 && event.charCode <= 57' required></td> 
						<td> </td>
						
					</tr>
					
				</table>	
				</form>
		</div>
		
	</div>
	<div class="col-xs-2" > </div> <!--empty-->
	
</div>

<div class="col-xs-2" > </div> <!--empty-->	



<div class="col-md-8" style="text-align:center">
			<p id = "legenda"> * (campos obrigatorios) </p> 
			<input id = "botao_reg" type="submit" onclick="registar()"/>
</div>
			


</div>

<script>
    var link="<c:url value="/resources/localidades.json"> </c:url>";
    $.ajax({
        type:       "GET",
        url:        link,
        success:    function(response) {
            $("#tags").autocomplete({ source: response });
        }
    });
</script>
		
<script>
function registar()
{
		path="https://" + window.location.host + "/";
		$('.registo1').append($('<img>',{id:'theImg',src:'resources/gfx/loadingGif.gif',width: '50', height: '50'}));
		$.post(path + 'registoUtente', $("form").serialize()).done(function( data ) {
			if (data[0]=="true"){window.location.replace(path + "index");}
			else {alert(data[0]);$("#theImg").remove();}}
		);

}
</script>

