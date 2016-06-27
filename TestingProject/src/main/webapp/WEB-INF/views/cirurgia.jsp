<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.0/jquery.min.js"></script>
<body background="/resources/gfx/Final2.png">

<div class= "container">    <!-- ROW -->
        <div class= "rowMajor">
            <div class="col-md-11">Portal do Utente</div>
            <div class="col-md-1" id="entrar">${username}</div>
		</div>
		
		<nav>
          <ul>
            <li id="login">
              <a id="login-trigger" href="#">
                OpÃ§Ãµes <span>â–¼</span>
              </a>
              <div id="login-content">
                <a button href= "/perfil/dados" id = "aaa">Ver Perfil</button></a><br>
                <a button href= "/logout" id ="aaa">Logout</button></a>
              </div>                     
            </li>
          </ul>
        </nav>
        
        
        <div class="col-lg-12" id="caixaGig">  <!--  -->
		
			<div id="wrapper">
	
				<!-- Sidebar -->
			<div id="sidebar-wrapper">
				<ul class="barra">
						
					<li id="consulta">
						<a href="/calendario/view">Marcação de Consultas</a>
					</li>
					<li id="medicacao2">
						<a href="/medicacao/view">Registar Medicação</a>
					</li>
					<li id="medicao">
						<a href="/medicoes">Medições</a>
					</li>
					<li id="cirurgia">
						<a href="/cirurgia">Cirurgia</a>
					</li>
					<li id="isencao">
						<a href="/isencao">Pedido de Isenção</a>
					</li>
										<li style="opacity:0">
						<a></a>
					</li>
					<li>
						<a href="/">Voltar para o Menu</a>
					</li>
				</ul>
			</div>
	        	<!-- /#sidebar-wrapper -->
			
			
			<!-- Page Content -->
			<div id="page-content-wrapper">
			   <div class="container-fluid">
							
				<div class="col-lg-12">
	
	<p id = "frase">Cirurgias pendentes</p>
	<div class = "registo">
	<table id = "tabela_ciru">
	  <c:forEach items="${lista}" var="cirurgia">
	    <tr>
	    	<td> <c:out value="${cirurgia.tipo }"/> </td>
	        <td>MÃ©dico: <c:out value="${cirurgia.idMedico}"/></td>
	        <td>Data: <c:out value="${cirurgia.data}"/></td>
	        <td><button id = "confirmar" value="${cirurgia.getId() }" onclick="confirmar(${cirurgia.getId() })"> Confirmar </button></td>
	        <td><button id = "cancelar" value="${cirurgia.getId() }" onclick="cancelar(${cirurgia.getId() })"> Remarcar </button></td>
	        
	    </tr>
	</c:forEach>
	</table>
	</div>
	
				</div>
						
				</div>
			</div><!-- /#page-content-wrapper -->
	
					
			</div>    <!-- /#wrapper -->
	    </div><!-- caixaGig -->
		
<script>
function confirmar(ID) { 
	path="https://" + window.location.host + "/";
	console.log(ID);
	$.post(path+"confirmarCirurgia", {"id":ID},function(data){
		if (data==true){window.location.replace(path + "index");}
	else {alert("Falhou a Confirmar a Cirurgia!");}
})}

</script>

<script>
     $(document).ready(function(){
   $('#login-trigger').click(function(){
     $(this).next('#login-content').slideToggle();
     $(this).toggleClass('active');          
    
     if ($(this).hasClass('active')) $(this).find('span').html('&#x25B2;')
       else $(this).find('span').html('&#x25BC;')
     })
 });
 </script>
	

</body>
</html>