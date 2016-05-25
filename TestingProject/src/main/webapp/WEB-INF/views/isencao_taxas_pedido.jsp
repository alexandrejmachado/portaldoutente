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
                <a button href= "/perfil" id = "aaa">Ver Perfil</button></a><br>
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
						
					<li>
						<a href="#mar">Marcação de Consultas</a>
					</li>
					<li>
						<a href="#">Marcações Confirmadas</a>
					</li>
					<li>
						<a href="#">Renovar Receita</a>
					</li>
					<li>
						<a href="#">Medições</a>
					</li>
					<li>
						<a href="#">Agregado Familiar</a>
					</li>
					<li>
						<a href="#">Pedido de Isenção</a>
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
													
													<td><input id = "campoM" type="text" name="morada"> </td>
												</tr>
												<tr>
													<td>E-Mail </td>
													
													<td> <input id = "campoM" type="text" name="mail"></td>
												</tr>
												<tr>
													<td>Password </td>
													
													<td><input id = "campoM" type="password" name="pass" required> </td>
												</tr>
												<tr>
													<td>Número de Telemóvel </td>
													
													<td> <input id = "campoM" type="text" name="telemovel" required></td>
												</tr>
												<tr>
													<td style="width:70%" >Contacto de Emergência </td>
													
													<td> <input id = "campoM" type="text" name="emergencia"></td>
													
												</tr>	

											</table>	
											</form>
											
									</div>	
								<button  id = "botao_mudancas" type="submit"> Submeter mudanças</button>
							
						</div>
						</div>
				</div>
				
				            
     

				
		</div>
    </div>
	    <!-- /#wrapper -->
	
	    <!-- jQuery -->
	   <!-- <script src="js/jquery.js">  </script> -->
	    <script src='<c:url value="/resources/js/jquery.js"></c:url>'></script>
	
	    <!-- Bootstrap Core JavaScript -->
	    <!-- <script src="js/bootstrap.min.js"></script> -->
	    <script src='<c:url value="/resources/js/bootstrap.min.js"></c:url>'></script>
	
	    <!-- Error Script -->
	    <script>
	    path="https://" + window.location.host + "/";
		warning=null;
		function lolada(){
			$.post(path + 'verificarIsencao', $("form").serialize()).done(function( data ) { 
				if (data=="true"){window.location.replace(path + "isento");}
				else if(warning==null){ $("#erro").append('<div class="alert alert-danger fade in" role="alert" style="margin-top: 3%;">NIF incorrecto</div>');
				warning=true;}
				
				});
			}
	    </script>
	    
	</div>
</body>



</html>
