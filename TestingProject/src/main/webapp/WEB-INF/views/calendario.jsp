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
    <link href= <c:url value="/resources/css/bootstrap.min.css" ></c:url> rel="stylesheet"/>
    
    <link href= <c:url value="/resources/css/main1.css" ></c:url> rel="stylesheet"/>
    	<link href='https://fonts.googleapis.com/css?family=Roboto:400,300,100' rel='stylesheet' type='text/css'>
	 <!-- Calendario -->
    <link href=<c:url value="/resources/css/style.css" ></c:url> rel="stylesheet">

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

					<div class="col-md-6"><a id = "PortalDoUtente" href="/" style="text-decoration:none; color: white; font-weight:100">Portal do Utente</a></div>
					<div class="col-md-6" id="entrar" style="text-align: right;">${username}</div>
					</div>
				
				</div>
				
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
					<li id="upload">
						<a href="/upload">Submeter Exames</a>
					</li>
				</ul>
			</div>
				<!-- /#sidebar-wrapper -->
			
			
					<!-- Page Content -->
					<div id="page-content-wrapper">
						<div class="container-fluid">
								<div class="col-lg-12">
								   <h1>Marcar consultas</h1>
										<br>Texto explicativo. 
								</div>
						</div>

<br><br>
								<!--! Calendario -->	
								<div id="calendar-wrap">
									<header>
										<h1>Mes WTV</h1>
									</header>
								
									<div id="calendar">
										<ul class="weekdays">
											<li>Segunda</li>
											<li>Terça</li>
											<li>Quarta</li>
											<li>Quinta</li>
											<li>Sexta</li>
											<li>Sabado</li>
											<li>Domingo</li>
										</ul>
										<ul class="days">
											<li class="day">
												<div class="date">1</div>
												<div class="event">
													<div class="event-disc">
														BLA BLA BLA
													</div>
													<div class="event-time">
														UR MUUUM
													</div>
												</div>
											</li>
											
											<li class="day">
												<div class="date">2</div>
												<div class="event">
													<div class="event-disc">
														
													</div>
													<div class="event-time">
														
													</div>
												</div>
											</li>
											
											<li class="day">
												<div class="date">3</div>
												<div class="event">
													<div class="event-disc">
														
													</div>
													<div class="event-time">
														
													</div>
												</div>
											</li>
											
											<li class="day">
												<div class="date">4</div>
											</li>
											
											<li class="day">
												<div class="date">5</div>
											</li>
											
											<li class="day">
												<div class="date">6</div>
											</li>
											<li class="day">
												<div class="date">7</div>
											</li>
											
										</ul>
										
										<ul class="days">
											<li class="day">
												<div class="date">8</div>
												<div class="event">
													<div class="event-disc">
														BLA BLA BLA
													</div>
													<div class="event-time">
														UR MUUUM
													</div>
												</div>
											</li>
											
											<li class="day">
												<div class="date">9</div>
												<div class="event">
													<div class="event-disc">
														BLA BLA BLA
													</div>
													<div class="event-time">
														UR MUUUM
													</div>
												</div>
											</li>
											
											<li class="day">
												<div class="date">10</div>
												<div class="event">
													<div class="event-disc">
														BLA BLA BLA
													</div>
													<div class="event-time">
														UR MUUUM
													</div>
												</div>
											</li>
											
											<li class="day">
												<div class="date">11</div>
											</li>
											
											<li class="day">
												<div class="date">12</div>
											</li>
											
											<li class="day">
												<div class="date">13</div>
											</li>
											<li class="day">
												<div class="date">15</div>
											</li>
											
										</ul>
									</div> <!--fecha calendario-->
								</div><!--fecha calendar wrap-->
								
								
					</div><!-- /#page-content-wrapper -->
					
			</div> <!--fecha wrapper -->
		</div><!--caixa gig -->
		
	</div> 
	


    <!-- jQuery -->
   <!-- <script src="js/jquery.js">  </script> -->
    <script src='<c:url value="/resources/js/jquery.js"></c:url>'></script>

    <!-- Bootstrap Core JavaScript -->
    <!-- <script src="js/bootstrap.min.js"></script> -->
    <script src='<c:url value="/resources/js/bootstrap.min.js"></c:url>'></script>
    

    <!-- Menu Toggle Script -->
    <script>
    $("#menu-toggle").click(function(e) {
        e.preventDefault();
        $("#wrapper").toggleClass("toggled");
    });
    </script>

</body>



</html>
