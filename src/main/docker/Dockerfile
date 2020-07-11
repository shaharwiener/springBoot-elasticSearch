FROM java:8
ADD springBoot-elasticSearch-0.0.1-SNAPSHOT.jar app.jar
RUN bash -c 'touch /app.jar'
EXPOSE 9000 9001
ENTRYPOINT ["java","-Xdebug","-Xrunjdwp:transport=dt_socket,server=y,suspend=n,address=9001", "-Djava.security.egd=file:/dev/./urandom","-jar","/app.jar"]