FROM tomcat:8.0.45-jre8

ENV AGENT_FILE_PATH=/usr/local/tomcat/agent \
    COLLECTOR_SERVER=127.0.0.1

COPY catalina.sh /usr/local/tomcat/bin/
COPY opentracing-case.war /usr/local/tomcat/webapps
COPY sky-walking.config /usr/local/tomcat/agent-config/sky-walking.config

ADD docker-entrypoint.sh /
RUN chmod +x /usr/local/tomcat/bin/catalina.sh && chmod +x /docker-entrypoint.sh

VOLUME /usr/local/tomcat/agent
ENTRYPOINT ["/docker-entrypoint.sh"]
CMD ["catalina.sh", "run"]
