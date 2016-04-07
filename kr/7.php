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

$sql="INSERT INTO datoteki(ime,korisnik) VALUES(?,?)";
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
<form action="7.php" method="post" enctype="multipart/form-data" >
    <label for="UploadFileField"></label>
    <input type="file" name="UploadFileField" id="UploadFileField" />
    <input type="submit" name="submit" id="UploadButton" value="Upload" />
	<li><a href="Friendship.php">Back</a></li>
  </form>