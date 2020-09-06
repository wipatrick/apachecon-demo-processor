# Licensed to the Apache Software Foundation (ASF) under one or more
# contributor license agreements.  See the NOTICE file distributed with
# this work for additional information regarding copyright ownership.
# The ASF licenses this file to You under the Apache License, Version 2.0
# (the "License"); you may not use this file except in compliance with
# the License.  You may obtain a copy of the License at
#
#    http://www.apache.org/licenses/LICENSE-2.0
#
# Unless required by applicable law or agreed to in writing, software
# distributed under the License is distributed on an "AS IS" BASIS,
# WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
# See the License for the specific language governing permissions and
# limitations under the License.

# build stage
FROM maven:3.6.3-jdk-8-openj9 AS build
COPY src /usr/src/app/src
COPY pom.xml /usr/src/app
RUN mvn -f /usr/src/app/pom.xml clean package

# final image
FROM adoptopenjdk/openjdk8-openj9:alpine-slim
EXPOSE 8090
ENV CONSUL_LOCATION consul
COPY --from=build /usr/src/app/target/apachecon-demo-processor.jar  /streampipes-processing-element-container.jar
ENTRYPOINT ["java", "-jar", "/streampipes-processing-element-container.jar"]
