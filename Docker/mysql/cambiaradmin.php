<?php
include_once("config.php");
$iduser= $_GET['iduser'];
$status = $_GET['status'];
$sql = "Select id from usuarios where status = '2'";
$result = mysqli_query($conn,$sql);
if (mysqli_num_rows($result)>1 || $status == '2'){
    $sql2 = "Update usuarios set status= '$status' where id = '$iduser'";
    $result2 = mysqli_query($conn,$sql2);
    if ($result2 === TRUE){
        echo "Exito#";
    }
    else{
        echo mysqli_error($conn);
    }
}
else{
    echo "Nop#";
}
mysqli_close($conn);
?>