FROM openjdk:17
COPY target/web_lab4-0.0.1-SNAPSHOT.jar app/backEnd/web_lab4-0.0.1-SNAPSHOT.jar
CMD java -jar /app/backEnd/web_lab4-0.0.1-SNAPSHOT.jar
