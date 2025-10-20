#!/bin/sh
# ----------------------------------------------------------------------------
# Licensed to the Apache Software Foundation (ASF) under one
# or more contributor license agreements.  See the NOTICE file
# distributed with this work for additional information
# regarding copyright ownership.  The ASF licenses this file
# to you under the Apache License, Version 2.0 (the
# "License"); you may not use this file except in compliance
# with the License.  You may obtain a copy of the License at
#
#    https://www.apache.org/licenses/LICENSE-2.0
#
# Unless required by applicable law or agreed to in writing,
# software distributed under the License is distributed on an
# "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
# KIND, either express or implied.  See the License for the
# specific language governing permissions and limitations
# under the License.
# ----------------------------------------------------------------------------

# ----------------------------------------------------------------------------
# Apache Maven Wrapper startup batch script, v3.3.2
# ----------------------------------------------------------------------------

set -euf

# OS specific support.
native_path() { printf %s\n "$1" ; }
case "$(uname)" in
CYGWIN*|MINGW*)
  native_path() { cygpath --path --windows "$1"; }
  ;;
esac

# set java executable
if [ -n "${JAVA_HOME:-}" ] ; then
  if [ -x "$JAVA_HOME/bin/java" ] ; then
    JAVACMD="$JAVA_HOME/bin/java"
  else
    echo "Error: JAVA_HOME is set to an invalid directory: $JAVA_HOME" >&2
    exit 1
  fi
else
  JAVACMD="java"
fi

# Verify Java
if ! "$JAVACMD" -version >/dev/null 2>&1 ; then
  echo "Error: Java (or JAVA_HOME) not found; please install Java 17+" >&2
  exit 1
fi

WRAPPER_DIR="$(cd """$(dirname "$0")"""; pwd)/.mvn/wrapper"
WRAPPER_JAR="$WRAPPER_DIR/maven-wrapper.jar"
WRAPPER_PROP="$WRAPPER_DIR/maven-wrapper.properties"

# Download wrapper jar if missing
if [ ! -f "$WRAPPER_JAR" ]; then
  mkdir -p "$WRAPPER_DIR"
  WRAPPER_URL=$(sed -n 's/^wrapperUrl=\(.*\)$/\1/p' "$WRAPPER_PROP")
  if [ -z "$WRAPPER_URL" ]; then
    echo "Error: wrapperUrl not found in $WRAPPER_PROP" >&2
    exit 1
  fi
  echo "Downloading Maven Wrapper JAR from: $WRAPPER_URL" >&2
  if command -v curl >/dev/null 2>&1; then
    curl -fsSL -o "$WRAPPER_JAR" "$WRAPPER_URL"
  elif command -v wget >/dev/null 2>&1; then
    wget -q -O "$WRAPPER_JAR" "$WRAPPER_URL"
  else
    echo "Error: curl or wget is required to download the Maven Wrapper" >&2
    exit 1
  fi
fi

# Provide a default JVM opts if not set
MAVEN_OPTS="${MAVEN_OPTS:-}"

# Launch the Maven Wrapper
exec "$JAVACMD" $MAVEN_OPTS \
  -classpath "$(native_path "$WRAPPER_JAR")" \
  -Dmaven.multiModuleProjectDirectory="$(native_path "$(pwd)")" \
  org.apache.maven.wrapper.MavenWrapperMain "$@"
