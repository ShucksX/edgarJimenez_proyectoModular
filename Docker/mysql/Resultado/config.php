<?php
$server= "mysql";
$user= "root";
$password = "Passwert";
$bd = "id20342822_bd22313_alzh_edg";
$conn = mysqli_connect($server,$user,$password,$bd);
if(!$conn){
    die ("Conexion fallo");
}
?>