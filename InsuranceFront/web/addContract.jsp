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
    <div style=" display: flex; justify-content: space-between;">
        <div style="width: 30%; border-right: solid 1px grey;">
            <h4>Ajouter un contrat d'habitation</h4>
            <h></h>
            <form Method="POST" Action="ajouterContrat">
                <div class="mdl-textfield mdl-js-textfield">
                    <input class="mdl-textfield__input" type="text" id="htitle" size="40" name="htitle">
                    <label class="mdl-textfield__label" for="htitle">Titre</label>
                </div>
                <br>
                Utilisateur:
                <select name="husername">
                    <%= request.getAttribute("optionsUser") %>
                </select>
                <br>
                <div class="mdl-textfield mdl-js-textfield">
                    <input class="mdl-textfield__input" type="number" id="hamount" name="hamount">
                    <label class="mdl-textfield__label" for="hamount">Montant des créances</label>
                </div>
                <br>
                Type de contrat:
                <select name="hkindid">
                    <%= request.getAttribute("optionsHouseKinds") %>
                </select>
                <br>
                <div class="mdl-textfield mdl-js-textfield">
                    <input class="mdl-textfield__input" type="number" id="hmaxAmount" name="hmaxAmount">
                    <label class="mdl-textfield__label" for="hmaxAmount">Montant maximum à assurer</label>
                </div>
                <br>
                <div class="mdl-textfield mdl-js-textfield">
                    <input class="mdl-textfield__input" type="text" id="address" size="100" name="address">
                    <label class="mdl-textfield__label" for="address">Adresse</label>
                </div>
                <br>
                <br>
                <button class="mdl-button mdl-js-button mdl-button--raised mdl-button--primary" type="submit" value="Créer" name="action:AddHouseContract">
                    Ajouter
                </button>
            </form>
        </div>
        <div style="width: 30%; border-right: solid 1px grey;">
            <h4>Ajouter un contrat d'assurance vie</h4>
            <h></h>
            <form Method="POST" Action="ajouterContrat">
                <div class="mdl-textfield mdl-js-textfield">
                    <input class="mdl-textfield__input" type="text" id="ltitle" size="40" name="ltitle">
                    <label class="mdl-textfield__label" for="ltitle">Titre</label>
                </div>
                <br>
                Utilisateur:
                <select name="lusername">
                    <%= request.getAttribute("optionsUser") %>
                </select>
                <br>
                <div class="mdl-textfield mdl-js-textfield">
                    <input class="mdl-textfield__input" type="number" id="lamount" name="lamount">
                    <label class="mdl-textfield__label" for="lamount">Montant des créances</label>
                </div>
                <br>
                Type de contrat:
                <select name="lkindid">
                    <%= request.getAttribute("optionsLifeKinds") %>
                </select>
                <br>
                <div class="mdl-textfield mdl-js-textfield">
                    <input class="mdl-textfield__input" type="number" id="capital" name="capital">
                    <label class="mdl-textfield__label" for="capital">Capital</label>
                </div>
                <br>
                <div class="mdl-textfield mdl-js-textfield">
                    <input class="mdl-textfield__input" type="number" id="minYears" name="minYears">
                    <label class="mdl-textfield__label" for="minYears">Nombre minimum d'années</label>
                </div>
                <br>
                <br>
                <button class="mdl-button mdl-js-button mdl-button--raised mdl-button--primary" type="submit" value="Créer" name="action:AddLifeContract">
                    Ajouter
                </button>
            </form>
        </div>
        <div style="width: 30%;">
            <h4>Ajouter un contrat voiture</h4>
            <h></h>
            <form Method="POST" Action="ajouterContrat">
                <div class="mdl-textfield mdl-js-textfield">
                    <input class="mdl-textfield__input" type="text" id="ctitle" size="40" name="ctitle">
                    <label class="mdl-textfield__label" for="ctitle">Titre</label>
                </div>
                <br>
                Utilisateur:
                <select name="cusername">
                    <%= request.getAttribute("optionsUser") %>
                </select>
                <br>
                <div class="mdl-textfield mdl-js-textfield">
                    <input class="mdl-textfield__input" type="number" id="camount" name="camount">
                    <label class="mdl-textfield__label" for="camount">Montant des créances</label>
                </div>
                <br>
                Type de contrat:
                <select name="ckindid">
                    <%= request.getAttribute("optionsCarKinds") %>
                </select>
                <br>
                <div class="mdl-textfield mdl-js-textfield">
                    <input class="mdl-textfield__input" type="text" size="40" id="model" name="model">
                    <label class="mdl-textfield__label" for="model">Modèle</label>
                </div>
                <br>
                <div class="mdl-textfield mdl-js-textfield">
                    <input class="mdl-textfield__input" type="text" id="plate" size="40" name="plate">
                    <label class="mdl-textfield__label" for="plate">Immatriculation</label>
                </div>
                <br>
                <br>
                <button class="mdl-button mdl-js-button mdl-button--raised mdl-button--primary" type="submit" value="Créer" name="action:AddCarContract">
                    Ajouter
                </button>
            </form>
        </div>
    </div>
    <br>
    <a href="types_de_contrat" style="text-align: left;" class="mdl-button mdl-js-button mdl-button--primary" >Retour</a>
</div>
</body>
</html>