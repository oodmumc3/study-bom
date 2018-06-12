FROM mdock.daumkakao.io/tomcat:8.0.20-jre8
RUN rm -rf /usr/local/tomcat/webapps/ROOT.war
RUN rm -rf /usr/local/tomcat/webapps/ROOT
COPY ./build/libs/bom-web-1.0-SNAPSHOT.war /usr/local/tomcat/webapps/ROOT.war
