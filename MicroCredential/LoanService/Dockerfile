FROM java:8-jre
WORKDIR /usr/src
ENV MYSQL_DATABASE=LoanDB
ENV MYSQL_USER=app_root
ENV MYSQL_PASSWORD=root
ENV MYSQL_CI_URL=jdbc:mysql://localhost:3306/LoanDB
ADD ./target/LoanService-0.0.1-SNAPSHOT.jar /usr/src/LoanService-0.0.1-SNAPSHOT.jar
ENTRYPOINT ["java","-jar","/usr/src/LoanService-0.0.1-SNAPSHOT.jar"]
