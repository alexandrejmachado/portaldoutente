<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="pt">
​
<head>
	 <title>Portal do Utente</title>
	 <link href= <c:url value="/resources/css/main.css" ></c:url> rel="stylesheet"/>
</head>

<style>
.ui-autocomplete { max-height: 200px; overflow-y: auto; overflow-x: hidden;}
</style>

<body style="background:  url('/resources/gfx/print5.jpg'); background-repeat: no-repeat; width: 915px; height: 652px; background-position: left top; margin:0px;"> 
​

<textarea id = "primeira" style="position:absolute; margin-top: 79px; margin-left: 43px;">​<c:out value="${utenteName}"></c:out></textarea>
<textarea id = "segunda" style="position: absolute; margin-top: 107px;margin-left: 46px;height:20px;">​<c:out value="${utenteTelemovel}"></c:out> </textarea>

<textarea id = "terceira" style="position: absolute; margin-top: 280px; margin-left: 28px;">​<c:out value="${nomeMedicamento}"></c:out></textarea>

<textarea id = "quarta" style="position: absolute; margin-top: 280px; margin-left: 271px;">​<c:out value="${dose}"></c:out></textarea>

<textarea id = "quinta" style="position: absolute; margin-top: 280px; margin-left: 297px;">
​<c:out value="${extenso}"></c:out></textarea>

<textarea id = "sexta" rows="2" cols="40" style="position: absolute; margin-top: 41px; margin-left: 478px;height: 122px;width: 423px;">
Receita Médica No.: <c:out value="${medicacaoID }"></c:out>
Local de Prescricao: <c:out value="${instituicao }"></c:out>
Médico Prescritor: <c:out value="${nomeMedico }"></c:out>
Contacto Telefónico da Instituição: <c:out value="${contactoInstituicao }"></c:out>
Dados do Utente
Nome do Utente: <c:out value="${utenteName }"></c:out>
Entidade Responsável: CEDS/EHIC
Nº do Beneficiário: <c:out value="${utenteID }"></c:out>            
</textarea>
​
<textarea id = "nona" style="position: absolute; margin-top: 186px; margin-left: 494px;">
<c:out value="${nomeMedicamento}"></c:out>
</textarea>
<textarea id = "decima" style="position: absolute; margin-top: 185px; margin-left: 846px;">
<c:out value="${dose}"></c:out>
</textarea>
<textarea id = "decima1" style="position: absolute; margin-top: 185px; margin-left: 874px;">
<c:out value="${extenso}"></c:out>
</textarea>
</body>

<style>
input,input:hover,textarea,textarea:hover
{
	border:0px;
	overflow-y:hidden;
}
@media print {
body {
    -webkit-print-color-adjust: exact; 
}}

</style>