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

	

			
			<?php
session_start();
	if(!isset($_SESSION["sess_user"])){
	header("location:Log.php");
} else {
if(isset($_POST['submit'])){
	
		$name='uploads';
		
		if(isset($_FILES['UploadFileField'])){
		
		
		$UploadName = $_FILES['UploadFileField']['name'];
		
		$UploadTmp = $_FILES['UploadFileField']['tmp_name'];
		$UploadType = $_FILES['UploadFileField']['type'];
		
		
	if ($_FILES["UploadFileField"]["error"] > 0)
  {
  echo "ERROR: " . $_FILES["UploadFileField"]["error"] . "<br>";
  }
  if (file_exists("$name/" .$_FILES["UploadFileField"]["name"]))
      {
      echo $_FILES["UploadFileField"]["name"] . " already exists. ";
      }
    else
      {
      move_uploaded_file($_FILES["UploadFileField"]["tmp_name"],
       "$name/" .$_FILES["UploadFileField"]["name"]);
      echo "Saved: " . "$name/" . $_FILES["UploadFileField"]["name"];

	  try{
$handler=new PDO('mysql:host=127.0.0.1;dbname=feitdb','root','');
$handler->setAttribute(PDO::ATTR_ERRMODE,PDO::ERRMODE_EXCEPTION);

}
catch(PDOException $e){
echo $e->getMessage();
die();}

$sql="INSERT INTO datoteki(ime,kreiranje,korisnik) VALUES(?,NOW(),?)";
$query=$handler->prepare($sql);


	  
$query->bindParam(1,$_SESSION["sess_user"]);
$query->bindParam(2,$n="$name/" . $_FILES["UploadFileField"]["name"]);
$query->execute();

header("location:Friendship.php");
		 
		 
}

}
}
}

?>
<form action="Upload.php" method="post" enctype="multipart/form-data" >
    <label for="UploadFileField"></label>
<input type="file" name="UploadFileField" id="UploadFileField" />
    <input type="submit" name="submit" id="UploadButton" value="Upload" />
	<li><a href="Friendship.php">Back</a></li>
      

</form>
			

</html>			

			