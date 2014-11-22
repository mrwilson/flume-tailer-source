docker build -t tailer-flume-example

docker run -t -i \
  -e FLUME_AGENT_NAME=fsink \
  -e FLUME_CONF_FILE=/var/tmp/flume.conf \
  tailer-flume-example
