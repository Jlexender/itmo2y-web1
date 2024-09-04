#!/bin/sh

killall java
killall httpd

export JAVA_VERSION=21
httpd -f $HOME/httpd-root/conf/httpd.conf -k start
nohup java -XX:MaxHeapSize=1G -XX:MaxMetaspaceSize=128m -DFCGI_PORT=45455 -jar $HOME/httpd-root/fcgi-bin/server.jar > server.log 2>&1 &
