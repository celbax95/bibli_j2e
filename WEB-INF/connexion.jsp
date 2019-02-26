<%@ page pageEncoding="UTF-8" %>
<%@ page import ="java.util.*" %>
<!DOCTYPE html>

<%
	Boolean logIn = (Boolean) request.getAttribute("logIn");
%>

<html>
<head>
	<title></title>
</head>

<style type="text/css">

</style>

<body>
	<% if (logIn) { %>
		<h1>Connecté !</h1>
		<h2>Bienvenue </h2>

	<% } else { %>
		<h1>Pseudo ou mot de passe invalide.</h1>
	<% } %>
	<p>Redirection en cours...</p>

	<%
		if (logIn)
			response.sendRedirect("/");
		else
			response.sendRedirect("/projet/log?error=1");
	%>

</body>
</html>