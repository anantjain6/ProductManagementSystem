# Product Management System

[![Open in GitPod](https://gitpod.io/button/open-in-gitpod.svg)](https://gitpod.io/from-referrer/)

It is a marketplace where customer can place order and Admin can manage inventory of products, view order and generate the report.

## Technology Used
-   Maven for Dependency Management
-   Spring MVC for Web application development
-   Spring Data JPA for Creating Custom Repository
-   Spring Boot for Autoconfiguration
-   Spring Security for Authentication & Authorisation
-   Hibernate Validator for form data validation
-   H2 In-memory Database for Storing data
-   Java Mail API to send HTML E-Mail over SMTP
-   JSTL

## Requirements
-   Java
-   Oracle
-   Apache Tomcat
-   Maven

## Configuration
Change the SMTP details in "application.properties"

## Login Details

#### Admin
Username: admin@gmail.com

Password: admin

#### Customer
Username: customer@gmail.com

Password: user

## Database Schema Diagram

!["Database Schema Diagram"](https://github.com/anantjain6/ProductManagementSystem/blob/master/document/schema.png)

## Screenshots

#### Home Page
!["Home Page"](https://github.com/anantjain6/ProductManagementSystem/blob/master/document/Home.png)

#### Login Page
!["Login Page"](https://github.com/anantjain6/ProductManagementSystem/blob/master/document/Login.png)

#### Show available product to place order
!["Show available product to place order"](https://github.com/anantjain6/ProductManagementSystem/blob/master/document/Place_Order.png)

#### Successfully Placed Order
!["Successfully Placed Order"](https://github.com/anantjain6/ProductManagementSystem/blob/master/document/Order_Placed.png)

#### E-Mail on placing order
!["E-Mail on placing order"](https://github.com/anantjain6/ProductManagementSystem/blob/master/document/EMail.png)

#### Admin can see which product sold how much
!["Admin can see which product sold how much"](https://github.com/anantjain6/ProductManagementSystem/blob/master/document/Admin_Product_Report.png)

## Dockerizing

[Reference: Deploy a Spring Boot WAR into a Tomcat Server](https://www.baeldung.com/spring-boot-war-tomcat-deploy)

This application is a Web Application that runs JSP, so the target should be compiled as WAR, instead of JAR.

First, compile and packaging the application as WAR:

```
chmod +x ./mvnw
./mvnw clean package
```

If you want to test the application in the host OS:

```
java -jar target/*.war
```

To build the docker image and docker run, where `myorg/pms` is the tag you can specify:

```
docker build -t myorg/pms .
docker run -p 8083:8083 myorg/pms
```

### Docker compose

If you want to orchestra with more services, you may consider `docker compose`. You could edit `docker-compose.yaml` to extend the services.

To run by docker compose:

```
docker compose up --build
```


## Contribution Guildlines
-    Raise an issue for enhancement, new feature, bug report etc.
-    Folk this repository
-    Create new branch in your folked repository
-    Do changes in that new branch
-    Raise a pull request to master branch. Do mention the respective issue in the pull request.

## License

This project is licensed under the MIT License
