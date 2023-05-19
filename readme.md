# TP Compte Bancaire JPA :
### Objectif : Créer une application Java console utilisant JPA pour gérer une liste de comptes bancaire.
## Description de l'application créée:

En utilisant les éléments JPA, il faudra créer une application en ligne de commande qui vous
permettra de gérer les comptes d’une personne.
Une agence bancaire dispose de plusieurs comptes en banque. Chacun de ces comptes en
banque est rattaché à une seule agence.
Chaque client peut disposer de plusieurs comptes en banque. Malgré la particularité de la
situation, des comptes en banque peuvent appartenir à plusieurs personnes (membre de la
famille par exemple.)

### Un client se compose des éléments suivants :
• Id

• Nom

• Prenom

• Date de naissance

• Liste de compte

### Un compte se compose des éléments suivants :
• Id

• Libelle, jamais nul

• IBAN, longueur max : 27, jamais nul

• Solde : avec une précision de 10 et un nombre après la virgule de 2.

• L’agence

• La liste des clients.

### Une agence, elle se compose des éléments suivants :
• Id

• Une addresse

Une base de données, qu’on appellera "banque_bdd", sera créée via MySQL Workbench.