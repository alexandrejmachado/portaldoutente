<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="pt">
​
<head>
​
	 <title>Portal do Utente</title>
​
​
    
   
​
</head>
<style>
.ui-autocomplete { max-height: 200px; overflow-y: auto; overflow-x: hidden;}
</style>
​
<body background="print5.jpg" > 
​
​
​
​
        <input id = "primeira" value="${utenteName }">
​
​
​
        <input id = "segunda" value="${utenteTelemovel}">
​
​
​
        <input id = "terceira" value="${nomeMedicamento}">
​
​
​
        <input id = "quarta" value="${dose}">
​
​
        <input id = "quinta" value="${extenso}">
​
​
​
        <textarea id = "sexta" rows="2" cols="40">
            <p>Receita Médica No.: <c:out value="${medicacaoID }"></c:out></p>
            <p>Local de Prescricao: <c:out value="${instituicao }"></c:out></p>
            <p>Médico Prescritor: <c:out value="${nomeMedico }"></c:out></p>
        </textarea>
​
​
​
        <textarea id = "setima" rows="2" cols="40">
            1!!!
        </textarea>
​
​
​
        <textarea id = "oitava" rows="1" cols="40">
            1!!!
        </textarea>
​
​
​
        <textarea id = "nona" rows="1" cols="28">
            1!!!
        </textarea>
​
​
​
        <textarea id = "decima" rows="1" cols="1">
            1!!!
        </textarea>
​
​
​
        <textarea id = "decima1" rows="1" cols="1">
            1!!!
        </textarea>
​
​
</body>
​
<style>
​
​
​
body #primeira{
    position: absolute;
    margin-top: 50px;
    margin-left: 37px;
​
​
}
​
body #segunda{
    position: absolute;
    margin-top: 72px;
    margin-left: 37px;
}
​
 body #terceira{
    position: absolute;
    margin-top: 213px;
    margin-left: 19px;
}
​
body #quarta{
    position: absolute;
    margin-top: 213px;
    margin-left: 209px;
}
​
body #quinta{
    position: absolute;
    margin-top: 213px;
    margin-left: 217px;
}
​
body #sexta{
    position: absolute;
    margin-top: 28px;
    margin-left: 376px;
}
​
body #setima{
    position: absolute;
    margin-top: 63px;
    margin-left: 376px;
}
​
body #oitava{
    position: absolute;
    margin-top: 98px;
    margin-left: 376px;
}
​
body #nona{
    position: absolute;
    margin-top: 137px;
    margin-left: 387px;
}
​
body #decima{
    position: absolute;
    margin-top: 137px;
    margin-left: 658px;
}
​
body #decima1{
    position: absolute;
    margin-top: 137px;
    margin-left: 684px;
}
​
​
body 
    {
        height: 500px;
        width: 720px;
        background-size: auto 100%;
        background-repeat: no-repeat;
        background-position: left top;
    }
​
​
</style>
​
​