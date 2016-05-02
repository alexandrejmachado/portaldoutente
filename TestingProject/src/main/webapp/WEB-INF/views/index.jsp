<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

<head>

    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="author" content="">

    <title>Portal do Utente Mega Fixe</title>

    <!-- Bootstrap Core CSS -->
    <!-- Teste 5 Escondido para verificar se a imagem é actualizada-->
    
    <!-- Two options -->
   
    <!-- Custom CSS -->
    <link href= <c:url value="/resources/css/bootstrap.min.css" ></c:url> rel="stylesheet"/>
    
    <link href= <c:url value="/resources/css/main.css" ></c:url> rel="stylesheet"/>
    

    <!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
    <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
    <!--[if lt IE 9]>
        <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
        <script src="https://oss.maxcdn.com/libs/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->

</head>

<body background=<c:url value="/resources/gfx/Final2.png"/> />

<div class= "container">    <!-- ROW -->
        <div class= "rowMajor">
            <div class="col-md-11">Portal do Utente</div>
		</div>
		
<nav>
  <ul>
    <li id="login">
      <a id="login-trigger" href="#">
        Entrar <span>▼</span>
      </a>
      <div id="login-content">
        <form action="/loginUtente" method="post" id="login">
          <fieldset id="inputs">
            <input id="username" type="text" pattern="\d*" maxlength="9" name="username" placeholder="NºUtente" required>   
            <input id="password" type="password" name="password" placeholder="Palavra-passe" required>
          </fieldset>
          <fieldset id="actions">
          </fieldset>
        </form>
        <input type="submit" id="submit" value="Log in" onclick="lolada()">
        <a button href= "/registo" id = "aaa">Registe-se</button></a>
      </div>                     
    </li>
  </ul>
</nav>
 

		<div  class="col-xs-4">
			<p id="texto1">No Portal do Utente</p>
			<p id="texto2">pode marcar consultas</p>
			<div id="icons" class ="barraIcons">
				<div class="col-xs-3">
				</div>
				<div class="col-xs-2"> <img src=<c:url value="/resources/gfx/mini4.png"/>  style="width:50px">
				</div>
				<div class="col-xs-2"> <img src=<c:url value="/resources/gfx/mini2.png"/>  style="width:50px">
				</div>
				<div class="col-xs-2"> <img src=<c:url value="/resources/gfx/mini3.png"/>  style="width:50px">
				</div>
				<div class="col-xs-3">
				</div>
				
			</div>
		</div>
		
			
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

	<script>
	path=window.location.href;
	warning=null;
	function lolada(){
		$.post(path + 'loginUtente', $("form").serialize()).done(function( data ) { 
			if (data=="true"){window.location.replace(path + "index");}
			else if (data=="activate"){window.location.replace(path + "activate");}
			else if(warning==null){ $("#login-content").append('<div class="alert alert-danger fade in" role="alert" style="margin-top: 10%;">NºUtente ou Contra Senha Incorrecta</div>');
			warning=true;}
			
			});
		}
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
