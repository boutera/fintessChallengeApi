
To Run the App:
you should have already Java 17, 
To check if Java is installed:

java -version


If Java is installed, you’ll see the version info.

If not installed:

Install Java 17 (recommended for Spring Boot 3.x) using Homebrew for macOs:

brew install openjdk@17

after installing it: 
run the spring boot project using maven wrapper:

./mvnw spring-boot:run

> Make sure you're in the root folder of your project (where pom.xml and mvnw are located).




---

If You Get Permission Denied

If ./mvnw gives a permission denied please use:


chmod +x mvnw
./mvnw spring-boot:run



Before Running

Ensure your application.properties (or application.yml) file contains the 
correct PostgreSQL connection info and 
that the DB is running.

for this project that postgres derver config is :
host: localhost,
port: 5432,
username: postgres,
password: admin,
database name: fitness_challenge




