
FROM openjdk:17-jdk-alpine as builder

WORKDIR /app/ms-company

COPY ./pom.xml /app
COPY ./ms-company/.mvn ./.mvn
COPY ./ms-company/mvnw .
COPY ./ms-company/pom.xml .

RUN ./mvnw clean package -Dmaven.test.skip -Dmaven.main.skip -Dspring-boot.repackage.skip && rm -r ./target/

#RUN ./mvnw dependency:go-offline

COPY ./ms-company/src ./src

RUN ./mvnw clean package -DskipTests

FROM openjdk:17-jdk-alpine

WORKDIR /app
RUN mkdir ./logs

COPY --from=builder /app/ms-company/target/ms-company-0.0.1-SNAPSHOT.jar .

EXPOSE 8001

CMD ["java", "-jar", "ms-company-0.0.1-SNAPSHOT.jar"]