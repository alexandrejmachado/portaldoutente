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
        
        <link href= <c:url value="/resources/css/bootstrap.min.css"></c:url> rel="stylesheet"/>
    
        <link href= <c:url value="/resources/css/main1.css"></c:url> rel="stylesheet"/>
        
        <link href= <c:url value="/resources/css/button.css"></c:url> rel="stylesheet"/>
        <title>Portal do Utente</title>
    </head>
    
    <body background=<c:url value="/resources/gfx/Final2.png"/>>
    <div class="container">
            <div class="col-lg-12"> 
                <div class= "rowMajor">
					<div class="col-md-11">Portal do Utente</div>
					<div class="col-md-1" id="entrar">${username}</div>
				</div>    
            </div>
        <nav>
          <ul>
            <li id="login">
              <a id="login-trigger" href="#">
                Opções <span>▼</span>
              </a>
              <div id="login-content">
                <a href= "/perfil" id = "aaa">Ver Perfil</a><br>
                <a href= "/logout" id ="aaa">Logout</a>
              </div>                     
            </li>
          </ul>
        </nav>
        
        
    <div class="col-lg-12" id="caixaGig">
    
        <div id="wrapper">

			<!-- Sidebar -->
			<div id="sidebar-wrapper">
				<ul class="barra">
						
					<li>
						<a href="/calendario">Marcação de Consultas</a>
					</li>
					<li>
						<a href="#">Marcações Confirmadas</a>
					</li>
					<li>
						<a href="#">Renovar Receita</a>
					</li>
					<li>
						<a href="/medicoes">Medições</a>
					</li>
					<li>
						<a href="#">Agregado Familiar</a>
					</li>
					<li>
						<a href="/isencao">Pedido de Isenção</a>
					</li>
				</ul>
			</div>
			<!-- /#sidebar-wrapper -->
		
		
								<!-- Page Content -->
				<div id="page-content-wrapper">
					<div class="container-fluid">
						
						<div class="col-lg-12">
						
							   <h1>Modificação de Dados</h1>
																	
		
								
									<div class = "modificacao">

											<form method="post">
											<table>
												<tr>
													<td>Nome</td> 
													
													<td id="fixosMod">${username}</td>
													
												</tr>
												<tr>
													<td>Número do Utente 
													
													<td id="fixosMod">${utente} </td>
													
												</tr>
												<tr>
													<td>Número do CC</td>
													
													<td id="fixosMod">${cc} </td>
												</tr>
												<tr>
													<td>Morada </td>
													
													<td><input id = "campoM" type="text" name="morada" value="${morada}" > </td>
												</tr>
												<tr>
													<td>E-Mail </td>
													
													<td> <input id = "campoM" type="text" value="${mail}" name="mail"></td>
												</tr>
												
												<tr>
													<td>Número de Telemóvel </td>
													
													<td> <input id = "campoM" type="text" value="${telemovel}" name="telemovel" ></td>
												</tr>
												<tr>
													<td style="width:70%" >Contacto de Emergência </td>
													
													<td> <input id = "campoM" type="text" value="${emergencia}" name="emergencia"></td>
													
												</tr>
												
											</table>	
											</form>
											
											<form method="post">
											<table style = "margin-top: 32px;">
												<tr>
													<td style="width:70%" " >Password antiga:</td>
													
													<td><input id = "campoM" type="password" name="pass" required> </td>
												</tr>
										
												<tr>
													<td>Nova password:</td>
													
													<td><input id = "campoM" type="password" name="pass" required> </td>
												</tr>
												<tr>
													<td>Confirmar nova password:</td>
													
													<td><input id = "campoM" type="password" name="pass" required> </td>
												</tr>
											</table>
											</form>
											
									</div>
											<div class='buttons'>
											  <div class='set blue'>
											    <a class='btn pri' type="submit" >Submeter</a>
											  </div>	
							
						</div>
						</div>
				</div>

				
		</div> 
    </div> <!-- /wrapper -->
    </div> <!-- /caixaGig -->
    </div><!-- /container-->
    </body>
    
    <!-- jQuery -->
   <!-- <script src="js/jquery.js">  </script> -->
    <script src='<c:url value="/resources/js/jquery.js"></c:url>'></script>

    <!-- Bootstrap Core JavaScript -->
    <!-- <script src="js/bootstrap.min.js"></script> -->
    <script src='<c:url value="/resources/js/bootstrap.min.js"></c:url>'></script>
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
    
</html>