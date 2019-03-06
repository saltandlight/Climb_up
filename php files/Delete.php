<?php
    $con=mysqli_connect("localhost","***","***", "***");
    
    $ID = $_POST["ID"];
    $statement = mysqli_prepare($con, "DELETE FROM USER WHERE ID = ?");
    mysqli_stmt_bind_param($statement, "s", $ID);
    mysqli_stmt_execute($statement);

    $response = array();
    $response["success"] = TRUE;

    echo json_encode($response);
?>