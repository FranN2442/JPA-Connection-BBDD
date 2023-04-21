# JPA-Connection-BBDD


## Database Creating

In this assigment we only need to execute one comand on our SQL:

```
CREATE DATABASE db_name;
```

The reason of that is because our program, is going to create or drop our tables and columns, we'll see later.

## Tools

![JAVA](https://img.shields.io/badge/Java-red?style=for-the-badge)
![MariaDB](https://img.shields.io/badge/MariaDB-00000F?style=for-the-badge&logo=mariadb&logoColor=E2A26C)

## Creating Project

For the project i'm going to use Maven to create the project, here [maven project](https://github.com/FranN2442/JDBC_Connection/blob/master/README.md#Maven-Project) its the same but in the ``` /main ``` we are going to add two directory's  ``` /resources/META-INF ```.

We need to change dependencies from the file ``` pom.xml ``` and add this three dependency's:

```
<dependency>
	<groupId>jakarta.persistence</groupId>
	<artifactId>jakarta.persistence-api</artifactId>
	<version>3.0.0</version>
</dependency>

<dependency> 
	<groupId>org.hibernate</groupId>
	<artifactId>hibernate-core-jakarta</artifactId>
	<version>5.6.4.Final</version>
</dependency>

<dependency>
	<groupId>org.glassfish.jaxb</groupId>
	<artifactId>jaxb-runtime</artifactId>
	<version>3.0.0</version>
</dependency>

<dependency>
	<groupId>org.mariadb.jdbc</groupId>
	<artifactId>mariadb-java-client</artifactId>
	<version>3.0.3</version>
</dependency>
```

## Connection

The first part is create the and configure the connection with jakarta, in ``` /resources/META-INF ``` let's create ``` persistence.xml ``` with this we are going to group a set of clases of which objects can be persisted plus configuration parameters like data base connection and pooling options:

```
<persistence xmlns="http://java.sun.com/xml/ns/persistence"
             xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
             xsi:schemaLocation="http://java.sun.com/xml/ns/persistence http://java.sun.com/xml/ns/persistence/persistence_2_0.xsd"
             version="2.0">
    <persistence-unit name="jpa-demo-local" transaction-type="RESOURCE_LOCAL">
        <properties>
            <property name="jakarta.persistence.jdbc.url" value="jdbc:mariadb://localhost:3306/jpa_demo"/>
            <property name="jakarta.persistence.jdbc.user" value="user"/>
            <property name="jakarta.persistence.jdbc.password" value="password"/>
            <property name="jakarta.persistence.schema-generation.database.action" value="drop-and-create"/>
        </properties>
    </persistence-unit>
</persistence>
```

Then lets create the Class that will contain the specifications about the table, this clas will create the table insede the database.

Lets implement Entity, an entity is a class of which instances we want to persist in the database. A JPA entity class needs to have the @Entity annotation, an @Id-annotated field to represent the primary key, default constructor, and getters for its properties. However, additional configuration can be added, such as specifying the SQL table and column names, using a generated value for primary keys, and adding custom constructors, equals(Object) and hashCode() methods, and setters for its properties. The example mentioned had these additional configurations.

It is recommended to have only one instance of ``` EntityManagerFactory ``` per Persistence Unit for an application. EntityManagerFactory is a costly object to create and maintain, and it is better to reuse it rather than creating multiple instances. On the other hand, EntityManager objects are relatively cheaper to create, and you should create them as needed. This helps to improve the performance and scalability of the application.

### Inserting

To insert inside the database we need to use the ``` persist() ``` method:

```
entityManager.persist(new Class_Name(atributes));
```

To get all the values of the table  stored as a list, lets use ``` createQuery() ``` method:

```
ist<ProgrammingLanguage> list = entityManager.createQuery(   
        "select p from ProgrammingLanguage p where p.rating > 5",
        ProgrammingLanguage.class
).getResultList();
```
With ``` createQuery() ``` method we can do more query's like:

```
SELECT * FROM Tipos ORDER BY nombre ASC;

SELECT COUNT(*) FROM Tipos WHERE numero >= 8;
```


