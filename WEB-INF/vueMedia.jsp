<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page import ="java.util.*" %>
<%@ page import ="user.User" %>
<%@ page import ="mediatheque.Document" %>

<%
	List<Document> docs = (List<Document>)request.getAttribute("docs");
	String nomUser = ((User)request.getSession().getAttribute("user")).getLogin();
	nomUser = Character.toUpperCase(nomUser.charAt(0)) + nomUser.substring(1, nomUser.length());

%>

<!DOCTYPE html>
<html>
<head>
	<title>Vue Mediatheque</title>
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
		width: 500px;
		height: 130px;
		background-color: rgba(255,255,255,0.05);
		margin: auto;
		margin-top: 30px;
		margin-bottom: 150px;
		border: 7px solid white;
		border-radius: 50px;
		box-shadow: 0 0 10px gray;
	}

	#titre a {
		width: 100%;
		height: 100%;
		display: flex;
		flex-direction: column;
		justify-content: center;
		text-decoration: none;
	}

	#titre h1 {
		font-size: 60px;
		color: white;
		text-align: center;

	}

	#user {
		width: 220px;
		height: 80px;
		float: left;
		margin-top: 10px; 
		margin-left: 20px;
		overflow: hidden;
	}

	#user a {
		width: 100%;
		height: 100%;
		display: flex;
		text-decoration: none;
	}

	#user div:first-child {
		background-image: url("./avatar_defaut.png");
		background-size: cover;
		margin-top: 10px;
		margin-left: 10px; 
		width: 60px;
		height: 60px;
		border: 1px solid white;
		border-radius: 50%;
		box-shadow: 0 0 5px rgba(255,255,255,0.1);
	}

	#user div:nth-child(2) {
		display: flex;
		margin-top: 10px;
		margin-left: 20px;
		flex-direction: column;
		justify-content: center;
	}
	#user div:nth-child(2) p {
		color: white;
		font-size: 25px;
		font-weight: bold;
		padding-bottom: 5px;
		border-bottom: 1.5px solid white;
		text-shadow: 0 0 5px rgba(255,255,255,0.1);
	}


	@media screen and (max-width: 975px) {
		#user {
			display: none;
		}
	}

	.inline {
		display: inline-block;
	}

	#content {
		width: 100%;
		height: 900px;
		margin: auto;
	}

	#rechForm label {
		font-size: 18px;
	}

	#rechForm input#rech {
		border: none;
		background-color: transparent;
		color: white;
		font-size: 17px;
		border-bottom: 1px solid white;
		padding-bottom: 2px;
		padding-left: 5px;
		margin-left: 5px;
	}

	#rechForm button {
		background-color: transparent;
		border: none;
		width: 20px;
		height: 20px;
		background-image: url("./loupe.png");
		background-size: cover;
	}

	#content #list {
		background-color: rgba(255,255,255,0.15);
		width: 900px;
		margin: auto;
		padding: 20px;
		padding-top: 30px;
		padding-bottom: 30px;
		border: 5px solid lightgray;
		border-radius: 30px;
	}

	#content #list form {
		color: white;
		text-align: center;
		margin-bottom: 30px;
	}

	#content #docList {
		width: 600px;
		margin:auto;
		text-align: center;
		color: white;
	}

	#content #docList .doc:not(:nth-child(1)) {
		margin-top: 10px;
	}

	#content.doc .cCourt {
		width: 80px;
	}
	#content .doc .cMoy {
		width: 120px;
	}
	#content .doc .cLong {
		width: 200px;
	}

	.doc {
		position: relative;
		padding-top: 2px;
		padding-bottom: 2px;
		border: 2px solid black;
		border-radius: 8px;
		transition: 1s;
	}

	.doc p, .doc pre {
		font-family: "Times";
		font-size: 18px;
	}

	.allDocInfo {
		background-color: gray;
		z-index: 1;
		float: left;
		position: absolute;
		top: -20px;
		left: -5%;
		width: 110%;
		padding: 8px;
		border: 2px solid black;
		border-radius: 8px;
		visibility: hidden;
		opacity: 0;
		transition-duration: 0.5s;
		transition-delay: 0.5s;
		overflow: hidden;
	}

	.doc:active .allDocInfo {
		transition-delay: 0s;
		visibility: visible;
		opacity: 1;
	}

	.doc .allDocInfo:hover {
		visibility: visible;
		opacity: 1;
	}

	.inlineInfo {
		margin-top: 4px;
	}

	.inlineInfo pre {
		display: inline-block;
	}

	.action {
		margin-top: 18px;
		margin-bottom: 6px;
	}

	.btn {
		background-color: white;
		width: 120px;
		height: 40px;
		margin: auto;
		text-align: center;
		display: flex;
		flex-direction: column;
		justify-content: center;
		box-shadow: 0 4px rgb(200,200,200);
		border-radius: 22px;
		transition: 0.8s;
	}
	#content .btn:hover {
		background-color: rgb(240,240,240);
		box-shadow: 0 4px rgb(180,180,180);
	}
	#content .btn:active {
		transform: translateY(2px);
		box-shadow: 0 2px rgb(150,150,150);
		transition: 0.08s;
	}
	#content a {
		display: block;
		width: auto;
		height: auto;
		overflow: hidden;
		padding-bottom: 5px;
		margin: auto;
		text-decoration: none;
	}
	#content .btn p {
		font-size: 16px;
		font-weight: bold;
		text-decoration: none;
		color: black;
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
		<div id="list">
			<form action="#" method="get" id="rechForm">
				<label for="rech">Rechercher : </label>
				<input id="rech" type="text" name="rech">
				<button type="submit"></button>
			</form>

			<div id="docList">
				<%for (Document d : docs) {
				Object[] o = d.affiche();
				%>
				<div class="doc">
					<div class="stdDocInfo">
						<div class="inline cCourt"><p><%=o[1].toString()%></p></div>
						<div class="inline cLong"><p><%=o[2].toString()%></p></div>
						<div class="inline cLong"><p><%=o[3].toString()%></p></div>
						<div class="inline cCourt"><p><%=o[4].toString()+"€"%></p></div>
					</div>
					<div class="allDocInfo">
						<div class="inlineInfo">
							<pre><%=o[1].toString()%> :</pre>
							<%for (int i = 2, c = o.length; i < c; i++) {%>
								<pre> <%=o[i].toString() + (((i+1)<c)?",":"")%></pre>
							<%}%>
							<div class="action"><a href="#"><div class="btn"><p>Emprunter</p></div></a></div>
						</div>
					</div>
				</div>
				<%}%>
			</div>
		</div>
	</div>
</body>
</html>