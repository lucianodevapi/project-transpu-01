
FROM openjdk:17-jdk-alpine as builder

WORKDIR /app/ms-user

COPY ./pom.xml /app
COPY ./ms-user/.mvn ./.mvn
COPY ./ms-user/mvnw .
COPY ./ms-user/pom.xml .

RUN ./mvnw clean package -Dmaven.test.skip -Dmaven.main.skip -Dspring-boot.repackage.skip && rm -r ./target/

#RUN ./mvnw dependency:go-offline

COPY ./ms-user/src ./src

RUN ./mvnw clean package -DskipTests

FROM openjdk:17-jdk-alpine

WORKDIR /app
RUN mkdir ./logs

COPY --from=builder /app/ms-user/target/ms-user-0.0.1-SNAPSHOT.jar .

EXPOSE 8002

CMD ["java", "-jar", "ms-user-0.0.1-SNAPSHOT.jar"]