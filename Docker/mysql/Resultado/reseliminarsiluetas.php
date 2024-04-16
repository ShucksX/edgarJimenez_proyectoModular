<?php
include_once("config.php");
$id= $_GET['id'];

$sql = "DELETE FROM siluetas WHERE id = '$id'";
$query= mysqli_query($conn, $sql);  
    if($query){
        echo "Exito#";
    }
    else{
            echo mysqli_error($conn);
        }        
    
?>