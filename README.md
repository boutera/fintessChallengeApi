
To Run the App:

./mvnw spring-boot:run

> Make sure you're in the root folder of your project (where pom.xml and mvnw are located).




---

If You Get Permission Denied

If ./mvnw gives a permission error, make it executable:

chmod +x mvnw
./mvnw spring-boot:run



Before Running

Ensure your application.properties (or application.yml) file contains the correct PostgreSQL connection info and that the DB is running.


