@ECHO OFF
@REM ----------------------------------------------------------------------------
@REM Licensed to the Apache Software Foundation (ASF) under one
@REM or more contributor license agreements.  See the NOTICE file
@REM distributed with this work for additional information
@REM regarding copyright ownership.  The ASF licenses this file
@REM to you under the Apache License, Version 2.0 (the
@REM "License"); you may not use this file except in compliance
@REM with the License.  You may obtain a copy of the License at
@REM 
@REM    https://www.apache.org/licenses/LICENSE-2.0
@REM 
@REM Unless required by applicable law or agreed to in writing,
@REM software distributed under the License is distributed on an
@REM "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
@REM KIND, either express or implied.  See the License for the
@REM specific language governing permissions and limitations
@REM under the License.
@REM ----------------------------------------------------------------------------

@REM ----------------------------------------------------------------------------
@REM Apache Maven Wrapper startup script, v3.3.2
@REM ----------------------------------------------------------------------------

setlocal
set ERROR_CODE=0

if NOT "%JAVA_HOME%"=="" goto findJavaFromJavaHome

set JAVA_EXE=java.exe
%JAVA_EXE% -version >NUL 2>&1
if "%ERRORLEVEL%" == "0" goto init

echo Error: Java (or JAVA_HOME) not found; please install Java 17+ 1>&2
set ERROR_CODE=1
goto end

:findJavaFromJavaHome
set JAVA_HOME=%JAVA_HOME:~0,-1%%JAVA_HOME:~-1%
set JAVA_EXE=%JAVA_HOME%\bin\java.exe

if exist "%JAVA_EXE%" goto init
echo Error: JAVA_HOME is set to an invalid directory: %JAVA_HOME% 1>&2
set ERROR_CODE=1
goto end

:init
set WRAPPER_JAR=.mvn\wrapper\maven-wrapper.jar
set WRAPPER_PROP=.mvn\wrapper\maven-wrapper.properties

if exist "%WRAPPER_JAR%" goto run
for /F "tokens=1,* delims==" %%A in (%WRAPPER_PROP%) do (
  if "%%A"=="wrapperUrl" set WRAPPER_URL=%%B
)
if not defined WRAPPER_URL (
  echo Error: wrapperUrl not found in %WRAPPER_PROP% 1>&2
  set ERROR_CODE=1
  goto end
)
echo Downloading Maven Wrapper JAR from: %WRAPPER_URL% 1>&2
where curl >NUL 2>&1
if "%ERRORLEVEL%" == "0" (
  curl -fsSL -o "%WRAPPER_JAR" "%WRAPPER_URL%"
) else (
  where wget >NUL 2>&1
  if "%ERRORLEVEL%" == "0" (
    wget -q -O "%WRAPPER_JAR" "%WRAPPER_URL%"
  ) else (
    echo Error: curl or wget is required to download the Maven Wrapper 1>&2
    set ERROR_CODE=1
    goto end
  )
)

:run
"%JAVA_EXE%" %MAVEN_OPTS% -classpath "%WRAPPER_JAR%" -Dmaven.multiModuleProjectDirectory="%CD%" org.apache.maven.wrapper.MavenWrapperMain %*
set ERROR_CODE=%ERRORLEVEL%

:end
exit /B %ERROR_CODE%
