<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page import ="java.util.*" %>
<%@ page import ="user.User" %>
<%@ page import ="mediatheque.Document" %>

<%
	List<Document> docs = (List<Document>)request.getAttribute("docs");
	String nomUser = ((User)request.getSession().getAttribute("user")).getLogin();
	nomUser = Character.toUpperCase(nomUser.charAt(0)) + nomUser.substring(1, nomUser.length());

	String type = (String)request.getParameter("type");
	List<String> types = (List<String>)request.getAttribute("types");

	List<String> colN = (List<String>)request.getAttribute("colN");
	List<String> colT = (List<String>)request.getAttribute("colT");

	List<Integer> err = (List<Integer>)request.getAttribute("err");

	String ajout = (String)request.getParameter("ajout");

	Boolean bibli = (Boolean)request.getAttribute("bibli");
%>

<!DOCTYPE html>
<html>
<head>
	<title>Ajout document</title>
	<link rel="stylesheet" type="text/css" href="./css/body.css">
	<link rel="stylesheet" type="text/css" href="./css/user.css">
	<link rel="stylesheet" type="text/css" href="./css/titre.css">
	<link rel="stylesheet" type="text/css" href="./css/vueAddDoc.css">

	<script type="text/javascript" src="./javascript/vueAddDoc.js"></script>
</head>

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
					<p style="<%=(type==null?"":"color:white;")%>"><%=type==null?"Document":type%></p>
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

			<%if (ajout != null && ajout.equals("1")) {%>
			<div id="ajoute">
				<p>Document ajouté</p>
			</div>
			<%}%>

			<%if (colN != null) {%>
			<form id="addDoc" action="/bibli_j2e/vueAddDoc" method="get">
				<input type="hidden" name="type" value="<%=type%>">
				<input type="hidden" name="askVerif" value="1">
				<%for (int i = 0, c = colN.size(); i < c; i++) {%>
					<div class="input">
						<label for="<%=colN.get(i)%>"><%=Character.toUpperCase(colN.get(i).charAt(0)) + colN.get(i).substring(1, colN.get(i).length())%></label>
						<div id="centerinp"><input type="text" name="<%=colN.get(i)%>"
							placeholder="- <%=Character.toUpperCase(colN.get(i).charAt(0)) + colN.get(i).substring(1, colN.get(i).length())%> -"
							value="<%=request.getParameter(colN.get(i))==null?"":request.getParameter(colN.get(i))%>"
							style="<%=(err != null && err.contains(i))?"border-color:red;":""%>"></div>
					</div>
				<%}%>
				<div id="submit">
					<button id="submitButton" type="submit"><p>Valider</p></button>
				</div>
			</form>
			<%}%>
		</div>
	</div>
</body>
</html>