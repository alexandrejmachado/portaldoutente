<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="pt">

<head>

    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="author" content="">

     <!-- Bootstrap Core CSS -->
    <link href="/resources/css/bootstrap.min.css" rel="stylesheet">

    <!-- Custom CSS -->
    <link href="/resources/css/main.css" rel="stylesheet">
    	<link href='https://fonts.googleapis.com/css?family=Roboto:400,300,100' rel='stylesheet' type='text/css'>
    
    <link rel="stylesheet" href="https://code.jquery.com/ui/1.11.4/themes/smoothness/jquery-ui.css">
  	<script src="https://code.jquery.com/jquery-1.10.2.js"></script>
  	<script src="https://code.jquery.com/ui/1.11.4/jquery-ui.js"></script>

</head>


<body background="/resources/gfx/Final2.png"/>


<div class= "container">    <!-- ROW -->
        <div class= "rowMajor">
            	<div class="col-md-6"><a id = "PortalDoUtente" href="/" style="text-decoration:none; color: white; font-weight:100">Portal do Utente</a></div>
				<div class="col-md-6" id="entrar" style="text-align: right;">${username}</div>
        </div>
</div>


<div  class="exame">
		<p id="texto2">Consulte abaixo os seus Exames MÃ©dicos</p>

        <table id="tabela_exames">

            <tr id = "texto_tab">
                <td>NOME</td>
                <td>DOSE</td> 
                <td>INDICAÇÕES</td>
                <td>Renovar</td>
                <td>Apagar</td>
            </tr>

         <c:forEach items="${lista}" var="medicacao">
    <tr id = "texto_tab">
    	<td> <c:out value="${medicacao.nomeMedicamento }"/> </td>
        <td><c:out value="${medicacao.dose}"/></td>
        <td><c:out value="${medicacao.indicacoes}"/></td>
        <td><button id = "renovar" value="${medicacao.getId()}" onclick="renovar(${medicacao.id})"> Pedir Renovacao </button></td>
        <td><button id = "apagar" value="${medicacao.getId() }" onclick="apagar(${medicacao.id})"> Apagar </button></td>
        
    </tr>
</c:forEach>
        </table>
      <script>
function renovar(ID) { 
	path="https://" + window.location.host + "/";
	console.log(ID);
	$.post(path+"confirmarCirurgia", {"id":ID},function(data){
		if (data==true){window.location.replace(path + "index");}
	else {alert("Falhou a Confirmar a Cirurgia!");}
})}

</script>
      
</div>



</body>



</html>