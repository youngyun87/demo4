FROM openjdk:11

EXPOSE 8080

ADD build/libs/demo4-1.jar demo4-1.jar

ENTRYPOINT ["java","-jar","demo4-1.jar"]