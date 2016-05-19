<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>
<html>
<head>
<title>Upload File Request Page</title>
</head>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.0/jquery.min.js"></script>
<body>
 
    <form method="POST" action="uploadFile" enctype="multipart/form-data">
        Ficheiro a submeter <input type="file" name="file"><br /> 
        Nome: <input type="text" name="name"><br /> <br /> 
        <input type="submit" value="Upload"> Carrega aqui para fazer upload
    </form>
    
    <input type="submit" value="Get File List" onclick="wow()">
    <br>
    <br>
    <div id="files"></div>
    <form method="POST" action="getFile">
        Nome: <input type="text" name="name"><br /> <br /> 
        <input type="submit" value="Download"> Carrega aqui para sacar o ficheiro
    </form>

<script>
function wow(){
	path=window.location.host
	$.getJSON('https://'+ path +'/ListFiles',function(data){
		data.forEach(function(entry) {
			$("#files").append("<p>"+ entry +"</p>")
		});
		
		})

}
</script>
</body>
</html>