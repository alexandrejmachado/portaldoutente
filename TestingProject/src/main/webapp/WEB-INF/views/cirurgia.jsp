<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">

	 <title>Portal do Utente</title>

	<!-- Bootstrap Core CSS -->
    <link href="css/bootstrap.min.css" rel="stylesheet">

    <!-- Custom CSS -->
    <link href="/resources/css/main.css" rel="stylesheet">

</head>
<body background="/resources/gfx/Final2.png">

<div class= "container">    <!-- ROW -->
        <div class= "rowMajor">
            <div class="col-md-11">Portal do Utente</div>
</div>
</div>
<p id = "frase">Cirurgias pendentes</p>
<div class = "registo">
<table id = "tabela_ciru">
  <tr>
    <td>Cirurgia ao Coracao</td>
    <td><button id = "botao_reg">confirmar</button></td> 
   <td><button id = "botao_reg">remarcar</button></td> 
  </tr>
  <tr>
    <td>Cirurgia aos Pulmoes</td>
    <td><button id = "botao_reg">confirmar</button></td>  
    <td><button id = "botao_reg">remarcar</button></td> 
  </tr>
  <tr>
    <td>Cirurgia ao Pe</td>
    <td><button id = "botao_reg">confirmar</button></td> 
   <td><button id = "botao_reg">remarcar</button></td> 
  </tr>
  <tr>
    <td>Cirurgia ao Cerebro</td>
    <td><button id = "botao_reg">confirmar</button></td> 
   <td><button id = "botao_reg">remarcar</button></td> 
  </tr>
</table>
	
	
</div>
</body>
</html>