# CARA 2017 - MASTER 2 MIAGE

**Authors:**
- __Aurelien Rousseau__
- __Cyril Ferlicot__

## Description

Content of the archive:
- A *README* file to explain the project
- An *Archives* folder containing a jar with the back of the application and a war with the back **and** the front of the application. Those two archives were compiled on an OSX El Captain environment and Intellij.
- An *sql* folder containing a script to add some users to the database in order to be able to start the application
- An *Insurance-back* folder with the sources of the back
- An *Insurance-front* folder with the sources of the front of the application

Since this project aim to learn some Java framework we did not lose time implementing checks on the inputs of our forms or other things like checking that we do not add two time the same user... Thus it is possible to break the application if you insert wrong datas intentionnaly.

This application uses EJBs, JPA for the persistance, JAAS for the security and Servelets for the front to create a web application.

## Implemented features

- Login: A user will be ask to log-in in order to access to a screen of the web application. This login is managed with JAAS. There is three roles in the application and each role allow to access some actions. The roles are *admin*, *broker* or *insured*. In order to enable the login you'll need to setup your glassfish serveur to create a realm as explain bellow in the deployement section.
- Add/Delete/List the users: This action is for the *admin* only. They will have the possibility to get a list of users with a button to delete them. They'll also get a button to get to a form in order to add a new user.
- Add/Delete/List the kinds of contracts: This action is for the *admin* only. They will have the possibility to get a list of the kinds of contracts for each categories (life, housing, car) with a button to delete them. They'll also get a button to get to a form in order to add a new kind of contract.
- Add/Delete/List contracts: This action is for the *broker* only. They will have the possibility to get a list of theire contracts for each categories (life, housing, car) with a button to delete one. They'll also get a button to get to a form in order to add a contract for a user.

## Missing features

We did not get the time to implement everything because we got a lot of problem with JAAS. As soon as we added an url pattern matching our application we got 403 errors everywhere even when the user got the right role. Checking the log the user was right, the role was right, the access was granted but we ended up on a 403 error all the time. 

During our check we found that this error might come from the name of our computer because even if we wrote "localhost:8080" in the url, JNDI used the name of our computer in order to do his lookup. 

In the end we could get it work only by changing of computer with a name without secial characters as `-` or `.`. This problem made us lose a lot of time, thus these are the functionality we did not implemented:

- TODO

## How to deploy

In order to deploy the application you can use directly the archives in the folder **Archives**.

### Prerequisites

In order to run our application you need a MySql database. You'll need to create the database and then you can execute the script in the **sql** folder in order to init some users.

### Glassfish configuration

To run the application you'll need a glassfish server with some configurations. We'll detail everything here.

Do not forget to add the MySql drivers to your glassfish.

#### Connection Pool

You first need to create a new connection pool for JDBC.

- Name: InsurancePool
- Type: DataSource
- Datasource Classname: com.mysql.jdbc.jdbc2.optional.MysqlDataSource
- Properties
  - Port: 3306
  - DatabaseName: insurance (change this if yours is not insurance)
  - Password: *****
  - ConnectionAttributes: ;create=true
  - ServerName: localhost
  - User: root

#### Resource 

Now you need a persistance unit for your JDBC. For this create a new resource.

- JNDI Name: jdbc/InsuranceDS
- Pool Name: InsurancePool

#### Security Realm

For JAAS you need a security realm:

- Realm Name: InsuranceRealm
- Class Name: com.sun.enterprise.security.auth.realm.jdbc.JDBCRealm
- JAAS Context: jdbcRealm
- JNDI: jdbc/InsuranceDS
- User Table: user
- User Name Column: userName
- Password Column: pswd
- Group Table: roleUser
- Group Table User Name Column: userName
- Group Name Column: roleName

### Deployement

To deploy the application just deploy the war inside the **Archives** folder.

## How to use the application

Once the application is deployed you can just go to `localhost:8080/InsuranceFront`