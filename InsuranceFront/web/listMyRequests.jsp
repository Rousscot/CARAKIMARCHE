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
<div style="background-color: #FFF; width: 75%; margin: auto; margin-top: 100px; border-radius: 5px; padding: 20px; text-align: center;">
    <h2>Liste des demandes de <%= request.getAttribute("insuredUserName")%></h2>
    </br>

    <h4>Liste des contrats d'assurance habitation</h4>
    <hr>
    <br>
    <table class="mdl-data-table mdl-js-data-table mdl-shadow--2dp" style="width: 100%;">
        <thead>
        <tr>
            <th class="mdl-data-table__cell--non-numeric">Titre</th>
            <th class="mdl-data-table__cell--non-numeric">Assuré</th>
            <th class="mdl-data-table__cell--non-numeric">Adresse</th>
            <th class="mdl-data-table__cell--non-numeric">Type de contrat</th>
            <th>Montant</th>
            <th>Montant max assuré</th>
            <th class="mdl-data-table__cell--non-numeric">Type de requête</th>
            <th class="mdl-data-table__cell--non-numeric">Statut</th>
        </tr>
        </thead>
        <tbody>
        <%= request.getAttribute("tableHouse")%>
        </tbody>
    </table>
    <br>
    <br>
    <h4>Liste des contrats d'assurance vie</h4>
    <hr>
    <table class="mdl-data-table mdl-js-data-table mdl-shadow--2dp" style="width: 100%;">
        <thead>
        <tr>
            <th class="mdl-data-table__cell--non-numeric">Titre</th>
            <th class="mdl-data-table__cell--non-numeric">Assuré</th>
            <th>Capital</th>
            <th class="mdl-data-table__cell--non-numeric">Type de contrat</th>
            <th>Montant</th>
            <th>Nombre minimum d'années</th>
            <th class="mdl-data-table__cell--non-numeric">Type de requête</th>
            <th class="mdl-data-table__cell--non-numeric">Statut</th>
        </tr>
        </thead>
        <tbody>
        <%= request.getAttribute("tableLife")%>
        </tbody>
    </table>
    <br>
    <br>
    <h4>Liste des contrats auto</h4>
    <hr>
    <table class="mdl-data-table mdl-js-data-table mdl-shadow--2dp" style="width: 100%;">
        <thead>
        <tr>
            <th class="mdl-data-table__cell--non-numeric">Titre</th>
            <th class="mdl-data-table__cell--non-numeric">Assuré</th>
            <th class="mdl-data-table__cell--non-numeric">Modèle</th>
            <th class="mdl-data-table__cell--non-numeric">Type de contrat</th>
            <th>Montant</th>
            <th class="mdl-data-table__cell--non-numeric">Immatriculation</th>
            <th class="mdl-data-table__cell--non-numeric">Type de requête</th>
            <th class="mdl-data-table__cell--non-numeric">Statut</th>        </tr>
        </thead>
        <tbody>
        <%= request.getAttribute("tableCar")%>
        </tbody>
    </table>
    <br>
    <br>
    <a href="index.html" class="mdl-button mdl-js-button mdl-button--accent">Retour</a>
</div>
</body>
</html>