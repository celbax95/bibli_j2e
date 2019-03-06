<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page import ="java.util.*" %>
<%@ page import ="user.User" %>
<%@ page import ="mediatheque.Document" %>

<%
	List<Document> docs = (List<Document>)request.getAttribute("docs");
	String nomUser = ((User)request.getSession().getAttribute("user")).getLogin();
	nomUser = Character.toUpperCase(nomUser.charAt(0)) + nomUser.substring(1, nomUser.length());

	String docType = (String)request.getAttribute("docType");
	List<String> types = (List<String>)request.getAttribute("types");

	List<String> colN = (List<String>)request.getAttribute("colN");
	List<String> colT = (List<String>)request.getAttribute("colT");

	Boolean bibli = (Boolean)request.getAttribute("bibli");
%>

<!DOCTYPE html>
<html>
<head>
	<title>Ajout document</title>
	<link rel="stylesheet" type="text/css" href="./css/body.css">
	<link rel="stylesheet" type="text/css" href="./css/user.css">
	<link rel="stylesheet" type="text/css" href="./css/titre.css">
</head>

<style type="text/css">
	#content {
		width: 100%;
		height: 900px;
		margin: auto;
	}

	#content2 {
		background-color: rgba(255,255,255,0.15);
		width: 900px;
		margin: auto;
		padding: 20px;
		padding-top: 30px;
		padding-bottom: 30px;
		border: 5px solid lightgray;
		border-radius: 30px;
	}

	#content div.list {
		margin: auto;
		width: 150px;
		position: relative;
		border: 1px solid white;
		border-radius: 8px;
	}
	#content div.list:hover {
		border: 1px solid white;
		border-bottom: 0;
		border-radius: 8px 8px 0 0;
	}

	#content div.headList {
		background-color: rgb(20,20,20);
		display: flex;
		height: 50px;
		flex-direction: column;
		justify-content: center;
		border-radius: 8px;
	}
	#content div.list:hover div.headList {
		border-radius: 8px 8px 0 0;
		border-bottom: 1px solid rgb(250,250,250);
	}

	#content div.list div.headList p {
		text-align: center;
		color: rgba(255,255,255,0.5);
		transition: 0.2s;
	}
	#content div.list:hover div.headList p {
		color: rgba(255,255,255,0.8);
	}

	#content div.list div.noScroll {
		top: 50px;
		background-color: rgb(50,50,50);
		position: absolute;
		left: -1px;
		width: calc(100% + 2px);
		height: auto;
		float: left;
		visibility: hidden;
		opacity: 0;
		overflow: hidden;
		border: 1px solid white;
		border-top: 0;
		border-radius: 0 0 8px 8px;
	}
	#content div.list:hover div.noScroll {
		visibility: visible;
		opacity: 1;
		transition: 0.2s;
	}

	#content div.list div.noScroll ul {
		width: calc( 100% + 1.4vw);
		height: 100%;
		overflow-y: scroll; 
	}

	#content div.list div.noScroll ul a li {
		display: flex;
		flex-direction: column;
		justify-content: center;
		height: 50px;
		list-style: none;
		width: 100%;
		text-align: center;
		color: white;
	}
	#content div.list div.noScroll ul a:not(:first-child) li {
		border-top: 1px solid rgb(250,250,250);
	}
	#content div.list div.noScroll ul a li:hover {
		background-color: rgba(255,255,255,0.1);
	}

	#content div.list div.noScroll ul a {
		text-decoration: none;
	}

	#content #addDoc {
		width: 500px;
		margin: auto;
		margin-top: 40px;
	}

	#content .input label {
		width: 150px;
		display: inline-block;
		color: white;
		font-size: 18px;
		height: 30px;
	}
	#content .input input {
		background-color: rgb(200,200,200);
		width: 345px;
		font-size: 17px;
		padding: 2px;
		padding-left: 8px;
		padding-right: 8px;
		border: 1px solid black;
		border-radius: 5px;
	}

	#content #submit {
		width: 100%;
		text-align: center;
	}

	#content #addDoc button {
		background-color: white;
		border: none;
		width: 120px;
		height: 40px;
		margin-top: 40px;
		text-align: center;
		box-shadow: 0 5px rgb(200,200,200);
		border-radius: 20px;
		transition: 0.8s;
	}

	#content #addDoc button:hover {
		background-color: rgb(240,240,240);
		box-shadow: 0 5px rgb(180,180,180);
	}

	#content #addDoc button:active {
		transform: translateY(2px);
		box-shadow: 0 3px rgb(150,150,150);
		transition: 0.08s;
	}

	#content #addDoc button p {
		display: block;
		text-align: center;
		font-size: 17px;
	}
</style>

<script type="text/javascript">
	window.onload = function() {
		var l = document.querySelector("#content div.list div.noScroll");
		if (l.offsetHeight > 200)
			l.style.height = "200px";
	};
</script>

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
		<div id="content2">
			<div class="list">
				<div class="headList">
					<p><%=docType==null?"Document":docType%></p>
				</div>
				<div class="noScroll">
					<ul>
						<%for (int i = 0, c = types.size(); i < c; i++) {
						String t = types.get(i);%>
							<a href="?type=<%=t%>"><li>
								<%=Character.toUpperCase(t.charAt(0)) + t.substring(1, t.length())%>
							</li></a>
						<%}%>
					</ul>
				</div>
			</div>
			<%if (colN != null) {%>
			<form id="addDoc" action="/bibli_j2e/vueAddDoc" method="get">
				<div class="input">
					<%for (int i = 0, c = colN.size(); i < c; i++) {%>
						<label for="<%=colN.get(i)%>"><%=Character.toUpperCase(colN.get(i).charAt(0)) + colN.get(i).substring(1, colN.get(i).length())%> :</label>
						<input type="text" name="<%=colN.get(i)%>">
					<%}%>
				</div>
				<div id="submit">
					<button id="submitButton" type="submit"><p>Valider</p></button>
				</div>
			</form>
			<%}%>
		</div>
	</div>
</body>
</html>