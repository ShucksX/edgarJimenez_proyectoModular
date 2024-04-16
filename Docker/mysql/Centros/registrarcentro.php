<?php
include_once("config.php");
$nombre= $_GET['nombre'];
$pais= $_GET['pais'];
$municipio= $_GET['municipio'];
$localidad= $_GET['localidad'];
$direccion = $_GET['direccion'];
$numerotelefono = $_GET['numtel'];
$correo= $_GET['correo'];
if ($correo== "" || $nombre== ""|| $pais== "" || $municipio== "" || $localidad== "" || $direccion== "" || $numerotelefono== "") {
    echo "Asegurese de llenar todos los campos";
} else {

$sql = "INSERT INTO centros (nombre, pais, municipio, localidad, direccion, numerotelefono, correo) VALUES ('$nombre', '$pais', '$municipio', '$localidad', '$direccion', '$numerotelefono', '$correo')";
$query= mysqli_query($conn, $sql);  
    if($query){
        echo "Registro con exito";
    }
    else{
            echo mysqli_error($conn);
        }        
    }
?>