FROM openjdk:11
ADD build/libs/product-service-0.0.1-SNAPSHOT.jar product-service-0.0.1-SNAPSHOT.jar
ENTRYPOINT [ "java", "-jar", "product-service-0.0.1-SNAPSHOT.jar" ]