FROM openjdk:11 AS builder

COPY . .

RUN ["./gradlew", "assemble"]

FROM openjdk:11

COPY --from=builder /build/libs/app.jar app.jar

ENTRYPOINT ["java", "-jar", "app.jar"]
