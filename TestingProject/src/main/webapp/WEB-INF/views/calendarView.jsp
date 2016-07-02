<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>

<meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="author" content="">
    <meta charset="utf-8">

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
	
	
<meta charset='utf-8' />



<style>

	body {
		margin: 40px 10px;
		padding: 0;
		font-family: "Lucida Grande",Helvetica,Arial,Verdana,sans-serif;
		font-size: 14px;
	}

	@font-face {
    font-family: 'DengXian';
    src: url('/resources/fonts/DengXian.ttf');
}

	#calendar {
		    max-width: 900px;
	    margin-top: 18%;
	    margin-left: 24%;
	    
	}
	
	.fc-view-container {
	background-color: white;
	}
	
	h2 {
	color: white;
	font-family:'DengXian', sans-serif;}

</style>
</head>
<body background=<c:url value="/resources/gfx/Final2.png"/> />

<div id="guardiao"></div>
<div class= "container">   
        
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
					<li id="upload">
						<a href="/upload">Submeter Exames</a>
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
		
		
				

				
		</div>
    </div>

    <div id='calendar'></div>
    
    <!-- /#wrapper -->

   <!-- jQuery -->
   <!-- <script src="js/jquery.js">  </script> -->
    <script src='<c:url value="/resources/js/jquery.js"></c:url>'></script>

    <!-- Bootstrap Core JavaScript -->
    <!-- <script src="js/bootstrap.min.js"></script> -->
    <script src='<c:url value="/resources/js/bootstrap.min.js"></c:url>'></script>

    <!-- Menu Toggle Script -->
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
 <script> 
    
     path="https://" + window.location.host + "/";
 	warning=null;
 	pathWindow = window.location.href;
 	lastThing = pathWindow.split("/")[3];
 	if (lastThing=="isento") {
 		$("#divisento").append('<div class="alert alert-danger fade in" role="alert" style="margin-top: 3%;">Isencao aceite!</div>');
 	}
	
<%-- 	paramOne =  '<%=(String) request.getAttribute("sessionMode")%>'; --%>
 	console.log(paramOne);
 	if ( paramOne == "guardian") {
 		$("#guardiao").append('<div class="alert alert-danger fade in" role="alert" style="margin-top: 3%;">Modo Guardiao!</div>');
		
 	}
	
    </script>

<link href="<c:url value="/resources/res-cal/fullcalendar.css" ></c:url>" rel='stylesheet' />
<link href="<c:url value="/resources/res-cal/fullcalendar.print.css"> </c:url>" rel='stylesheet' media='print' />
<script src='<c:url value="/resources/js/jquery.js"></c:url>'></script>
<script src='<c:url value="/resources/res-cal/lib/moment.min.js"></c:url>'></script>
<script src='<c:url value="/resources/res-cal/fullcalendar.js"></c:url>'></script>
<script src='<c:url value="/resources/res-cal/lib/jquery.fancybox.pack.js"></c:url>'></script>
<link href="<c:url value="/resources/res-cal/jquery.fancybox.css"> </c:url>" rel='stylesheet' media='screen' />
 
	

<script>
$( "#inst" ).change(function() {
			  alert( $("#inst").val() );
			});
</script>
<script>
$(document).ready(function() {
	$.get("https://"+ window.location.host+"/calendario/getEventos").done(function(data){
			var pel = ${data}
			var d = new Date(pel);
			$('#calendar').fullCalendar({
				timezone:'local',
				timeFormat: 'HH:mm',
					defaultDate: d,
					editable: false,
					disableDragging:true,
					eventLimit: true, // allow "more" link when too many events
					events: data,
					selectable: true,
					selectHelper: true,
					dayRender: function(date, cell){
						if(date < new Date()){
							cell.css("background-color","#D3D3D3");
							cell.css("opacity", "0.5");
						}
					},
					select: function(start, end) {
						today= d
					    if(new Date(start) < new Date())
					    {
					        alert("Não pode marcar consultas para datas passadas");
					    }
					    else
					    {
					    	$.fancybox.open({
								title :  "Marque uma consulta por favor",
								href : 'https://' + window.location.host + "/calendario/marcarConsultaView?data="+new Date(start).getTime(),
								type : 'iframe',
								width: '60%',
								height: '60%',
								padding : 5
										});
					    }
						
						$('#calendar').fullCalendar('unselect');
					},
			});
	});
});

</script>

</body>
</html>
