<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ page import="java.util.List, br.com.alura.gerenciador.model.Empresa"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Curso JSP - Listagem Empresas</title>
</head>
<body>
	Usuario Logado: ${usuarioLogado.login } </br>
	<c:import url="logout-parcial.jsp"/>
    <br>
    <br>
	<a href="/gerenciador/entrada?acao=NovaEmpresaForm">Cadastrar Nova Empresa</a></br></br>
	<c:if test="${not empty empresa}">
            Empresa ${ empresa } cadastrada com sucesso!</br></br>
    </c:if>
	Lista de empresas: <br/>
	<ul>
		<c:forEach items="${empresas}" var="empresa">
			<li>
				${empresa.nome} - <fmt:formatDate value="${empresa.dataAbertura}" pattern="dd/MM/yyyy"/>
				<a href="/gerenciador/entrada?acao=MostrarEmpresa&id=${empresa.id}">Editar</a>
				<a href="/gerenciador/entrada?acao=RemoverEmpresa&id=${empresa.id}">Remover</a>
			</li>
		</c:forEach>
	</ul>
</body>
</html>