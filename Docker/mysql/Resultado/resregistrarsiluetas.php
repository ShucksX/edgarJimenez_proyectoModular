<?php
include_once("config.php");
$iduser = $_GET['iduser'];
$puntos = $_GET['puntos'];
$fecharesultado = $_GET['fecharesultado'];
$sql = "INSERT INTO siluetas (idusuario,puntaje,fecharesultado) VALUES ('$iduser', '$puntos', '$fecharesultado')";
$result = mysqli_query($conn,$sql);
if ($result){
    echo "Exito#";
}
else{
    echo "Error";
}
mysqli_close($conn);
?>