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
    <h4>Ajouter un utilisateur</h4>
    <hr>
    <form Method="POST" Action="ajouterUtilisateur">
        <div class="mdl-textfield mdl-js-textfield">
            <input class="mdl-textfield__input" type="text" id="nom" size="40" name="lastName">
            <label class="mdl-textfield__label" for="nom">Nom</label>
        </div>
        <br>
        <div class="mdl-textfield mdl-js-textfield">
            <input class="mdl-textfield__input" type="text" id="prenom" size="40" name="firstName">
            <label class="mdl-textfield__label" for="prenom">Prenom</label>
        </div>
        <br>
        <div class="mdl-textfield mdl-js-textfield">
            <input class="mdl-textfield__input" type="text" id="pseudo" size="40" name="userName">
            <label class="mdl-textfield__label" for="pseudo">Pseudo</label>
        </div>
        <br>
        <div class="mdl-textfield mdl-js-textfield">
            <input class="mdl-textfield__input" type="password" id="psw" size="80" name="pswd">
            <label class="mdl-textfield__label" for="psw">Mot de passe</label>
        </div>
        <br>
        Role:
        <select name="role">
            <%= request.getAttribute("options") %>
        </select>
        <br>
        <button class="mdl-button mdl-js-button mdl-button--raised mdl-button--primary" type="submit" value="Créer" name="action:Add">
            Ajouter
        </button>
    </form>
    <br>
    <a href="utilisateurs" style="text-align: left;" class="mdl-button mdl-js-button mdl-button--primary" >Retour</a>
</div>
</body>
</html>