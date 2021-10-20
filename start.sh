mvn clean
mvn install
nohup java  -Xms256m -Xmx256m -jar ./target/ccb-api-0.0.1-SNAPSHOT.jar --spring.profiles.active=prod &