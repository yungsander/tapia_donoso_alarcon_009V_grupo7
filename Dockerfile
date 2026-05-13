# ── Etapa 1: build ──────────────────────────────────────────────
FROM maven:3.9-eclipse-temurin-21 AS builder

WORKDIR /app

# Copia el pom primero para aprovechar caché de dependencias
COPY pom.xml .
RUN mvn dependency:go-offline -q

# Compila sin tests
COPY src ./src
RUN mvn package -DskipTests -q

# ── Etapa 2: runtime ─────────────────────────────────────────────
FROM eclipse-temurin:21-jre-alpine

WORKDIR /app

COPY --from=builder /app/target/*.jar app.jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "app.jar"]
