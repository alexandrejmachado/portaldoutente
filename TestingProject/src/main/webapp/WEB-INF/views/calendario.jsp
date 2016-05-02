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
					<div class="col-md-11">Portal do Utente</div>
					<div class="col-md-1" id="entrar">Entrar</div>
				</div>
            
            </div>
			
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
				<div id="page-content-wrapper" style="background:none">
					<div class="container-fluid" style="background:none">
						
							<div class="col-lg-12">
											
		<!-- Calendario widget -->
		<!-- widget do site, tirado de http://booking.timekit.io/setup?first_name=Rita&last_name=Capela&email=rita_capela@msn.com&token=Dil7dVr4S2dR78bgqmO31xovGSijlvkI&signup=true  -->
			<div id="bookingjs">
			  <script type="text/javascript" src="http://ajax.googleapis.com/ajax/libs/jquery/2.1.3/jquery.min.js"></script>
			  <script type="text/javascript" src="http://cdn.timekit.io/booking-js/v1/booking.min.js" defer></script>
			  <script type="text/javascript">
				window.timekitBookingConfig = {
				  name: 'Rita',
				  email: 'rita_capela@msn.com',
				  apiToken: 'PD9uG3eaWVdoP66M5NpWKq3FLfcyeRkh',
				  calendar: 'a9b3e7ad-db1d-4abb-a645-bff8391393c8',
				  avatar: 'https://lh3.googleusercontent.com/-OkhL6Q7VHds/AAAAAAAAAAI/AAAAAAAAADs/wEhIrQtkCPQ/photo.jpg',
				  timekitFindTime: {
					filters: {
					  and: [
						{
						  business_hours: { }
						},
						{
						  exclude_weekend: { }
						}
					  ]
					}
				  }
				}
			  </script>
			</div>
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
    $("#menu-toggle").click(function(e) {
        e.preventDefault();
        $("#wrapper").toggleClass("toggled");
    });
    </script>

</body>



</html>
