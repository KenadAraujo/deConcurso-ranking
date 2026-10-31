FROM maven:3.9-eclipse-temurin-21 AS build
WORKDIR /build

COPY pom.xml .
COPY src ./src
RUN mvn --batch-mode --no-transfer-progress -DskipTests package

FROM eclipse-temurin:21-jre-jammy
WORKDIR /app

RUN apt-get update \
    && apt-get install -y --no-install-recommends curl \
    && rm -rf /var/lib/apt/lists/* \
    && groupadd --system ranking \
    && useradd --system --gid ranking ranking

COPY --from=build /build/target/ranking-*.jar /app/ranking.jar

USER ranking
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "/app/ranking.jar"]
