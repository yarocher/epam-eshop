# Eshop
E-commerce project (training)

## Data Base 
In package *sql* you can find all necessary .sql scripts for project:
+ **db-reset.sql** - drop and reset all database tables. You need to entry your database first;
+ **db-fill-init.sql** - initialize demonstraition data in database;
+ **db-fill-init-test.sql** - initialize testing data in database.

## Documentation
There is no javadoc supporting, but you can find some project descriptions in *doc* package:
+ **db.png** - data base schema;
+ **uml.png** - UML-diagram of java-classes

## Build
Build tool is not provided here, so if you want to build the project by yourself, you should compile all classes on your own, BUT
only if you need to REbuild project.
There is already ready to deploy build with all necessary files in web-archive *ehsop.war* located in the root of project.

## Deploy
All you need to deploy this project is to copy *eshop.war* file in `<your apache tomcat directory>/webapps` directory. Please be informed that the project was developed for Apache Tomcat
version **8.5.65** 

## Source code
All sources are located in *src* directory

## Web content
Besides *eshop.war*, all web content (except binary .class files) is located in *webapp* directory. There you can find .jsp pages, deployment descriptor (web.xml) and taglib descriptor (.tld)



