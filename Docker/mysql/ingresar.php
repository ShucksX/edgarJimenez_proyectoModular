<?php
include_once("config.php");
$correo= $_GET['correo'];
$password= $_GET['password'];
if ($correo == "" || $password == ""){
    echo "vac";
}
else{
    $password = md5($password);
    $sql = "Select status, id from usuarios where correo = '$correo' and contrasena = '$password'";
    $result = mysqli_query($conn,$sql);
    if (mysqli_num_rows($result)>0){
        while($row = mysqli_fetch_assoc($result)){
            if ($row["status"] == 1){
                echo "cli";
                echo $row["id"];
                break;
            }
            else if($row["status"] == 2){
                echo "adm";
                echo $row["id"];
                break;
            }
            else{
                echo "unv";
                break;
            }
        }
    }else{
        echo "noe";
    }
    mysqli_close($conn);
}
?>