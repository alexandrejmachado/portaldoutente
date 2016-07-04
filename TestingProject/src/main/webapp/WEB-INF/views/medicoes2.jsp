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
	<link href='https://fonts.googleapis.com/css?family=Roboto:400,300,100' rel='stylesheet' type='text/css'>
	
    <!-- Custom CSS -->
    <link href=<c:url value="/resources/css/main1.css" ></c:url> rel="stylesheet">

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
							<h1>Medições</h1>
																								
							<div class="col-lg-12">
							
							 <p id="frase">Preencha aqui os dados que pretende inserir no Sistema</p>

							<div class="registo">
										<p style="color:black">Escolha a Medição:</p>><br>
								        <select id="medidas" onclick="testing()" style="color:black;">
									    </select>
									
										<form id="campos" method="post"></form>
										
										<input id = "botao_reg" type="submit" onclick="aquivoueu()">
									  	<button id = "botao_voltar" onclick= "verMedida()" >Histórico</button>
							</div>
							
							<div id="graph"></div>
							<script src='resources/js/jquery.js'></script>
							
							<script>
							path=window.location.href;
							medida=""
							$("#medidas").change(function(){testing();});
								function testing(){
									var e = document.getElementById("medidas");
									medida=e.options[e.selectedIndex].value;
								$.getJSON( path + "/daoInputs/" + medida, function( data ) {
							  var items = [];
							  $("#campos").html("");
							  $.each( data, function( key, val ) {
							    if(key!="data"){items.push( key + ":\n <input class='campo' name='" + key + "'><br>" );}
							  });
							  $("#campos").append(items);
							  })
							  }
							
							  function getthem(){
								$.getJSON( path + "/avaliableMeasures", function( val ) {
							  var items = [];
							  console.log(val);
							   for(i=0;i<val.length;i++){
								   ceas=val[i];
							    items.push( "<option id = 'opcao' value=" + ceas + ">" + ceas + "</option>" );
							 	}
							  $("#medidas").append(items);
							  })
							  }
							  getthem();
							
							  function aquivoueu()
							  {
							    var response={};
							    response["medida"]=medida;
							    $("form").serializeArray().map(function(x){response[x.name] = x.value;});
							    $.ajax({
							    url:path + "/guardar",
							    type:"POST",
							    contentType: "application/json; charset=utf-8",
							    data: JSON.stringify(response), //Stringified Json Object
							    async: false,    //Cross-domain requests and dataType: "jsonp" requests do not support synchronous operation
							    cache: false,    //This will force requested pages not to be cached by the browser
							     processData:false, //To avoid making query String instead of JSON
							     success: function(resposeJsonObject){
							        alert(resposeJsonObject);
							    }
							    });
							  }
							  $(document).ready(function(){
								  $('#login-trigger').click(function(){
								    $(this).next('#login-content').slideToggle();
								    $(this).toggleClass('active');          
								    
								    if ($(this).hasClass('active')) $(this).find('span').html('&#x25B2;')
								      else $(this).find('span').html('&#x25BC;')
								    })
								});
							  function verMedida(medida2)
							  {
								  $("#peso").load("https://"+window.location.host+"/visualizar/"+ medida2);
							  }
							</script>
							</div> 							
							
							<!-- --DROPDOWN MENUS-- -->		
							<div class="container">
							<div class="panel-group col-md-8" id="accordion">
							
							  <div class="panel panel-default">
							    <div class="panel-heading">
							      <h4 class="panel-title">
							        <a class="accordion-toggle" data-toggle="collapse" data-parent="#accordion" href="#collapseOne">
							          <span class="glyphicon glyphicon-minus"></span>
							          Peso
							        </a>
							      </h4>
							    </div>
							    <div id="collapseOne" class="panel-collapse collapse in" onclick='verMedida("Peso")'>
							      <div id="peso" class="panel-body">
							      </div>
							    </div>
							  </div>
							  
							  <div class="panel panel-default">
							    <div class="panel-heading">
							      <h4 class="panel-title">
							        <a class="accordion-toggle" data-toggle="collapse" data-parent="#accordion" href="#collapseTwo">
							          <span class="glyphicon glyphicon-plus"></span>
							          Altura
							        </a>
							      </h4>
							    </div>
							    <div id="collapseTwo" class="panel-collapse collapse">
							      <div class="panel-body">
							      TEXTO
							        </div>
							     </div>
							  </div>
							  
							  <div class="panel panel-default">
							    <div class="panel-heading">
							      <h4 class="panel-title">
							        <a class="accordion-toggle" data-toggle="collapse" data-parent="#accordion" href="#collapseThree">
							          <span class="glyphicon glyphicon-plus"></span>
							          Colesterol
							        </a>
							      </h4>
							    </div>
							    <div id="collapseThree" class="panel-collapse collapse">
							      <div class="panel-body">
							        TEXTO
							      </div>
							    </div>
							  </div>
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
    
	<script>
	$('.collapse').on('shown.bs.collapse', function(){
	$(this).parent().find(".glyphicon-plus").removeClass("glyphicon-plus").addClass("glyphicon-minus");
	}).on('hidden.bs.collapse', function(){
	$(this).parent().find(".glyphicon-minus").removeClass("glyphicon-minus").addClass("glyphicon-plus");
	});
    </script>
    
</body>

</html>