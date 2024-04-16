<?php
include_once("config.php");
$search = FALSE;
$sql = "Select * from colores";

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

if (isset($_GET['fecharesultado'])){
    if ($search == FALSE){
        $sql .= " where ";
        $search = TRUE;
    }
    else{
        $sql .= " and ";
    }
    $fecharesultado = $_GET['fecharesultado'];
    $sql .= "fecharesultado = '$fecharesultado'";
}

$result = mysqli_query($conn,$sql);
if (mysqli_num_rows($result)>0){
    while($row = mysqli_fetch_assoc($result)){
        echo $row["id"];
        echo "|";
        echo $row["idusuario"];
        echo "|";
        echo $row["puntos"];
        echo "|";
        echo $row["fecharesultado"];
        echo "%";
    }
}else{
    echo "noe#";
}
mysqli_close($conn);
?>