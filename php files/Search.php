<?php
    $con=mysqli_connect("localhost","***","***", "***");
    mysql_query("set session character_set_connection=utf8;");
  
    $Post_content="%".$_GET["Post_content"];
    $Post_content.="%";
    $response = array();
    
    $result = mysqli_query($con, "SELECT ID, Post_title, Post_content, Post_regtime FROM COMMENT WHERE Post_content LIKE '$Post_content'");
    while($row=mysqli_fetch_array($result)){
        array_push($response, array("ID"=>$row[0],"Post_title"=>$row[1], "Post_content"=>$row[2], "Post_regtime"=>$row[3]));
    }

    echo json_encode(array("response"=>$response));
    mysqli_close($con);
?>

 