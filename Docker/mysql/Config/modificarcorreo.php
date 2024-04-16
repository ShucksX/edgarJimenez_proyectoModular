<?php
include_once("config.php");
$id= $_GET['id'];
$correo = $_GET['correo'];
$contrasena = md5($_GET['contrasena']);
$sql = "Select nombre from usuarios where id = '$id' and contrasena = '$contrasena'";
$result = mysqli_query($conn,$sql);
if (mysqli_num_rows($result)>0){
    while($row = mysqli_fetch_assoc($result)){
        $nombre = $row["nombre"];
        $to = $correo;
        $subject = "Correo de verificacion para cambio de correo en aplicacion Alzheimer";
        $message = "Verifique el cambio de su cuenta de correo";
        $headers = "From: http://shucksopage.000webhostapp.com". "\r\n";
        $ms = "<html></body><div><div>Hola $nombre, </div></br></br>";
        $ms .= "<div style='padding:8px;'>Por favor da click en el siguiente enlace para verificar el cambio de la cuenta de correo </div><div style='padding:10px;'><a href='http://shucksopage.000webhostapp.com/Alz/Config/cambiarcorreo.php?id=$id&correo=$correo'>Click aqui</a></div><div style='padding:4px;'>Powered by <a href='dcc2.000webhostapp.com'>app tal</a></div></div></body></html>";
        mail($to, $subject, $ms);
        echo "Exito#";
        break;
    }
}
else{
    echo "noe#";
    
}
mysqli_close($conn);
?>