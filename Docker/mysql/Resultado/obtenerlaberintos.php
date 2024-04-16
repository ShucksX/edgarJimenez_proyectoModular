<?php
include_once("config.php");
$search = FALSE;
$sql = "Select * from laberintos";

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

if (isset($_GET['tiempo'])){
    if ($search == FALSE){
        $sql .= " where ";
        $search = TRUE;
    }
    else{
        $sql .= " and ";
    }
    $tiempo = $_GET['tiempo'];
    $sql .= "tiempo = '$tiempo'";
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
        echo $row["tiempo"];
        echo "|";
        echo $row["fecharesultado"];
        echo "%";
    }
}else{
    echo "noe#";
}
mysqli_close($conn);
?>