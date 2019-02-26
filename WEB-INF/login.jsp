<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page import ="java.util.*" %>
<%
	String err = request.getParameter("error");
%>

<!DOCTYPE html>
<html>
<head>
	<title></title>
</head>

<style type="text/css">
	div {
		text-align: center;
		margin-bottom: 20px;
		font-size: 17px;
	}

	input {
		font-size: 18px;
	}

	.input {
		display: block;
	}

	body {
		text-align: center;
	}

	form {
		margin-top: 100px;
		display: inline-block;
		overflow: hidden;
		padding: 30px;
		border: 2px solid black;
		border-radius: 10%;
	}

	button {
		font-size: 17px;
	}

	h2 {
		text-decoration: underline;
		margin-bottom: 20px;
	}

	.button {
		margin-top: 30px;
	}

	.space {
		margin-bottom: 15px;
		padding: 1px;
	}

	p {
		color: red;
	}
</style>

<body>
	<form action="/projet/connexion" method="get">
		<h2>Connexion</h2>

		<% if (err != null && err.equals("1")) { %>
			<p class="error">Pseudo ou mot de passe incorrect.</p>
		<% } else {%>
			<div class="space"></div>
		<% } %>

		<div class="input">
			<label for="log">Pseudo :</label>
			<input type="text" placeholder="Pseudo" id="log" value="test" name="log">
		</div>

		<div class="input">
			<label for="pwd">Mot de passe :</label>
			<input type="password" placeholder="Mot de passe" id="pwd" value="test" name="pwd">
		</div>

		<div class="button">
			<button type="submit">Valider</button>
		</div>

	</form>
</body>
</html>