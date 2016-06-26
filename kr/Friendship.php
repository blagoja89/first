<!DOCTYPE HTML>
<!--
	Arcana 2.0 by HTML5 UP
	html5up.net | @n33co
	Free for personal and commercial use under the CCA 3.0 license (html5up.net/license)
-->

<html>
	<head>
		<title>Sharing directories</title>
		<meta http-equiv="content-type" content="text/html; charset=utf-8" />
		<meta name="description" content="" />
		<meta name="keywords" content="" />
		<link href="http://fonts.googleapis.com/css?family=Open+Sans+Condensed:300,300italic,700" rel="stylesheet" />
		<script src="js/jquery.min.js"></script>
		<script src="js/config.js"></script>
		<script src="js/skel.min.js"></script>
		<script src="js/skel-panels.min.js"></script>
		<noscript>
			<link rel="stylesheet" href="css/skel-noscript.css" />
			<link rel="stylesheet" href="css/style.css" />
			<link rel="stylesheet" href="css/style-desktop.css" />
		</noscript>
		<!--[if lte IE 9]><link rel="stylesheet" href="css/style-ie9.css" /><![endif]-->
		<!--[if lte IE 8]><script src="js/html5shiv.js"></script><![endif]-->
	</head>
	<body>

		<!-- Header -->
		


			<div id="header-wrapper">
				<header class="container" id="site-header">
					<div class="row">
						<div class="12u">
							<div id="logo">
								<h1>Share<img width="69" height="64" name="logo" src="images/file.png"/></h1>
							</div>
							<nav id="nav">
								<ul>
									<li><a href="Home.php">Home</a></li>
									<li><a href="Directories.php">Directories</a></li>
									<li><a href="Logout.php">Log out!</a></li>
									<li></li>
								</ul>
							</nav>
						</div>
					</div>
				</header>
			</div>

			<div id="footer-wrapper">
				<footer class="container" id="site-footer">
					<div class="row">
						
						<div class="3u">
							<section>
								<h2>Add friend</h2>
								<ul class="link-list">
								<?php
								session_start();
if(!isset($_SESSION["sess_user"])){
	header("location:Log.php");
} else {
								try{
$handler=new PDO('mysql:host=127.0.0.1;dbname=feitdb','root','');
$handler->setAttribute(PDO::ATTR_ERRMODE,PDO::ERRMODE_EXCEPTION);

}
catch(PDOException $e){
echo $e->getMessage();
die();}
$sql="SELECT * FROM korisnici WHERE kime=?";
$query=$handler->prepare($sql);

$query->bindParam(1,$_SESSION["sess_user"]);
$query->execute();


$n=$query->fetch(PDO::FETCH_ASSOC);

$sql="SELECT ime FROM korisnici WHERE ime NOT IN (SELECT k.ime FROM korisnici as k, prijatelstva as p WHERE (k.ime=p.prijatelstvoOD AND p.prijatelstvoKON=?) OR (k.ime=p.prijatelstvoKON AND p.prijatelstvoOD=?)) AND ime!=? ";
$query=$handler->prepare($sql);

$query->bindParam(1,$n['ime']);
$query->bindParam(2,$n['ime']);
$query->bindParam(3,$n['ime']);
$query->execute();



while($row = $query->fetch(PDO::FETCH_ASSOC)){  
 
echo "<li>" . $row['ime'] . " " . "<a href='add.php?a=$row[ime]&b=$n[ime]'>add</a>" . "</li>"; 
}

}					
								?>
 



	



							</section>
						</div>
											</div>				
                    <a href="Upload.php"> Upload file </a>
					<div class="row">
						<div class="3u">
							<section>
								<h2>List of friends</h2>
								<?php
								
if(!isset($_SESSION["sess_user"])){
	header("location:Log.php");
} else {
								try{
$handler=new PDO('mysql:host=127.0.0.1;dbname=feitdb','root','');
$handler->setAttribute(PDO::ATTR_ERRMODE,PDO::ERRMODE_EXCEPTION);

}
catch(PDOException $e){
echo $e->getMessage();
die();}

$sql="SELECT * FROM korisnici WHERE kime=?";
$query=$handler->prepare($sql);

$query->bindParam(1,$_SESSION["sess_user"]);
$query->execute();


$n=$query->fetch(PDO::FETCH_ASSOC);


$sql = "SELECT * FROM prijatelstva"; 
$query=$handler->prepare($sql);

$query->execute();

while($row = $query->fetch(PDO::FETCH_ASSOC)){  
 if($n['ime']==$row['prijatelstvoOD'])
echo "<li>" . $row['prijatelstvoKON'] . " " . "<a href='delete.php?a=$row[prijatelstvoKON]&b=$n[ime]'>delete</a>" . "</li>"; 
if($n['ime']==$row['prijatelstvoKON'])
	echo "<li>" . $row['prijatelstvoOD'] . " "  . "<a href='delete.php?a=$row[prijatelstvoOD]&b=$n[ime]'>delete</a>" . "</li>"; 
}	

	
}							
								?>
							</section>
						</div>
					</div>
                    <div class="row">
						<div class="12u">
							<div id="copyright"> Blagoja Trajkovski | Design: HTML5 UP |</div>
						</div>
					</div>
				</footer>
			</div>

	</body>
</html>