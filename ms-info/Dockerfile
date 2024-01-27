
FROM openjdk:17-jdk-alpine as builder

WORKDIR /app/ms-info

COPY ./pom.xml /app
COPY ./ms-info/.mvn ./.mvn
COPY ./ms-info/mvnw .
COPY ./ms-info/pom.xml .

RUN ./mvnw clean package -Dmaven.test.skip -Dmaven.main.skip -Dspring-boot.repackage.skip && rm -r ./target/

#RUN ./mvnw dependency:go-offline

COPY ./ms-info/src ./src

RUN ./mvnw clean package -DskipTests

FROM openjdk:17-jdk-alpine

WORKDIR /app
RUN mkdir ./logs

COPY --from=builder /app/ms-info/target/ms-info-0.0.1-SNAPSHOT.jar .

EXPOSE 8003

CMD ["java", "-jar", "ms-info-0.0.1-SNAPSHOT.jar"]