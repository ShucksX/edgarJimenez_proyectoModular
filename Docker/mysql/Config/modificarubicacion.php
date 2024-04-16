<?php
include_once("config.php");
$id= $_GET['id'];
$contrasena = md5($_GET['contrasena']);
$sql = "Select id from usuarios where id = '$id' and contrasena = '$contrasena'";
$result = mysqli_query($conn,$sql);
if (mysqli_num_rows($result)>0){
    $sql2 = "Update usuarios set ";
    $update = FALSE;
    if (isset($_GET['pais'])){
        $pais = $_GET['pais'];
        $sql2 .= "pais = '$pais' ";
        $update = TRUE;
    }
    
    if (isset($_GET['municipio'])){
        $municipio = $_GET['municipio'];
        if ($update == TRUE){
            $sql2 .= ", ";
        }
        $sql2 .= "municipio = '$municipio' ";
        $update = TRUE;
    }
    if (isset($_GET['localidad'])){
        $localidad = $_GET['localidad'];
        if ($update == TRUE){
            $sql2 .= ", ";
        }
        $sql2 .= "localidad = '$localidad' ";
        $update = TRUE;
    }
    $sql2 .= "where id = '$id' and contrasena = '$contrasena'";
    $result2 = mysqli_query($conn,$sql2);
    if ($result2 == TRUE){
        echo "Exito#";
    }
    else{
        echo mysqli_error($conn);
        echo $result2;
    }
}
else{
    echo "noe#";
}
mysqli_close($conn);
?>