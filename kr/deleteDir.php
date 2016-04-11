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

$sql="DELETE FROM datoteki WHERE korisnik=?";
$query=$handler->prepare($sql);

$query->bindParam(1,$_GET['a']);
$query->execute();

if (file_exists($_GET['a'])){
	unlink($_GET['a']);
}

}
header("location:Directories.php");
?>