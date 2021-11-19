FROM openjdk:11 AS builder

COPY . .

RUN ["touch", "src/main/resources/aws.yml"]

RUN ["./gradlew", "assemble"]

FROM openjdk:11

COPY --from=builder /build/libs/app.jar app.jar

ENTRYPOINT ["java", "-jar", "-Dspring.profiles.active=prod", "app.jar"]
