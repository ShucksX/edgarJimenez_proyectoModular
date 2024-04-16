<?php
include_once("config.php");
$search = FALSE;
$sql = "Select * from palabras";

if (isset($_GET['id'])){
    if ($search == FALSE){
        $sql .= " where ";
        $search = TRUE;
    }
    $id = $_GET['id'];
    $sql .= "id = '$id'";
}

if (isset($_GET['idusuario'])){
    if ($search == FALSE){
        $sql .= " where ";
        $search = TRUE;
    }
    else{
        $sql .= " and ";
    }
    $idusuario = $_GET['idusuario'];
    $sql .= "idusuario = '$idusuario'";
}

if (isset($_GET['palabra1'])){
    if ($search == FALSE){
        $sql .= " where ";
        $search = TRUE;
    }
    else{
        $sql .= " and ";
    }
    $palabra1 = $_GET['palabra1'];
    $sql .= "palabra1 = '$palabra1'";
}

if (isset($_GET['palabra2'])){
    if ($search == FALSE){
        $sql .= " where ";
        $search = TRUE;
    }
    else{
        $sql .= " and ";
    }
    $palabra2 = $_GET['palabra2'];
    $sql .= "palabra2 = '$palabra2'";
}

if (isset($_GET['palabra3'])){
    if ($search == FALSE){
        $sql .= " where ";
        $search = TRUE;
    }
    else{
        $sql .= " and ";
    }
    $palabra3 = $_GET['palabra3'];
    $sql .= "palabra3 = '$palabra3'";
}
if (isset($_GET['status'])){
    if ($search == FALSE){
        $sql .= " where ";
        $search = TRUE;
    }
    else{
        $sql .= " and ";
    }
    $status = $_GET['status'];
    $sql .= "status = '$status'";
}
if (isset($_GET['fechaaccesible'])){
    if ($search == FALSE){
        $sql .= " where ";
        $search = TRUE;
    }
    else{
        $sql .= " and ";
    }
    $fechaaccesible = $_GET['fechaaccesible'];
    $sql .= "fechaaccesible = '$fechaaccesible'";
}
if (isset($_GET['fecharespuesta'])){
    if ($search == FALSE){
        $sql .= " where ";
        $search = TRUE;
    }
    else{
        $sql .= " and ";
    }
    $fecharespuesta = $_GET['fecharespuesta'];
    $sql .= "fecharespuesta = '$fecharespuesta'";
}

if (isset($_GET['puntos'])){
    if ($search == FALSE){
        $sql .= " where ";
        $search = TRUE;
    }
    else{
        $sql .= " and ";
    }
    $puntos = $_GET['puntos'];
    $sql .= "puntos = '$puntos'";
}

$result = mysqli_query($conn,$sql);
if (mysqli_num_rows($result)>0){
    while($row = mysqli_fetch_assoc($result)){
        echo $row["id"];
        echo "|";
        echo $row["idusuario"];
        echo "|";
        echo $row["palabra1"];
        echo "|";
        echo $row["palabra2"];
        echo "|";
        echo $row["palabra3"];
        echo "|";
        echo $row["status"];
        echo "|";
        echo $row["fechaaccesible"];
        echo "|";
        echo $row["fecharespuesta"];
        echo "|";
        echo $row["puntos"];
        echo "%";
    }
}else{
    echo "noe#";
}
mysqli_close($conn);
?>