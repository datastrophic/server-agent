# Server HealthCheck Agent
## Overview
Server HealthCheck Agent is a daemon reporting host health checks to console. Current implementation
supports two modes of operation:
 * heartbeat - agent sends heartbeat messages to console with specified frequency
 * file check - agent checks for file presence in specified location with specified frequency

Program arguments:
 * `--type` can be one of `heartbeat` or `filecheck`
 * `--frequency` time period for repeatable check in seconds
 * `--location` (mandatory for `filecheck` mode only) - location of a file to check for existence

## Building and running
Agent is using SBT for building and packaging the code. To build a fatjar with all dependencies included run:

    sbt clean assembly

When agent is packaged it can be executed with `java -jar <agent.jar location>`. E.g. from project root:

    #heartbeat mode
    java -jar target/scala-2.12/agent.jar --type heartbeat --frequency 5

    #file check mode
    java -jar target/scala-2.12/agent.jar --type filecheck --location <path to file> --frequency 5