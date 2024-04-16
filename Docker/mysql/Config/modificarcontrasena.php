<?php
include_once("config.php");
$id= $_GET['id'];
$contrasena = md5($_GET['contrasena']);
$newcontrasena = md5($_GET['newcontrasena']);
$sql = "Select id from usuarios where id = '$id' and contrasena = '$contrasena'";
$result = mysqli_query($conn,$sql);
if (mysqli_num_rows($result)>0){
    $sql2 = "Update usuarios set contrasena= '$newcontrasena' where id = '$id' and contrasena = '$contrasena'";
    $result2 = mysqli_query($conn,$sql2);
    if ($result2 == TRUE){
        echo "Exito#";
    }
    else{
        echo $result2;
    }
}
else{
    echo "noe#";
}
mysqli_close($conn);
?>