FROM maven:3.9.9-eclipse-temurin-21-alpine AS build

WORKDIR /usr/src/app

COPY pom.xml ./
COPY src ./src
RUN mvn clean install

FROM eclipse-temurin:21-jdk-alpine
RUN apk add --no-cache bash
WORKDIR /usr/src/app
COPY wait-for-it.sh /wait-for-it.sh
COPY --from=build /usr/src/app/target/trust-bank-account-service-1.0-SNAPSHOT.jar ./trust-bank-account-service-1.0-SNAPSHOT.jar

EXPOSE 8080

CMD ["bash", "/wait-for-it.sh", "db:5432", "--", "java", "-jar", "trust-bank-account-service-1.0-SNAPSHOT.jar"]