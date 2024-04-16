<?php
include_once("config.php");
$iduser= $_GET['iduser'];
$sql = "Select fechanac from usuarios where id = '$iduser'";
$result = mysqli_query($conn,$sql);
if (mysqli_num_rows($result)>0){
    while($row = mysqli_fetch_assoc($result)){
        echo $row["fechanac"];
    }
}
else{
    echo "noe#";
}
mysqli_close($conn);
?>