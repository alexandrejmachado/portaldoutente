<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
        
<!DOCTYPE HTML>
<html lang="pt">

    <head>
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <meta name="description" content="">
        <meta name="author" content="">
        <meta charset="utf-8">
        
        <link href= <c:url value="/resources/css/bootstrap.min.css" ></c:url> rel="stylesheet"/>
    
        <link href= <c:url value="/resources/css/main.css" ></c:url> rel="stylesheet"/>
        
        <title>Portal do Utente</title>
    </head>
    
    <body background=<c:url value="/resources/gfx/Final2.png"/>>
        
    <select>
        <option value="08:00">08:00</option>
        <option value="11:30">11:30</option>
        <option value="14:00">14:00</option>
        <option value="16:30">16:30</option>
    </select>
    
    <textarea rows="4" cols="50">Observações</textarea>
    <p>Confirmar marcação da consulta para: ${data}</p>
    <button>
        Sim
    </button>
    
    <button>
        Não
    </button>
        
        
    
    
        <div id="wrapper">

			<!-- Sidebar -->
    
    </body>
    
</html>