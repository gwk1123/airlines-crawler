FROM openjdk:8
VOLUME /date/crawler
ADD airlines-crawler-0.0.1-SNAPSHOT.jar airlines-crawler.jar
EXPOSE 19086
ENTRYPOINT ["java","-jar","/airlines-crawler.jar"]
