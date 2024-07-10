FROM tomcat:9.0.89-jre8
RUN rm -rf /usr/local/tomcat/webapps/*
COPY /target/seller.war /usr/local/tomcat/webapps/seller.war
COPY /target/util.war /usr/local/tomcat/webapps/util.war
COPY /target/dealer.war /usr/local/tomcat/webapps/dealer.war
EXPOSE 90
CMD [ "catalina.sh", "run" ]