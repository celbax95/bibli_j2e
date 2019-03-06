<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page import ="java.util.*" %>
<%
	String err = request.getParameter("error");
%>

<!DOCTYPE html>
<html>
<head>
	<title>Login Média2e</title>
	<link rel="stylesheet" type="text/css" href="./css/body.css">
	<link rel="stylesheet" type="text/css" href="./css/login.css">
</head>

<body>
	<form action="/bibli_j2e/connexion" method="get">
		<div id="titre"><h2>Connexion</h2></div>

		<% if (err != null && err.equals("1")) { %>
			<p class="error">Pseudo ou mot de passe incorrect.</p>
		<% } else {%>
			<div class="space"></div>
		<% } %>

		<div class="input">
			<label for="log">Pseudo :</label>
			<input type="text" placeholder="Pseudo" id="log" value="testb" name="log">
		</div>

		<div class="input">
			<label for="pwd">Mot de passe :</label>
			<input type="password" placeholder="Mot de passe" id="pwd" value="test" name="pwd">
		</div>

		<div class="button">
			<button type="submit"><p>Valider</p></button>
		</div>

	</form>
</body>
</html>