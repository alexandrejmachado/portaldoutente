<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="pt">

<head>

    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="author" content="">
    <meta charset="utf-8">

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

<div class= "container">   
        
            <div class="col-lg-12"> 
                <div class= "rowMajor">
                	<div id="divisento"></div>
					<div class="col-md-11">Portal do Utente</div>
					<div class="col-md-1" id="entrar">${username}</div>
				</div>
            
            </div>
			
	<div class="col-lg-12" id="caixaGig">  <!--  -->
	
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
						<form action="logout"><button id="logout" type="submit">Logout</button></form>
							<div class="col-lg-12">
								<h1>Tí­tulo</h1>
								<p>"Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. 
								Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum."</p>
								
								<a href="#menu-toggle" class="btn btn-default" id="menu-toggle">Esconder Menu</a>
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

    <!-- Menu Toggle Script -->
    <script>
    
    path="https://" + window.location.host + "/";
	warning=null;
	pathWindow = window.location.href;
	lastThing = pathWindow.split("/")[3];
	if (lastThing=="isento") {
		$("#divisento").append('<div class="alert alert-danger fade in" role="alert" style="margin-top: 3%;">Isencao aceite!</div>');
	}
    $("#menu-toggle").click(function(e) {
        e.preventDefault();
        $("#wrapper").toggleClass("toggled");
    });
    
    
    
    </script>

</body>



</html>
