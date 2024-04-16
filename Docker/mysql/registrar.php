<?php
include_once("config.php");
$nombre= $_GET['nombre'];
$correo= $_GET['correo'];
$password= md5($_GET['password']);
$fechanac= $_GET['fechanac'];
$pais= $_GET['pais'];
$municipio= $_GET['municipio'];
$localidad= $_GET['localidad'];
if ($password== "" || $correo== "" || $nombre== "" || $fechanac== "" || $pais== "" || $municipio== "" || $localidad== "") {
    echo "Asegurese de llenar todos los campos";
} else {

$status= 0;
$activationcode = md5($correo.time());
$sql = "INSERT INTO usuarios (nombre, correo, contrasena, fechanac, pais, municipio, localidad, status, activationcode) VALUES ('$nombre', '$correo', '$password', '$fechanac', '$pais', '$municipio', '$localidad', '$status', '$activationcode')";
$query= mysqli_query($conn, $sql);  
    if($query){
        $to = $correo;
        $subject = "Correo de Verificacion Aplicacion Alzheimer";
        $message = "Gracias por registrarse";
        $headers = "MIME-Version: 1.0" . "\r\n";
        $headers .= "Content-type:text/html; charset=iso-8559-1" . "\r\n";
        $headers .= "From: Alzheimer Application ". "\r\n";
        $ms = "<html></body><div><div>Hola $nombre, </div></br></br>";
        $ms .= "<div style='padding:8px;'>Por favor da click en el siguiente enlace para verificar la cuenta </div><div style='padding:10px;'><a href='http://shucksopage.000webhostapp.com/Alz/email_verification.php?code=$activationcode'>Click Here</a></div><div style='padding:4px;'>Powered by <a href='dcc2.000webhostapp.com'>app tal</a></div></div></body></html>";
        mail($to, $subject, $ms, $headers);
        echo "Registro con exito, verifique su cuenta con su correo";
    }
    else{
            echo mysqli_error($conn);
        }        
    }
?>