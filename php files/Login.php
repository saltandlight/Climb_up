<?php
$con=mysqli_connect("localhost","***","***", "***");

$ID=$_POST["ID"];
$Password= $_POST["Password"];

$statement=mysqli_prepare($con, "SELECT * FROM USER WHERE ID = ? AND Password = ?");
mysqli_stmt_bind_param($statement, "ss", $ID, $Password);
mysqli_stmt_execute($statement);

mysqli_stmt_store_result($statement);
mysqli_stmt_bind_result($statement, $ID, $Password, $Name );

$response=array();
$response["success"]=false;

while(mysqli_stmt_fetch($statement)){
	$response["success"]=true;
	$response["ID"]=$ID;
	$response["Password"]=$Password;
	$response["Name"]=$Name;
}

echo json_encode($response);

?>

