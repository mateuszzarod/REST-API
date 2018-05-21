
call gradlew build
echo %ERRORLEVEL%
if "%ERRORLEVEL%" == "0" goto rename
echo.
echo GRADLEW BUILD has errors - breaking work
goto fail

:rename
echo %ERRORLEVEL%
echo zaczynam komende rename i pokazuje katalogi
dir build\libs\
echo usuwam plik crud.war
del build\libs\crud.war
dir build\libs\
echo %ERRORLEVEL%
echo zmieniam nazwe tasks-0.0.1-SNAPSHOT.war na crud.war
dir build\libs

ren tasks\build\libs crud.war

echo %ERRORLEVEL%
if "%ERRORLEVEL%" == "0" goto stoptomcat
echo Cannot rename file
goto fail

:stoptomcat
call %CATALINA_HOME%\bin\shutdown.bat

:copyfile
copy build\libs\crud.war %CATALINA_HOME%\webapps
if "%ERRORLEVEL%" == "0" goto runtomcat
echo Cannot copy file
goto fail

:runtomcat
call %CATALINA_HOME%\bin\startup.bat
goto end

:fail
echo.
echo There were errors

:end
echo.
echo Work is finished.