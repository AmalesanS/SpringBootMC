FROM java:8-jre
WORKDIR /usr/src
ENV MYSQL_DATABASE=LoanDB
ENV MYSQL_USER=root
ENV MYSQL_PASSWORD=root
ENV MYSQL_CI_URL=jdbc:mysql://localhost:3306/LoanDB
ADD ./target/LoanAuthService-0.0.1-SNAPSHOT.jar /usr/src/LoanAuthService-0.0.1-SNAPSHOT.jar
ENTRYPOINT ["java","-jar","/usr/src/LoanAuthService-0.0.1-SNAPSHOT.jar"]
