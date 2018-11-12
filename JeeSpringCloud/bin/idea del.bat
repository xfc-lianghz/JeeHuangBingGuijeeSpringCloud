@echo off
rem /**
rem  */
echo.
echo [info] del project info and target。
echo.

cd ..\

if exist .\jeespring-web\target (  
	rmdir /s/q .\jeespring-web\target
	del .\jeespring-web\*.iml
	echo [info] del .\jeespring-web\target
)

if exist .\jeespring-mq\target (  
	rmdir /s/q .\jeespring-mq\target
	del .\jeespring-mq\*.iml
	echo [info] del .\jeespring-mq\target
)

if exist .\jeespring-framework\target (
	rmdir /s/q .\jeespring-framework\target
	del .\jeespring-framework\*.iml
	echo [info] del .\jeespring-mq\target
)

if exist .\jeespring-company\target (
	rmdir /s/q .\jeespring-company\target
	del .\jeespring-company\*.iml
	echo [info] del .\jeespring-company\target
)

if exist .\jeespring-cms\target (
	rmdir /s/q .\jeespring-cms\target
	del .\jeespring-cms\*.iml
	echo [info] del .\jeespring-cms\target
)

if exist .\projectFilesBackup (
	rmdir /s/q .\projectFilesBackup
	echo [info] del .\projectFilesBackup
)

del jeespring.iml
del jeespring.ipr
del jeespring.iws

echo [info] deal end
pause