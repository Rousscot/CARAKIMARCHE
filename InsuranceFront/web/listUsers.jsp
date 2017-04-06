<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Liste des utilisateurs</title>
    <link rel="stylesheet" href="https://fonts.googleapis.com/icon?family=Material+Icons">
    <link rel="stylesheet" href="https://code.getmdl.io/1.3.0/material.teal-indigo.min.css">
    <script defer src="https://code.getmdl.io/1.3.0/material.min.js"></script>
</head>
<body  style="background-color: #008080">
<div style="background-color: #FFF; width: 50%; margin: auto; margin-top: 100px; border-radius: 5px; padding: 20px; text-align: center;">
    <h4>Liste des utilisateurs</h4>
    <h></h>
    <table class="mdl-data-table mdl-js-data-table mdl-shadow--2dp" style="width: 100%;">
        <thead>
        <tr>
            <th class="mdl-data-table__cell--non-numeric">Pseudo</th>
            <th class="mdl-data-table__cell--non-numeric">Prenom</th>
            <th class="mdl-data-table__cell--non-numeric">Nom</th>
            <th class="mdl-data-table__cell--non-numeric">Action</th>
        </tr>
        </thead>
        <tbody>
            <%= request.getAttribute("usersTable")%>
        </tbody>
    </table>
    <br>
    <a href="ajouterUtilisateur" class="mdl-button mdl-js-button mdl-button--raised mdl-button--accent" style="text-align: left" >Ajouter un utilisateur (admin)</a>
    <a href="types_de_contrat" class="mdl-button mdl-js-button mdl-button--raised mdl-button--accent" style="text-align: left" >Liste des types de contrat (admin)</a>
</div>
</body>
</html>