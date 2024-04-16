<?php
include_once("config.php");
$iduser = $_GET['iduser'];
$tiempo = $_GET['tiempo'];
$fecharesultado = $_GET['fecharesultado'];
$sql = "INSERT INTO laberintos (idusuario,tiempo,fecharesultado) VALUES ('$iduser', '$tiempo', '$fecharesultado')";
$result = mysqli_query($conn,$sql);
if ($result){
    echo "Exito#";
}
else{
    echo "Error";
}
mysqli_close($conn);
?>