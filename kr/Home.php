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
									<li><a href="Log.php">Log in!</a></li>
									<li><a href="Registration.php">Sign up!</a></li>
									
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
								<h2>Public directories</h2>
								<ul class="link-list">
								

								<?php
								
								try{
$handler=new PDO('mysql:host=127.0.0.1;dbname=feitdb','root','');
$handler->setAttribute(PDO::ATTR_ERRMODE,PDO::ERRMODE_EXCEPTION);

}
catch(PDOException $e){
echo $e->getMessage();
die();}

$sql="SELECT * FROM datoteki";
$query=$handler->prepare($sql);


$query->execute();
					
echo "<table>";
					
			while($row = $query->fetch(PDO::FETCH_ASSOC)){  
 echo "<tr>";
 echo "<td>";echo $row['ime'] . " " .$row['kreiranje'] . " " . $row['korisnik'] . " ";?><a href="<?php echo $row['korisnik'];?>">Download</a><?php echo "</td>";
 echo "</tr>";

 
}					
				echo "</table>";				
								?>
	



							</section>
						</div>
											</div>				
                    
                    <div class="row">
						<div class="12u">
							<div id="copyright"> Blagoja Trajkovski | Design:HTML5 UP |</div>
						</div>
					</div>
				</footer>
			</div>

	</body>
</html>