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

	#titre div {
		width: 100%;
		height: 100%;
		display: flex;
		flex-direction: column;
		justify-content: center;
	}

	#titre h1 {
		font-size: 60px;
		color: white;
		text-align: center;

	}

	#user {
		width: 220px;
		height: 80px;
		display: flex;
		float: left;
		margin-top: 10px; 
		margin-left: 20px;
		overflow: hidden;
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

	#content {
		width: 100%;
		height: 900px;
		margin: auto;
	}

	#content #mab {
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

	#content #mab:hover {
		background-color: rgb(240,240,240);
		box-shadow: 0 8px rgb(180,180,180);

	}

	#content #mab:active {
		transform: translateY(5px);
		box-shadow: 0 5px rgb(150,150,150);
		transition: 0.08s;
	}

	#content a {
		text-decoration: none
	}
	#content #mab p {
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
		<div></div>
		<div><p>Utilisateur</p></div>


	</div>
	<div id="titre"><div>
		<h1>Bibli2e</h1>
	</div></div>
	<div id="content">
		<a href=""><div id="mab"><p>Ma bibliothèque</p></div></a>
		<div class="space"></div>
		<a href=""><div id="mab"><p>Mes emprunts</p></div></a>
	</div>
</body>
</html>