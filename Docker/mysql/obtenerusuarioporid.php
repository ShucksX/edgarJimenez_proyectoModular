<?php
include_once("config.php");
$search = FALSE;
$id = $_GET["id"];
$sql = "Select * from usuarios where id = '$id'";

$result = mysqli_query($conn,$sql);
if (mysqli_num_rows($result)>0){
    while($row = mysqli_fetch_assoc($result)){
        echo $row["nombre"];
        echo "|";
        echo $row["fechanac"];
        echo "|";
        echo $row["correo"];
        echo "|";
        echo $row["pais"];
        echo "|";
        echo $row["municipio"];
        echo "|";
        echo $row["localidad"];
        echo "%";
        break;
    }
}else{
    echo "noe#";
}
mysqli_close($conn);
?>