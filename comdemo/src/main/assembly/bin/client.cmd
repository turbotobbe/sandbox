@echo off
rem Copyright 2012 lingonskogen.se All Rights Reserved.

rem Launches the Client.
rem Stop by Ctrl-C

java -Dlog.dir=%~dp0..\log -Dmsg.file=%~dp0..\etc\msg.txt -cp "%~dp0..\lib\comdemo-${project.version}.jar" se.lingonskogen.demo.comdemo.client.ClientApp %*


