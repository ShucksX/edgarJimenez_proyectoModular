<?php
include_once("config.php");
$userid= $_GET['userid'];
$sql = "Select pais,municipio,localidad from usuarios where id = '$userid'";
$result = mysqli_query($conn,$sql);
if (mysqli_num_rows($result)>0){
    while($row = mysqli_fetch_assoc($result)){
        $pais = $row["pais"];
        $municipio = $row["municipio"];
        $localidad = $row["localidad"];
        $sql2 = "Select * from centros where pais = '$pais' and municipio = '$municipio' and localidad = '$localidad'";
        $result2 = mysqli_query($conn,$sql2);
        if (mysqli_num_rows($result2)>0){
            while($row2 = mysqli_fetch_assoc($result2)){
                echo $row2["id"];
                echo "|";
                echo $row2["nombre"];
                echo "|";
                echo $row2["pais"];
                echo "|";
                echo $row2["municipio"];
                echo "|";
                echo $row2["localidad"];
                echo "|";
                echo $row2["direccion"];
                echo "|";
                echo $row2["numerotelefono"];
                echo "|";
                echo $row2["correo"];
                echo "%";
            }
        }else{
            echo "noec#";
        }
    }
}else{
    echo "noe#";
}
mysqli_close($conn);

?>