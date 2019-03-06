<?php
    $con=mysqli_connect("***","***","***", "***");
     mysql_query("set session character_set_connection=utf8;");
    $location=$_GET["location"];
    $response = array();

    $result = mysqli_query($con, "SELECT mountain, Mount_num FROM MOUNT WHERE location='$location'");
    while($row=mysqli_fetch_array($result)){
        array_push($response, array("mountain"=>$row[0], "Mount_num"=>$row[1]));
        //echo $row[0];
    }

    //$response["success"]=true;
    echo json_encode(array("response"=>$response));
    
    mysqli_close($con);
?>
