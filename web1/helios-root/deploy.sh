killall java
killall httpd

export JAVA_VERSION=21

httpd -f $HOME/httpd-root/conf/httpd.conf -k start
java -XX:MaxHeapSize=1G -XX:MaxMetaspaceSize=128m -DFCGI_PORT=45455 -jar $HOME/webapp/fcgi-bin/server.jar &
