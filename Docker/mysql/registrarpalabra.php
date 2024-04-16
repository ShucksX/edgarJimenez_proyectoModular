<?php
include_once("config.php");
$iduser= $_GET['iduser'];
$palabra1= $_GET['palabra1'];
$palabra2= $_GET['palabra2'];
$palabra3= $_GET['palabra3'];
$fechaacc= $_GET['fechaacc'];
$status= 0;
$sql = "INSERT INTO palabras (idusuario, palabra1, palabra2, palabra3, fechaaccesible, status) VALUES ('$iduser', '$palabra1', '$palabra2', '$palabra3', '$fechaacc', '$status')";
$query= mysqli_query($conn, $sql);  
    if($query){
        echo "Exito";
    }
    else{
            echo mysqli_error($conn);
        }        
?>