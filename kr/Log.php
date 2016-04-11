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
								<h1>Share</h1>
							</div>
							<nav id="nav">
								<ul>
									<li><a href="Home.php">Home</a></li>
									<li><a href="Registration.php">Sign up!</a></li>
									
									<li></li>
								</ul>
							</nav>
						</div>
					</div>
				</header>
			</div>
<form action="" name="forma" id="forma1" method="POST">
<div id="footer-wrapper">
				<footer class="container" id="site-footer">
					<div class="row">
						
						<div class="3u">
		<h2>Log in</h2>
    <input type="text" placeholder="Username" name="kime" class="text"/>
     <input type="password" placeholder="Password" name="lozinka" class="text"/>
      
<input type="submit" value="Log in" name="submit" />
</form>
			
			<?php
if(isset($_POST["submit"])){
	
	if(!empty($_POST['kime'])&&!empty($_POST['lozinka'])){
		try{
$handler=new PDO('mysql:host=127.0.0.1;dbname=feitdb','root','');
$handler->setAttribute(PDO::ATTR_ERRMODE,PDO::ERRMODE_EXCEPTION);

}
catch(PDOException $e){
echo $e->getMessage();
die();}

$sql="SELECT * FROM korisnici WHERE kime=? AND lozinka=?";
$query=$handler->prepare($sql);


$query->bindParam(1,$_POST['kime']);
$query->bindParam(2,$_POST['lozinka']);
$query->execute();


if($query->rowCount()==1){
	
	session_start();
	$_SESSION['sess_user']=$_POST['kime'];

	/* Redirect browser */
	header("Location: Friendship.php");
}else{

echo "Invalid username or password!";


}
}
		
	else
		echo "All fields are required!";

}


?>

							<div id="copyright"> Blagoja Trajkovski | Design: HTML5 UP |</div>
						</div>
					</div>
				</footer>
			</div>

	</body>
</html>