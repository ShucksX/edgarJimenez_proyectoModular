<?php
include_once("config.php");
$id= $_GET['id'];
$correo = $_GET['correo'];
$sql = "Select id from usuarios where id = '$id'";
$result = mysqli_query($conn,$sql);
if (mysqli_num_rows($result)>0){
    $sql2 = "Update usuarios set correo= '$correo' where id = '$id'";
    $result2 = mysqli_query($conn,$sql2);
    if ($result2 == TRUE){
        echo "Su cambio de correo se verifico con exito";
    }
    else{
        echo "Hubo un error al verificar su cambio de correo";
    }
}
else{
    echo "Se genero un error al verificar su cambio de correo";
}
mysqli_close($conn);
?>