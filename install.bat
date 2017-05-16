@echo off
echo [INFO] Install jar/war to local repository.

cd /d %~dp0
call mvn clean install -Dmaven.test.skip=true
pause