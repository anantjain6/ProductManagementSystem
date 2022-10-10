FROM openjdk:11.0.11-jre-buster
ARG JAR_FILE=target/*.war
COPY ${JAR_FILE} app.war
EXPOSE 8083
ENTRYPOINT ["java","-jar","/app.war"]
