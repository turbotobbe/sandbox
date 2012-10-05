#!/bin/bash
# Copyright 2012 linonskogen.se All Rights Reserved.
#
# Launches the Client.
# Stop by Ctrl-C
#
DEMO_BIN="`dirname "$0" | sed -e "s#^\\([^/]\\)#${PWD}/\\1#"`" # sed makes absolute

java -Dlog.dir=${DEMO_BIN}/../log -Dmsg.file=${DEMO_BIN}/../etc/msg.txt -cp "${DEMO_BIN}/../lib/comdemo-${project.version}.jar" se.lingonskogen.demo.comdemo.client.ClientApp "$@"
