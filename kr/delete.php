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

$sql="DELETE FROM prijatelstva WHERE (prijatelstvoOD=? AND prijatelstvoKON=?) OR (prijatelstvoOD=? AND prijatelstvoKON=?)";
$query=$handler->prepare($sql);

$query->bindParam(1,$_GET['b']);
$query->bindParam(2,$_GET['a']);
$query->bindParam(3,$_GET['a']);
$query->bindParam(4,$_GET['b']);
$query->execute();


}
header("location:Friendship.php");
?>