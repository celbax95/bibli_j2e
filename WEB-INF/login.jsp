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

	* {
		outline:0;
		margin : 0;
		padding:0;
		box-sizing : border-box;
	}

	html {
		height: 100%;
	}

	body {
		width: 100%;
		height: 100%;
		background-repeat: no-repeat;
		background-size: cover;
		background-position: right top;
		background-attachment: fixed;
		background-color: #383838;
	}

	#titre {
		width: 240px;
		border-bottom: 2px solid white;
		margin: auto;
		margin-bottom: 25px;
		padding-bottom: 8px; 
	}

	#titre h2 {
		display: block;
		color: white;
		font-size: 35px;
	}

	div.input {
		text-align: center;
		margin-bottom: 30px;
		font-size: 17px;
	}

	input {
		width: 200px;
		font-size: 18px;
		background-color: transparent;
		border: 0;
		border-bottom: 2px solid white;
		color: white;
		margin-left: 10px;
		padding: 3px;
		padding-left: 10px;
		text-shadow: 0 0 0.2px white;
	}

	.input {
		display: block;
	}

	label {
		color: white;
		text-shadow: 0 0 0.1px white;
		font-size: 18px;
	}

	body {
		text-align: center;
	}

	form {
		margin-top: 100px;
		display: inline-block;
		overflow: hidden;
		padding: 30px;
		border: 2px solid white;
		border-radius: 10%;
		box-shadow: 0 0 5px white;
	}

	button {
		background-color: white;
		border: none;
		width: 120px;
		height: 40px;
		margin: auto;
		margin-top: 10px;
		text-align: center;
		box-shadow: 0 5px rgb(200,200,200);
		border-radius: 20px;
		transition: 0.8s;
	}

	button:hover {
		background-color: rgb(240,240,240);
		box-shadow: 0 5px rgb(180,180,180);
	}

	button:active {
		transform: translateY(2px);
		box-shadow: 0 3px rgb(150,150,150);
		transition: 0.08s;
	}

	button p {
		display: block;
		text-align: center;
		font-size: 17px;
	}

	.space {
		margin-bottom: 15px;
		padding: 1px;
	}

	p.error {
		color: red;
	}
</style>

<body>
	<form action="/projet/connexion" method="get">
		<div id="titre"><h2>Connexion</h2></div>

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
			<button type="submit"><p>Valider</p></button>
		</div>

	</form>
</body>
</html>