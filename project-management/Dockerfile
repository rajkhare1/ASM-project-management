FROM ubuntu-jdk

MAINTAINER Raj Khare "raj.khare.java@gmail.com"

ENV version=Docker
ENV jdbcurl
ENV dbuser
ENV dbpass

WORKDIR /usr/local/bin

ADD /target/pma-app.jar .

ENTRYPOINT ["java","-jar","pma-app.jar"]
