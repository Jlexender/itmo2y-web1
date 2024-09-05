#!/bin/sh

killall java
killall httpd

export JAVA_VERSION=21
httpd -f "$HOME/httpd-root/conf/httpd.conf" -k start

nohup java -DFCGI_PORT=45455 -jar "$HOME/webapp/fcgi-bin/server.jar" > "$HOME/output.log" 2>&1 &

echo $! > "$HOME/server.pid"
