<?php
include_once("config.php");
$id= $_GET['id'];
$nombre = $_GET['nombre'];
$contrasena = md5($_GET['contrasena']);
$sql = "Select id from usuarios where id = '$id' and contrasena = '$contrasena'";
$result = mysqli_query($conn,$sql);
if (mysqli_num_rows($result)>0){
    $sql2 = "Update usuarios set nombre= '$nombre' where id = '$id' and contrasena = '$contrasena'";
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