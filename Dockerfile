FROM maven:3.3.9-jdk-8-alpine
WORKDIR /opt
ENV PORT 3030
EXPOSE 3030
COPY target/*.jar /opt/app.jar
ENTRYPOINT exec java $JAVA_OPTS -jar app.jar



