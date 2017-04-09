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
<div style="background-color: #FFF; width: 75%; margin: auto; margin-top: 100px; border-radius: 5px; padding: 20px; text-align: center;">
    <h4>Ajouter un type de contrat</h4>
    <hr>
    <form Method="POST" Action="ajouterTypeDeContrat">
        <div class="mdl-textfield mdl-js-textfield">
            <input class="mdl-textfield__input" type="text" id="title" size="40" name="title">
            <label class="mdl-textfield__label" for="title">Titre</label>
        </div>
        <br>
        <div class="mdl-textfield mdl-js-textfield">
            <input class="mdl-textfield__input" type="text" id="description" size="200" name="description">
            <label class="mdl-textfield__label" for="description">Description</label>
        </div>
        <br>
        <div class="mdl-textfield mdl-js-textfield">
            <input class="mdl-textfield__input" type="number" id="minAmount" name="minAmount">
            <label class="mdl-textfield__label" for="minAmount">Montant minimum</label>
        </div>
        <br>
        Catégorie:
        <select name="category">
            <%= request.getAttribute("options") %>
        </select>
        <br>
        <button class="mdl-button mdl-js-button mdl-button--raised mdl-button--primary" type="submit" value="Créer" name="action:Add">
            Ajouter
        </button>
    </form>
    <br>
    <a href="types_de_contrat" class="mdl-button mdl-js-button mdl-button--primary" >Retour</a>
</div>
</body>
</html>