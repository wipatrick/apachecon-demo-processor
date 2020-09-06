<!--
  ~ Licensed to the Apache Software Foundation (ASF) under one or more
  ~ contributor license agreements.  See the NOTICE file distributed with
  ~ this work for additional information regarding copyright ownership.
  ~ The ASF licenses this file to You under the Apache License, Version 2.0
  ~ (the "License"); you may not use this file except in compliance with
  ~ the License.  You may obtain a copy of the License at
  ~
  ~    http://www.apache.org/licenses/LICENSE-2.0
  ~
  ~ Unless required by applicable law or agreed to in writing, software
  ~ distributed under the License is distributed on an "AS IS" BASIS,
  ~ WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
  ~ See the License for the specific language governing permissions and
  ~ limitations under the License.
  ~
  -->
  
## Apache StreamPipes Demo @ApacheCon
How to write your own StreamPipes data processor? Simple `greeter` data processor appending a user-defined text to an input event stream.

![MyGreeter Demo](img/apachecon.gif)

## Prequisite
* Java 8 JDK (minimum)
* Maven (tested with 3.6)
* Docker >= 17.06.0
* Docker-Compose >= 1.17.0 (Compose file format: 3.4)
* Google Chrome (recommended)
* IntelliJ (reccommended)
* For Windows Developer: GitBash only

## Usage

#### Step 1: Clone repository
```bash
git clone https://github.com/wipatrick/apachecon-demo-processor
```

#### Step 2: Setup StreamPipes environment with **[StreamPipes CLI](https://github.com/apache/incubator-streampipes-installer/cli)**
> **Hint**: read StreamPipes CLI `README` for more details on various options.
```bash
git clone https://github.com/apache/incubator-streampipes-installer
cd cli/
./streampipes env --set pipeline-element
./streampipes up -d
```
Go to [http://localhost](http://localhost) to finish the installation in the browser.

#### Step 3: Import maven project
Open this repo as a project in IntelliJ (recommended).

#### Step 4: Start "MyGreeterProcessor" run configuration
If you use IntelliJ you're ready to go. Just use our run config `MyGreeterProcessor` (see `.idea/runConfiguration`) which
should be auto-loaded when importing the Maven project and run the application.

Since we're running the processor natively on your system, and the rest of StreamPipes inside Docker containers. We need
to make sure that both ends are able to communicate with each other. Thus, we use the environment variables `SP_HOST`,
`SP_PORT` and `SP_DEBUG` to configure the data processor what host and port it is running on and that we're in dev/debug
mode.

**Settings**: 
* **Windows, Mac** user: `SP_HOST=host.docker.internal` is already set.
* **Linux** user: set `SP_HOST` to your `docker0` bridge IP (see `ifconfig`).

> **Note**: if you're not using IntelliJ and want to run the demo you can so by starting the processor from the command line
> `mvn spring-boot:run`. The environment variables such as `SP_HOST` can be set in the `pom.xml`.

## What's next? Where to go from here?
You can package the data processor as a Docker image (see `Dockerfile`) and deploy it.