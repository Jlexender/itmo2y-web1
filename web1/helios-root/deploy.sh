#!/usr/local/bin/bash

killall java
killall httpd

httpd -f $HOME/httpd-root/conf/httpd.conf -k start
export JAVA_VERSION=21
java -XX:MaxHeapSize=1G -XX:MaxMetaspaceSize=128m -DFCGI_PORT=45455 -jar $HOME/webapp/fcgi-bin/server.jar &
