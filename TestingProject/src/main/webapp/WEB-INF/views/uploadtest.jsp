<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
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
    <link href='https://fonts.googleapis.com/css?family=Roboto:400,300,100' rel='stylesheet' type='text/css'>
    
    
  	<script src="https://code.jquery.com/jquery-1.10.2.js"></script>
  	<script src="/resources/js/jquery.flexText.js"></script>
  	<script src="/resources/js/jquery.flexText.min.js"></script>
  	<script src="https://code.jquery.com/ui/1.11.4/jquery-ui.js"></script>

    <!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
    <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
    <!--[if lt IE 9]>
        <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
        <script src="https://oss.maxcdn.com/libs/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->

</head>

<body background=<c:url value="resources/gfx/Final2.png"/>/>
 
 <div class="container">
            
            <div class="col-lg-12"> 
                <div class= "rowMajor">
                	<div id="divisento"></div>
						<div class="col-md-6"><a id = "PortalDoUtente" href="/" style="text-decoration:none; color: white; font-weight:100">Portal do Utente</a></div>
						<div class="col-md-6" id="entrar" style="text-align: right;">${username}</div>
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
	<div id="page-content-wrapper">
	<div class="container-fluid">
    <form method="POST" action="uploadFile" enctype="multipart/form-data" id="medform">
        Ficheiro a submeter <br /> <input type="file" name="file"> <br>
        Descrição: <input type="text" name="tipo"><br/>
        <input type="submit" value="Upload"> 
    </form>
    
    <br>
    <br>
    <table id="files">
    	<tr>
    		<td>Nome</td>
    		<td>Data</td>
    		<td>Tipo de Exame</td>
    		<td>Download</td>
    	</tr>
    	<c:forEach items="${exames}" var="exame">
    	<tr>
    		<td><c:out value="${exame.getNome()}"/></td>
    		<td><c:out value="${exame.getDate() }"/></td>
    		<td><c:out value="${exame.getTipo() }"/></td>
    		<td>
    		<form action="getFile" method="POST">
    		<input type="text" name="name" style="display:none" value="${exame.getMetalink()}">
    		<input type="submit" value="Download Ficheiro">
    		</form>
    		</td>
    	</tr>
    	</c:forEach>
    </table>

</div></div>
<script>
function sacar(nome) {
	path="https://" + window.location.host + "/";
	$.post(path+"getFile", {"name": nome},function(data){
		
})}
</script>

</body>
</html>