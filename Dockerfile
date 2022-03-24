FROM openjdk:8-jre-alpine
EXPOSE 8080
COPY ./target/echo-*.jar /usr/app/echo.jar
WORKDIR /usr/app
CMD java -jar echo-*.jar