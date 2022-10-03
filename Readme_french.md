# Système de Gestion de Produit

[![Open in GitPod](https://gitpod.io/button/open-in-gitpod.svg)](https://gitpod.io/from-referrer/)

It is a marketplace where customer can place order and Admin can manage inventory of products, view order and generate the report.

Le Système de Gestion de Produit est une marketplace sur laquelle les clients peuvent passer commande. Un administrateur peut gérer l'inventaire des produits, consulter les commandes et générer des rapports.

## Technologies utilisées
-   Maven pour la gestion de dépendances
-   Spring MVC pour le développement de l'application web
-   Spring Data JPA pour la création de dépots customisés
-   Spring Boot pour la configuration automatique
-   Spring Security pour l'Authentication et l'Authorisation
-   Hibernate Validator pour la validation des données formulaire
-   Base de donnée interne H2 pour le stockage de données
-   L'API Java Mail pour envoyer des e-mail HTML en utilisant le protocole SMTP
-   JSTL

## Prérequis
-   Java
-   Oracle
-   Apache Tomcat
-   Maven

## Configuration
Changez les détails SMTP dans "application.properties"

## Informations sur la connexion

#### Admin
Nom d'utilisateur: admin@gmail.com

Mot de passe: admin

#### Client
Nom d'utilisateur: customer@gmail.com

Mot de passe: user

## Schéma de la base de données

!["Diagramme de la base de données"](https://github.com/anantjain6/ProductManagementSystem/blob/master/document/schema.png)

## Captures d'écran

#### Page d'accueil
!["Page d'accueil"](https://github.com/anantjain6/ProductManagementSystem/blob/master/document/Home.png)

#### Page de connexion
!["Page de connexion"](https://github.com/anantjain6/ProductManagementSystem/blob/master/document/Login.png)

#### Faire apparaitre les produits disponible pour passer commande
!["Faire apparaitre les produits disponible pour passer commande"](https://github.com/anantjain6/ProductManagementSystem/blob/master/document/Place_Order.png)

#### Commande effectuée avec succès
!["Commande effectuée avec succès"](https://github.com/anantjain6/ProductManagementSystem/blob/master/document/Order_Placed.png)

#### E-Mail lors de la commande
!["E-Mail lors de la commande"](https://github.com/anantjain6/ProductManagementSystem/blob/master/document/EMail.png)

#### L'administrateur peut voir pour combien un produit s'est vendu
!["L'administrateur peut voir pour combien un produit s'est vendu"](https://github.com/anantjain6/ProductManagementSystem/blob/master/document/Admin_Product_Report.png)

## Guide pour la contribution
-    Créer une issue pour une amélioration, une nouvelle fonctionnalité, un rapport de bug, etc.
-    Fork le dépôt
-    Créer une nouvelle branche dans votre dépot forké
-    Réalisez vos modifications sur une nouvelle branche
-    Créer une pull request sur la branche master. Mentionnez l'issue respective dans celle-ci.

## License

Ce projet est sous la licence MIT
