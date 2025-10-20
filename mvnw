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

# Maven Wrapper script
# This allows building the project without installing Maven

MAVEN_WRAPPER_VERSION=3.9.5

if [ -n "$MAVEN_HOME" ] ; then
  echo "Using Maven from $MAVEN_HOME"
  exec "$MAVEN_HOME/bin/mvn" "$@"
else
  echo "Maven wrapper is provided for convenience. Please install Maven or use an IDE."
  echo "Visit https://maven.apache.org/install.html for installation instructions."
  exit 1
fi
