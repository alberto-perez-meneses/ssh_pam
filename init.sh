#!/bin/bash

echo "iniciando"
sleep 3

#java -jar demo-0.0.1-SNAPSHOT.jar -Dcom.sun.management.jmxremote -Dcom.sun.management.jmxremote.port=9081 -Dcom.sun.management.jmxremote.local.only=false -Dcom.sun.management.jmxremote.authenticate=false -Djava.rmi.server.hostname=172.17.202.21
java -jar sshConnect-0.0.1-SNAPSHOT.jar --spring.config.location=file:./application.properties > salida.txt &

ps -fea | grep demo-0.0.1-SNAPSHOT.jar | grep -v grep | awk '{print $2}' > java.pid

#java -jar demo-0.0.1-SNAPSHOT.jar > salida.txt &
