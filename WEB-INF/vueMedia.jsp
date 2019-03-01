<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page import ="java.util.*" %>
<%@ page import ="user.User" %>
<%@ page import ="mediatheque.Document" %>

<%
	List<Document> docs = (List<Document>)request.getAttribute("docs");
	String nomUser = ((User)request.getSession().getAttribute("user")).getLogin();
	nomUser = Character.toUpperCase(nomUser.charAt(0)) + nomUser.substring(1, nomUser.length());
	
	String rech = request.getParameter("rech")!=null?(String)request.getParameter("rech").toLowerCase():null;
	String[] rechT = rech!=null?rech.split("\\+"):null;

	Boolean bibli = (Boolean)request.getAttribute("bibli");
%>

<!DOCTYPE html>
<html>
<head>
	<title>Vue Mediatheque</title>
	<link rel="stylesheet" type="text/css" href="./css/body.css">
	<link rel="stylesheet" type="text/css" href="./css/user.css">
	<link rel="stylesheet" type="text/css" href="./css/titre.css">
	<link rel="stylesheet" type="text/css" href="./css/vueMedia.css">
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
		<div id="list">
			<form action="" method="get" id="rechForm">
				<label for="rech">Rechercher : </label>
				<input id="rech" type="text" name="rech" value="<%=rech!=null?rech:""%>">
				<button type="submit"></button>
			</form>

			<div id="docList">
				<%for (Document d : docs) {
				Object[] o = d.affiche();
				
				if (rechT != null) {
					boolean ct = false;
					for (int i = 0, c = o.length; i < c; i++) {
						for (int j = 0, c2 = rechT.length; j < c2; j++) {
							if (o[i].toString().toLowerCase().contains(rechT[j])) {
								ct = true;
								break;
							}
						}
						if (ct)
							break;
					}
					if (!ct)
						continue;
				}

				%>

				<div class="doc <%=(Boolean)o[0]?"grayed":""%>">
					<div class="stdDocInfo">
						<div class="inline cCourt"><p><%=o[2].toString()%></p></div>
						<div class="inline cLong"><p><%=o[3].toString()%></p></div>
						<div class="inline cLong"><p><%=o[4].toString()%></p></div>
						<div class="inline cCourt"><p><%=o[5].toString()%></p></div>
					</div>
					<div class="allDocInfo">
						<div class="inlineInfo">
							<pre><%=o[2].toString()%> :</pre>
							<%for (int i = 3, c2 = o.length; i < c2; i++) {%>
								<pre> <%=o[i].toString() + (((i+1)<c2)?",":"")%></pre>
							<%}%>
							<%if (!bibli) {%>
							<div class="action"><a href="<%if (!(boolean)o[0]) {%>
								?idDoc=<%=o[1].toString()%><%} else {%>#<%}%><%=rech!=null?"&rech="+rech:""%>"
								><div class="btn <%if (!(boolean)o[0]){%>active<%} else {%>blocked<%}%>"><p>Emprunter</p></div></a></div>
							<%}%>
						</div>
					</div>
				</div>
				<%}%>
			</div>
		</div>
	</div>
</body>
</html>