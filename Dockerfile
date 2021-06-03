FROM openjdk:8
ADD target/bank-account-spring-boot.jar bank-account-spring-boot.jar
EXPOSE 8085
ENTRYPOINT ["java", "-jar", "bank-account-spring-boot.jar"]