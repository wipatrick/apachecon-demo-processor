## Apache StreamPipes Demo @ApacheCon
How to write your own StreamPipes data processor? Simple `greeter` data processor appending a user-defined text to an input event stream.

![MyGreeter Demo](demo/apachecon.gif)

## Prequisite
* Java 8 JDK (minimum)
* Maven (tested with 3.6)
* Docker >= 17.06.0
* Docker-Compose >= 1.17.0 (Compose file format: 3.4)
* Google Chrome (recommended)
* IntelliJ (reccommended)
* For Windows Developer: GitBash only

## Usage

**Step 1**: Clone this repo
```bash
git clone https://github.com/wipatrick/apachecon-demo-processor
```

**Step 2**: Clone StreamPipes installer repo
> **Hint**: read StreamPipes CLI `README` for more details on various options.
```bash
git clone https://github.com/apache/incubator-streampipes-installer
cd cli
```

**Step 3**: Setup StreamPipes with **[StreamPipes CLI](https://github.com/apache/incubator-streampipes-installer/cli)** - The Developer's Favorite
Set dev environment for `pipeline-elements` and start it.
```bash
streampipes env --set pipeline-element
streampipes up -d
```
Go to [http://localhost](http://localhost) to finish the installation in the browser.

**Step 4**: Import maven project

**Step 5**: Run project

If you use IntelliJ you're ready to go. Just use our run config `MyGreeterProcessor` (see `.idea/runConfiguration`) which
should be auto-loaded when importing the Maven project and run the application.

## What's next? Where to go from here?
You can package the data processor as a Docker image (see `Dockerfile`) and deploy it.