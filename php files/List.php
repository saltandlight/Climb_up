<?php
    $con=mysqli_connect("localhost","***","***", "***");
    $result = mysqli_query($con, "SELECT * FROM USER");
    $response = array();

    while($row=mysqli_fetch_array($result)){
        array_push($response, array("ID"=>$row[0], "Password"=>$row[1], "Name"=>$row[2]));
    }

    echo json_encode(array("response"=>$response));
    mysqli_close($con);
?>