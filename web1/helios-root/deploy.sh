#!/bin/sh

export JAVA_VERSION=21
httpd -f $HOME/httpd-root/conf/httpd.conf -k start
java -DFCGI_PORT=45455 -jar $HOME/httpd-root/fcgi-bin/server.jar &

echo "Startup finished"
