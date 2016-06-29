<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
        
<!DOCTYPE html>
<html lang="pt">

<head>

    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="author" content="">

    <title>Portal do Utente</title>

    <!-- Bootstrap Core CSS -->
    <link href=<c:url value="/resources/css/bootstrap.min.css" ></c:url> rel="stylesheet">

    <!-- Custom CSS -->
    <link href=<c:url value="/resources/css/main.css" ></c:url> rel="stylesheet">

    <!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
    <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
    <!--[if lt IE 9]>
        <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
        <script src="https://oss.maxcdn.com/libs/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->

</head>


<body background=<c:url value="/resources/gfx/Final2.png"/> />

	 <div class="container">
            
            <div class="col-lg-12"> 
                <div class= "rowMajor">
                	<div id="divisento"></div>
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
								   <h1>Privacidade</h1>
									<div class="row" id = "caixaReceita">
										
								
								<div class = "registo">
								<table id = "tabela_pri">
								  <tr id = "texto7">
								  	<td>Medida</td>
								  	<td>Permissao</td>
								  </tr>
								  
								  <tr id = "texto7">
								  	<td>Altura</td>
								  	<td><button class= "botao_med" id="altura">Ativar</button></td>
								  </tr>
								  
								  <tr id = "texto7">
								  	<td>Colesterol</td>
								  	<td><button class= "botao_med" id="colesterol">Ativar</button></td>
								  </tr>
								  
								  <tr id = "texto7">
								  	<td>Glicemia</td>
								  	<td><button class= "botao_med" id="glicemia">Ativar</button></td>
								  </tr>
								  
								  <tr id = "texto7">
								  	<td>INR</td>
								  	<td><button class= "botao_med" id="inr">Ativar</button></td>
								  </tr>
								  
								  <tr id = "texto7">
								  	<td>Peso</td>
								  	<td><button class= "botao_med" id="peso">Ativar</button></td>
								  </tr>
								  
								  <tr id = "texto7">
								  	<td>Saturacao O2</td>
								  	<td><button class= "botao_med" id="saturacao">Ativar</button></td>
								  </tr>
								  
								  <tr id = "texto7">
								  	<td>Tensao Arterial</td>
								  	<td><button class= "botao_med" id="tensao">Ativar</button></td>
								  </tr>
								  
								  <tr id = "texto7">
								  	<td>Trigliceridos</td>
								  	<td><button class= "botao_med" id="trigliceridos">Ativar</button></td>
								  </tr>
								  
								  
								</table>
								</div>
											
									
									</div>
						
								</div>
						
						</div>
					</div>
					<!-- /#page-content-wrapper -->
	
					
			</div>
	    </div>
	    <!-- /#wrapper -->
	
	    <!-- jQuery -->
	   <!-- <script src="js/jquery.js">  </script> -->
	    <script src='<c:url value="/resources/js/jquery.js"></c:url>'></script>
	
	    <!-- Bootstrap Core JavaScript -->
	    <!-- <script src="js/bootstrap.min.js"></script> -->
	    <script src='<c:url value="/resources/js/bootstrap.min.js"></c:url>'></script>
	
				<script>
				function permitir(ID) { 
					path="https://" + window.location.host + "/";
					$.post(path+"perfil/mudarPrivacidade", {"tipo":ID, "booleano":"true"},function(data){
						if (data==true){window.location.replace(path + "index");}
					else {alert("Falhou a Confirmar a Cirurgia!");}
				})}

				function retirar(ID) { 
					path="https://" + window.location.host + "/";
					$.post(path+"perfil/mudarPrivacidade", {"tipo":ID, "booleano":"false"},function(data){
						if (data==true){window.location.replace(path + "index");}
					else {alert("Falhou a Confirmar a Cirurgia!");}
				})}
				</script>

				<script>
					 $(document).ready(function(){
				   		mu = ${mu};
				   		
					 })
				 });
				 </script>
	</div>
</body>



</html>