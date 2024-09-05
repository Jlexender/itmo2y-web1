#!/usr/local/bin/bash

killall java
killall httpd

export JAVA_VERSION=21
httpd -f $HOME/httpd-root/conf/httpd.conf -k start
nohup java -jar $HOME/webapp/fcgi-bin/server.jar > output.log 2>&1 &
