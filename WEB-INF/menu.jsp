<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page import ="java.util.*" %>
<%@ page import ="user.User" %>

<%
	String[][] b = (String[][]) request.getAttribute("b");
	String nomUser = ((User)request.getSession().getAttribute("user")).getLogin();
	nomUser = Character.toUpperCase(nomUser.charAt(0)) + nomUser.substring(1, nomUser.length());
%>

<!DOCTYPE html>
<html>
<head>
	<title>Menu</title>
	<link rel="stylesheet" type="text/css" href="./css/body.css">
	<link rel="stylesheet" type="text/css" href="./css/user.css">
	<link rel="stylesheet" type="text/css" href="./css/titre.css">
	<link rel="stylesheet" type="text/css" href="./css/menu.css">
</head>

<style type="text/css">
	#content {
		width: 100%;
		height: 900px;
		margin: auto;
	}

	#content .mab {
		background-color: white;
		width: 400px;
		height: 70px;
		margin: auto;
		margin-top: 12px;
		margin-bottom: 12px;
		text-align: center;
		display: flex;
		flex-direction: column;
		justify-content: center;
		box-shadow: 0 8px rgb(200,200,200);
		border-radius: 20px;
		transition: 0.8s;
	}

	#content .mab:hover {
		background-color: rgb(240,240,240);
		box-shadow: 0 8px rgb(180,180,180);

	}

	#content .mab:active {
		transform: translateY(5px);
		box-shadow: 0 5px rgb(150,150,150);
		transition: 0.08s;
	}

	#content a {
		display: block;
		width: 400px;
		height: 70px;
		margin: auto;
		text-decoration: none;
	}
	#content .mab p {
		font-size: 22px;
		font-weight: bold;
		text-decoration: none;
		color: black;
	}

	#content .space {
		margin-top: 50px; 
	}
</style>
<body>
	<div id="user">
		<a href="/bibli_j2e/log">
			<div></div>
			<div><p><%=nomUser%></p></div>
		</a>
	</div>

	<div id="titre"><a href="/bibli_j2e/menu">
	<h1>Média2e</h1>
	</a></div>
	<div id="content">

		<% for (int i = 0, nB = b.length; i < nB; i++) { %>

		<a href=<%=b[i][1]%>><div class="mab"><p><%=b[i][0]%></p></div></a>

		<% if (i+1 < nB) %>
		<div class="space"></div>

		<%}%>
	</div>
</body>
</html>