#!/bin/bash

LOG_FILE="$HOME/deploy.log"

echo "Starting deployment script" | tee -a $LOG_FILE

echo "Setting JAVA_VERSION to 21" | tee -a $LOG_FILE
export JAVA_VERSION=21

echo "Stopping any running httpd processes" | tee -a $LOG_FILE
killall httpd 2>> $LOG_FILE
sleep 1

echo "Stopping java processes" | tee -a $LOG_FILE
killall java 2>> $LOG_FILE

echo "Starting Apache httpd" | tee -a $LOG_FILE
httpd -f $HOME/httpd-root/conf/httpd.conf -k start 2>> $LOG_FILE
echo "Apache httpd started" | tee -a $LOG_FILE
sleep 1

echo "Starting Java application" | tee -a $LOG_FILE
java -XX:MaxHeapSize=1G -XX:MaxMetaspaceSize=128m -DFCGI_PORT=45455 -jar $HOME/webapp/fcgi-bin/server.jar >> $LOG_FILE 2>&1 &
echo "Java application started" | tee -a $LOG_FILE

echo "Deployment script completed" | tee -a $LOG_FILE
