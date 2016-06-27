FROM jboss/wildfly:10.0.0.Final
ADD TestingProject.war /opt/jboss/wildfly/standalone/deployments/
ADD TestingProject.war.dodeploy /opt/jboss/wildfly/standalone/deployments/
ADD standalone-custom.xml /opt/jboss/wildfly/standalone/configuration
ADD main/ /opt/jboss/wildfly/modules/system/layers/base/com/mysql/main/
ADD putente.jks /opt/jboss/wildfly/standalone/configuration
ADD bucketkey.json /opt/jboss/wildfly/standalone/configuration
ADD notakey.key /opt/jboss/wildfly/standalone/configuration
CMD ["/opt/jboss/wildfly/bin/standalone.sh", "-b", "0.0.0.0","--server-config=standalone-custom.xml"]
EXPOSE 8080 8443
