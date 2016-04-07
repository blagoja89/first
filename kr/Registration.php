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
									<li><a href="Log.php">Log in!</a></li>
									
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
<input type="text" placeholder="Name" name="ime" class="text"/>
   <input type="text" placeholder="Last name" name="prezime" class="text"/>
    <input type="text" placeholder="Username" name="kime" class="text"/>
     <input type="text" placeholder="Password" name="lozinka" class="text"/>
      <input type="text" placeholder="Confirm password" name="lozinkapak" class="text"/>
       <input type="text" placeholder="Date of birth" name="datumR" class="text"/>
        <input type="text" placeholder="Adress" name="adresa" class="text"/>
         <input type="text" placeholder="Profession" name="profesija" class="text"/>
          <input type="text" placeholder="e-mail" name="email" class="text"/>
<input type="submit" value="Registration" name="submit" />
</form>
			
			<?php
if(isset($_POST["submit"])){
	
	if(!empty($_POST['ime'])&&!empty($_POST['prezime'])&&!empty($_POST['kime'])&&!empty($_POST['lozinka'])&&!empty($_POST['lozinkapak'])&&!empty($_POST['datumR'])&&!empty($_POST['adresa'])&&!empty($_POST['profesija'])&&!empty($_POST['email'])){
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
	echo "That username already exists! Please try again with another.";
}else{
$sql="INSERT INTO korisnici(ime,prezime,kime,lozinka,datumR,adresa,profesija,email) VALUES(?,?,?,?,?,?,?,?)";
$query=$handler->prepare($sql);

$query->bindParam(1,$_POST['ime']);
$query->bindParam(2,$_POST['prezime']);
$query->bindParam(3,$_POST['kime']);
$query->bindParam(4,$_POST['lozinka']);
$query->bindParam(5,$_POST['datumR']);
$query->bindParam(6,$_POST['adresa']);
$query->bindParam(7,$_POST['profesija']);
$query->bindParam(8,$_POST['email']);

$query->execute();

if($query){
	echo "Account Successfully Created! Please log in.";
	} else {
	echo "Failure!";
	}

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