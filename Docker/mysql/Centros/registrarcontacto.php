<?php
include_once("config.php");
$idusuario= $_GET['idusuario'];
$nombre= $_GET['nombre'];
$parentesco= $_GET['parentesco'];
$pais= $_GET['pais'];
$municipio= $_GET['municipio'];
$localidad= $_GET['localidad'];
$direccion = $_GET['direccion'];
$telefono = $_GET['telefono'];
$correo= $_GET['correo'];
if ($idusuario== "" ||$parentesco== "" || $correo== "" || $nombre== ""|| $pais== "" || $municipio== "" || $localidad== "" || $direccion== "" || $telefono== "") {
    echo "Asegurese de llenar todos los campos";
} else {

$sql = "INSERT INTO contactos (idusuario, nombre, parentesco, pais, municipio, localidad, direccion, telefono, correo) VALUES ('$idusuario','$nombre','$parentesco', '$pais', '$municipio', '$localidad', '$direccion', '$telefono', '$correo')";
$query= mysqli_query($conn, $sql);  
    if($query){
        echo "Registro con exito";
    }
    else{
            echo mysqli_error($conn);
        }        
    }
?>