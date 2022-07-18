<h1 align="center">Echo Server</h1>

This Echo Server project consists of a client and server communicating over a socket connection. It is written in Java, without the use of any external dependencies.

After the server establishes a connection with the client, the server will accept the client's messages and echo them back to the client as a data stream.

## Table of Contents

- [Getting Started](#getting_started)
  - [Requirements](#requirements)
  - [Installation](#installation)
  - [Linting](#linting)
  - [Testing](#testing)
- [Usage](#usage)
  - [Launching the Game](#launching)
  - [Instructions](#instructions)

## Getting Started <a name = "getting_started"></a>

### Requirements <a name = "requirements"></a>

- <a href="https://www.oracle.com/java/technologies/javase/jdk17-archive-downloads.html">Java 17</a>

### Installation <a name = "installation"></a>

Clone this repo from the terminal:
```
git clone https://github.com/fifikim/echo-server.git
```

Navigate to program root directory:
```
cd echo-server
```  

Build the project:
```
./gradlew build
```

Automated linting and testing has been incorporated into the build process. However, each of these tasks may also be run individually if desired:

### Linting <a name = "linting"></a>

Run the linter for source code:
```
./gradlew :app:checkstyleMain
```

Run the linter for the test suite:
```
./gradlew :app:checkstyleTest
```

### Testing <a name = "testing"></a>

Run tests:
```
./gradlew test
```

## Usage <a name="usage"></a>

### Launch the program <a name = "launching"></a>

First run the server:
```
./gradlew runServer
```

Run the client from a separate terminal:
```
./gradlew runClient
```

If you wish to override the default settings for port number and host name, you may instead use the following commands (using the same port number for both):

```
./gradlew runServer --args <port-number>
```
```
./gradlew runClient --args="<host-name> <port-number>"
```

### Instructions

Follow the launch instructions above to first run the server and then one or more instances of the client (each from a separate terminal). 

From the client, enter a message to send to the server. The server will respond with your message echoed.

Enter "quit" to end your session.
