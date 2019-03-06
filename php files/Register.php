<?php
$con=mysqli_connect("localhost","***","***", "***");

$ID=$_POST["ID"];
$Password=$_POST["Password"];
$Name=$_POST["Name"];

$statement = mysqli_prepare($con,"INSERT INTO USER VALUES(?, ?, ?)");
mysqli_stmt_bind_param($statement, "sss", $ID, $Password, $Name);
mysqli_stmt_execute($statement);

$response = array();
$response["success"]=true;

echo json_encode($response);
?>