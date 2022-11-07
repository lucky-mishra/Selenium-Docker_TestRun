#!/usr/bin/env bash
# Environment Variables
# HUB_HOST
# BROWSER
# MODULE

echo "Checking if hub is ready "

while [ "$( curl -s http://172.26.0.1:4444/wd/hub/status | jq -r .value.ready )" != "true" ]
do
	sleep 1
done

# start the java command
mvn -f /home/SeleniumFramework/pom.xml clean test