<?php
include_once("config.php");
$iduser= $_GET['iduser'];
$fecharespuesta = $_GET['fecharespuesta'];
$puntuacion = $_GET['puntuacion'];
$sql2 = "Select id from palabras where idusuario = '$iduser' and status = '0'";
$result2 = mysqli_query($conn,$sql2);
if (mysqli_num_rows($result2)>0){
    while($row = mysqli_fetch_assoc($result2)){
        $sql = "Update palabras set fecharespuesta= '$fecharespuesta', puntos = '$puntuacion', status = '1' where idusuario = '$iduser' and status = '0'";
        $result = mysqli_query($conn,$sql);
        if ($result === TRUE){
            echo "Exito#";
            echo $row["id"];
        }
        else{
            echo $result;
        }
        break;
    }
}
else{
    echo $result;
}
mysqli_close($conn);
?>