<?php
$con=mysqli_connect("localhost","hyebin95","nolimits!!", "hyebin95");

$Post_title=$_POST["Post_title"];
$Post_content=$_POST["Post_content"];
$ID=$_POST["ID"];
$Post_regtime=date("Y-m-d H:i:s");
$Post_readcount=0;


//$statement = mysqli_prepare($con,"INSERT INTO COMMENT VALUES(?, ?, ?, ?, ?)");
//mysqli_stmt_bind_param($statement, "ssiss", $Post_title, $Post_content, $Post_readcount, $Post_regtime, $ID);


$statement = mysqli_prepare($con,"INSERT INTO COMMENT(Post_title, Post_content, Post_regtime, ID) VALUES('$Post_title', '$Post_content', '$Post_regtime','$ID')");
mysqli_stmt_execute($statement);

mysqli_stmt_store_result($statement);
mysqli_stmt_bind_result($statement, $ID);

$response = array();
$response["success"]=true;
$response["ID"]=$ID;

echo json_encode($response);
?>
