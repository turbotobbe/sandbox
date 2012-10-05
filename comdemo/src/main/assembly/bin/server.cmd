@echo off
rem Copyright 2012 Sigma AB. All Rights Reserved.

rem Launches the Server.
rem Stop by Ctrl-C

java -Dlog.dir=%~dp0..\log -cp "%~dp0..\lib\comdemo-${project.version}.jar" se.lingonskogen.demo.comdemo.server.ServerApp %*


