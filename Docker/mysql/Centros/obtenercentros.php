<?php
include_once("config.php");
$search = FALSE;
$sql = "Select * from centros";

if (isset($_GET['id'])){
    if ($search == FALSE){
        $sql .= " where ";
        $search = TRUE;
    }
    $id = $_GET['id'];
    $sql .= "id = '$id'";
}

if (isset($_GET['nombre'])){
    if ($search == FALSE){
        $sql .= " where ";
        $search = TRUE;
    }
    else{
        $sql .= " and ";
    }
    $nombre = $_GET['nombre'];
    $sql .= "nombre = '$nombre'";
}

if (isset($_GET['pais'])){
    if ($search == FALSE){
        $sql .= " where ";
        $search = TRUE;
    }
    else{
        $sql .= " and ";
    }
    $pais = $_GET['pais'];
    $sql .= "pais = '$pais'";
}

if (isset($_GET['municipio'])){
    if ($search == FALSE){
        $sql .= " where ";
        $search = TRUE;
    }
    else{
        $sql .= " and ";
    }
    $municipio = $_GET['municipio'];
    $sql .= "municipio = '$municipio'";
}

if (isset($_GET['localidad'])){
    if ($search == FALSE){
        $sql .= " where ";
        $search = TRUE;
    }
    else{
        $sql .= " and ";
    }
    $localidad = $_GET['localidad'];
    $sql .= "localidad = '$localidad'";
}

if (isset($_GET['direccion'])){
    if ($search == FALSE){
        $sql .= " where ";
        $search = TRUE;
    }
    else{
        $sql .= " and ";
    }
    $direccion = $_GET['direccion'];
    $sql .= "direccion = '$direccion'";
}

if (isset($_GET['numerotelefono'])){
    if ($search == FALSE){
        $sql .= " where ";
        $search = TRUE;
    }
    else{
        $sql .= " and ";
    }
    $numerotelefono = $_GET['numerotelefono'];
    $sql .= "numerotelefono = '$numerotelefono'";
}

if (isset($_GET['correo'])){
    if ($search == FALSE){
        $sql .= " where ";
        $search = TRUE;
    }
    else{
        $sql .= " and ";
    }
    $correo = $_GET['correo'];
    $sql .= "correo = '$correo'";
}

$result = mysqli_query($conn,$sql);
if (mysqli_num_rows($result)>0){
    while($row = mysqli_fetch_assoc($result)){
        echo $row["id"];
        echo "|";
        echo $row["nombre"];
        echo "|";
        echo $row["pais"];
        echo "|";
        echo $row["municipio"];
        echo "|";
        echo $row["localidad"];
        echo "|";
        echo $row["direccion"];
        echo "|";
        echo $row["numerotelefono"];
        echo "|";
        echo $row["correo"];
        echo "%";
    }
}else{
    echo "noe#";
}
mysqli_close($conn);
?>