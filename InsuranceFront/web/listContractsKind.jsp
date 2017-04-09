<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Liste des types de contrat</title>
    <link rel="stylesheet" href="https://fonts.googleapis.com/icon?family=Material+Icons">
    <link rel="stylesheet" href="https://code.getmdl.io/1.3.0/material.teal-indigo.min.css">
    <script defer src="https://code.getmdl.io/1.3.0/material.min.js"></script>
</head>
<body  style="background-color: #008080">
<div style="background-color: #FFF; width: 75%; margin: auto; margin-top: 100px; border-radius: 5px; padding: 20px; text-align: center;">
    <h4>Liste des types de contrats</h4>
    <hr>
    <table class="mdl-data-table mdl-js-data-table mdl-shadow--2dp" style="width: 100%;">
        <thead>
        <tr>
            <th class="mdl-data-table__cell--non-numeric">Titre</th>
            <th class="mdl-data-table__cell--non-numeric">Description</th>
            <th class="mdl-data-table__cell--non-numeric">Catégorie</th>
            <th>Montant mini</th>
            <th class="mdl-data-table__cell--non-numeric">Action</th>
        </tr>
        </thead>
        <tbody>
            <%= request.getAttribute("table")%>
        </tbody>
    </table>
    <br>
    <a href="ajouterTypeDeContrat" class="mdl-button mdl-js-button mdl-button--raised mdl-button--accent">Ajouter un type de contrat</a>
    <a href="index.html" class="mdl-button mdl-js-button mdl-button--accent">Retour</a>
</div>
</body>
</html>