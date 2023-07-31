FROM openjdk:11-jdk

WORKDIR /app

COPY ./build/libs/ApiStream.jar /app/ApiStream.jar

CMD ["java", "-server", "-Xmx512m", "-Xss512k", "-jar", "/app/ApiStream.jar"]
