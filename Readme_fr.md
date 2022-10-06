# Système de gestion des produits

[![Open in GitPod](https://gitpod.io/button/open-in-gitpod.svg)](https://gitpod.io/from-referrer/)

Il s'agit d'un marché où les clients peuvent passer des commandes et où l'administrateur peut gérer l'inventaire des produits, visualiser les commandes et générer des rapports.

# Technologie utilisée
- Maven pour la gestion des dépendances
- Spring MVC pour le développement d'applications Web
- Spring Data JPA pour la création d'un référentiel personnalisé
- Spring Boot pour l'autoconfiguration
- Spring Security pour l'authentification et l'autorisation
- Hibernate Validator pour la validation des données de formulaire
- H2 In-memory Database pour le stockage des données
- Java Mail API pour l'envoi de courriels HTML via SMTP
- JSTL

##  Exigences
- Java
- Oracle
- Apache Tomcat
- Maven

## Configuration
Modifiez les détails SMTP dans "application.properties".

## Détails d'accès

#### Admin
Nom d'utilisateur : admin@gmail.com

Mot de passe: user

## Schéma de la base de données

!["Database Schema Diagram"](https://github.com/anantjain6/ProductManagementSystem/blob/master/document/schema.png)


## Captures d'écran

### Page d'accueil
!["Page d'accueil"](https://github.com/anantjain6/ProductManagementSystem/blob/master/document/Home.png)

### Page d'identification
!["Page d'identification"](https://github.com/anantjain6/ProductManagementSystem/blob/master/document/Login.png)

#### Afficher les produits disponibles pour passer une commande
!["Afficher les produits disponibles pour passer une commande"](https://github.com/anantjain6/ProductManagementSystem/blob/master/document/Place_Order.png)

#### Commande passée avec succès
!["Commande passée avec succès"](https://github.com/anantjain6/ProductManagementSystem/blob/master/document/Order_Placed.png)

#### E-Mail lors de la commande
!["E-Mail lors de la commande"](https://github.com/anantjain6/ProductManagementSystem/blob/master/document/EMail.png)

#### L'administrateur peut voir quel produit a été vendu ainsi que la quantité
!["L'administrateur peut voir quel produit a été vendu ainsi que la quantité"](https://github.com/anantjain6/ProductManagementSystem/blob/master/document/Admin_Product_Report.png)

## Guildlines de contribution
- Soulever un problème pour une amélioration, une nouvelle fonctionnalité, un rapport de bogue, etc.
- Bifurquer ce dépôt
- Créez une nouvelle branche dans votre dépôt bifurqué
- Effectuer des changements dans cette nouvelle branche
- Faire une demande de transfert vers la branche master. Mentionnez le problème correspondant dans la demande de retrait.

## License
Ce projet est soumis à la licence MIT
