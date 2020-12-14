mvn clean install

echo "copying"
cp /Users/sudvee/eclipse-workspace/cfm/java/addressbook/target/addressbook-0.0.1-SNAPSHOT.war /Users/sudvee/install/apache-tomcat-9.0.41/webapps/addressbook.war
echo "copied"
tail -f /Users/sudvee/eclipse-workspace/logs/addressbook.log
