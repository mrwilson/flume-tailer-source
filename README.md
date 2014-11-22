## flume-tailer-source

[![Build Status](https://travis-ci.org/mrwilson/flume-tailer-source.svg?branch=master)](https://travis-ci.org/mrwilson/flume-tailer-source)

An [Apache Flume](https://flume.apache.org/) sink that uses [Apache Commons Tailer](https://commons.apache.org/proper/commons-io/apidocs/org/apache/commons/io/input/Tailer.html) to tail files and submit each line as an event.

## Example config

```
...
agent.sources.tailSource.type = uk.co.probablyfine.flume.TailerSource
agent.sources.tailSource.filePath = /var/tmp/test.txt
...
```

The only required parameter is `filePath` to point to the source file.

## Installation

Create a jar with `mvn package` and include in the flume [plugins.d](https://flume.apache.org/FlumeUserGuide.html#installing-third-party-plugins) directory. 

## License

MIT
