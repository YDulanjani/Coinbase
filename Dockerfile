FROM maven:3.8.4-jdk-8-slim
WORKDIR /opt
ENV PORT 3030
EXPOSE 3030
COPY target/*.jar /opt/app.jar
ENV JAVA_OPTS = "-Xmx1G"
ENTRYPOINT exec java $JAVA_OPTS -jar app.jar



