FROM openjdk:11-jdk-slim
RUN addgroup --system spring && adduser --system --ingroup spring spring
USER spring:spring
ADD target/*.jar app.jar
ENTRYPOINT ["java", "-jar", "app.jar"]
# Spring Boot configuration can be passed using the docker run -e flag, example:
# docker run -p 8080:8080 -e PRODUCT_REST_ENDPOINT=http://localhost:8080/products kafka-pubsub-sb