FROM maven:3.8.2-openjdk-11-slim AS BUILDER
RUN mkdir /project
WORKDIR /project
COPY . /project
RUN mvn clean package -DskipTests

FROM adoptopenjdk/openjdk11:x86_64-alpine-jre-11.0.12_7
RUN mkdir /app
WORKDIR /app
COPY --from=BUILDER /project/target/prestabooks-0.0.1.jar /app/app.jar
EXPOSE 3000
CMD "java" "-jar" "app.jar"