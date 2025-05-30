FROM openjdk:23
COPY "./target/adopcionmascotas-1.jar" "app.jar"
EXPOSE 8096
ENTRYPOINT [ "java", "-jar", "app.jar" ]
