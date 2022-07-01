<h1 align="center">Echo Server</h1>

This Echo Server project consists of a client and server communicating over a socket connection. It is written in Java, without the use of any external dependencies.

After the server establishes a connection with the client, the server will accept the client's messages and echo them back to the client as a data stream.

## Table of Contents

- [Getting Started](#getting_started)
  - [Requirements](#requirements)
  - [Installation](#installation)
  - [Formatting](#formatting)
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

Run the linter:
```
./gradlew :app:checkstyleMain
```

### Testing <a name = "testing"></a>

Run tests:
```
./gradlew test
```

## Usage <a name="usage"></a>

### Launch the program <a name = "launching"></a>

From program directory:
```
./gradlew run
```

### Instructions

tbd
