@echo off
rem START or STOP Services
rem ----------------------------------
rem Check if argument is STOP or START

if not ""%1"" == ""START"" goto stop

if exist F:\springboot_practice\xampp\hypersonic\scripts\ctl.bat (start /MIN /B F:\springboot_practice\xampp\server\hsql-sample-database\scripts\ctl.bat START)
if exist F:\springboot_practice\xampp\ingres\scripts\ctl.bat (start /MIN /B F:\springboot_practice\xampp\ingres\scripts\ctl.bat START)
if exist F:\springboot_practice\xampp\mysql\scripts\ctl.bat (start /MIN /B F:\springboot_practice\xampp\mysql\scripts\ctl.bat START)
if exist F:\springboot_practice\xampp\postgresql\scripts\ctl.bat (start /MIN /B F:\springboot_practice\xampp\postgresql\scripts\ctl.bat START)
if exist F:\springboot_practice\xampp\apache\scripts\ctl.bat (start /MIN /B F:\springboot_practice\xampp\apache\scripts\ctl.bat START)
if exist F:\springboot_practice\xampp\openoffice\scripts\ctl.bat (start /MIN /B F:\springboot_practice\xampp\openoffice\scripts\ctl.bat START)
if exist F:\springboot_practice\xampp\apache-tomcat\scripts\ctl.bat (start /MIN /B F:\springboot_practice\xampp\apache-tomcat\scripts\ctl.bat START)
if exist F:\springboot_practice\xampp\resin\scripts\ctl.bat (start /MIN /B F:\springboot_practice\xampp\resin\scripts\ctl.bat START)
if exist F:\springboot_practice\xampp\jetty\scripts\ctl.bat (start /MIN /B F:\springboot_practice\xampp\jetty\scripts\ctl.bat START)
if exist F:\springboot_practice\xampp\subversion\scripts\ctl.bat (start /MIN /B F:\springboot_practice\xampp\subversion\scripts\ctl.bat START)
rem RUBY_APPLICATION_START
if exist F:\springboot_practice\xampp\lucene\scripts\ctl.bat (start /MIN /B F:\springboot_practice\xampp\lucene\scripts\ctl.bat START)
if exist F:\springboot_practice\xampp\third_application\scripts\ctl.bat (start /MIN /B F:\springboot_practice\xampp\third_application\scripts\ctl.bat START)
goto end

:stop
echo "Stopping services ..."
if exist F:\springboot_practice\xampp\third_application\scripts\ctl.bat (start /MIN /B F:\springboot_practice\xampp\third_application\scripts\ctl.bat STOP)
if exist F:\springboot_practice\xampp\lucene\scripts\ctl.bat (start /MIN /B F:\springboot_practice\xampp\lucene\scripts\ctl.bat STOP)
rem RUBY_APPLICATION_STOP
if exist F:\springboot_practice\xampp\subversion\scripts\ctl.bat (start /MIN /B F:\springboot_practice\xampp\subversion\scripts\ctl.bat STOP)
if exist F:\springboot_practice\xampp\jetty\scripts\ctl.bat (start /MIN /B F:\springboot_practice\xampp\jetty\scripts\ctl.bat STOP)
if exist F:\springboot_practice\xampp\hypersonic\scripts\ctl.bat (start /MIN /B F:\springboot_practice\xampp\server\hsql-sample-database\scripts\ctl.bat STOP)
if exist F:\springboot_practice\xampp\resin\scripts\ctl.bat (start /MIN /B F:\springboot_practice\xampp\resin\scripts\ctl.bat STOP)
if exist F:\springboot_practice\xampp\apache-tomcat\scripts\ctl.bat (start /MIN /B /WAIT F:\springboot_practice\xampp\apache-tomcat\scripts\ctl.bat STOP)
if exist F:\springboot_practice\xampp\openoffice\scripts\ctl.bat (start /MIN /B F:\springboot_practice\xampp\openoffice\scripts\ctl.bat STOP)
if exist F:\springboot_practice\xampp\apache\scripts\ctl.bat (start /MIN /B F:\springboot_practice\xampp\apache\scripts\ctl.bat STOP)
if exist F:\springboot_practice\xampp\ingres\scripts\ctl.bat (start /MIN /B F:\springboot_practice\xampp\ingres\scripts\ctl.bat STOP)
if exist F:\springboot_practice\xampp\mysql\scripts\ctl.bat (start /MIN /B F:\springboot_practice\xampp\mysql\scripts\ctl.bat STOP)
if exist F:\springboot_practice\xampp\postgresql\scripts\ctl.bat (start /MIN /B F:\springboot_practice\xampp\postgresql\scripts\ctl.bat STOP)

:end

