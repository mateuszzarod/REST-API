call runcrud
if "%ERRORLEVEL%" == "0" goto runbrowser
echo.
echo RUNCRUD has errors - breaking work
goto fail

:runbrowser
start chrome.exe http://localhost:8080/crud/v1/task/getTasks
if "%ERRORLEVEL%" == "0" goto stoptomcat
echo Cannot open the browser.
goto fail

:stoptomcat
call %CATALINA_HOME%\bin\shutdown.bat



:fail
echo.
echo There were errors.

:end
echo.
echo Work is finished.