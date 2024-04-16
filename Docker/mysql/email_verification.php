<?php
include_once("config.php");

$code = $_GET['code'];
$sql = "Update usuarios set status = 1 where activationcode = '$code'";
if (mysqli_query($conn,$sql)){
    echo "Cuenta activada correctamente";
}else{
    echo mysqli_error($conn);
}
mysqli_close($conn);
?>