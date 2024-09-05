#!/bin/sh

# Kill existing processes
killall java || echo "No java process found"
killall httpd || echo "No httpd process found"

# Start httpd server
export JAVA_VERSION=21

echo "Starting httpd server..."
httpd -f "$HOME/httpd-root/conf/httpd.conf" -k start
if [ $? -ne 0 ]; then
    echo "Failed to start httpd server"
    exit 1
fi

# Start Java server with better logging
echo "Starting Java server..."
nohup java -DFCGI_PORT=45455 -jar "$HOME/webapp/fcgi-bin/server.jar" > "$HOME/output.log" 2> "$HOME/error.log" &

# Check if Java process started successfully
if [ $? -ne 0 ]; then
    echo "Failed to start Java server"
    exit 1
fi

# Store the process ID
echo $! > "$HOME/server.pid"
echo "Java server started with PID $(cat $HOME/server.pid)"
