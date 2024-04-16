<?php
include_once("config.php");
$iduser= $_GET['iduser'];
$datenow= date("Y-m-d");
$sql = "Select palabra1, palabra2, palabra3, fechaaccesible from palabras where idusuario = '$iduser' and status = '0'";
$result = mysqli_query($conn,$sql);
if (mysqli_num_rows($result)>0){
    while($row = mysqli_fetch_assoc($result)){
        $datebd = $row["fechaaccesible"];
        if ($datenow > $datebd){
            echo $row["palabra1"];
            echo "|";
            echo $row["palabra2"];
            echo "|";
            echo $row["palabra3"];
            break;
        }
        else{
            echo "temprano#";
            break;
        }
    }
}
else{
    echo "noe#";
}
mysqli_close($conn);
?>