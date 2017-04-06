<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Liste des contrats de l'assuré</title>
    <link rel="stylesheet" href="https://fonts.googleapis.com/icon?family=Material+Icons">
    <link rel="stylesheet" href="https://code.getmdl.io/1.3.0/material.teal-indigo.min.css">
    <script defer src="https://code.getmdl.io/1.3.0/material.min.js"></script>
</head>
<body  style="background-color: #008080">
<div style="background-color: #FFF; width: 50%; margin: auto; margin-top: 100px; border-radius: 5px; padding: 20px; text-align: center;">
    <h4>Liste des contrats de l'assuré</h4>
    <h></h>

            <%= request.getAttribute("insuredUserName")%>

</div>
</body>
</html>