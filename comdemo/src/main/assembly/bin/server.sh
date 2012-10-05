#!/bin/bash
# Copyright 2012 linonskogen.se All Rights Reserved.
#
# Launches the Server.
# Stop by Ctrl-C
#
DEMO_BIN="`dirname "$0" | sed -e "s#^\\([^/]\\)#${PWD}/\\1#"`" # sed makes absolute

java -Dlog.dir=${DEMO_BIN}/../log -cp "${DEMO_BIN}/../lib/comdemo-${project.version}.jar" se.lingonskogen.demo.comdemo.server.ServerApp "$@"
