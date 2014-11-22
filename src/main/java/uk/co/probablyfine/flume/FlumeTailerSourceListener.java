package uk.co.probablyfine.flume;

import org.apache.commons.io.input.Tailer;
import org.apache.commons.io.input.TailerListener;
import org.apache.flume.FlumeException;
import org.apache.flume.channel.ChannelProcessor;

import static org.apache.flume.event.EventBuilder.withBody;

class FlumeTailerSourceListener implements TailerListener {

    private final ChannelProcessor processor;
    private Tailer tailer;

    public FlumeTailerSourceListener(ChannelProcessor processor) {
        this.processor = processor;
    }

    @Override
    public void init(Tailer tailer) {
        this.tailer = tailer;
    }

    @Override
    public void fileNotFound() {
        throw new FlumeException("File "+tailer.getFile().getName()+" does not exist");
    }

    @Override
    public void fileRotated() {}

    @Override
    public void handle(String line) {
        processor.processEvent(withBody(line.getBytes()));
    }

    @Override
    public void handle(Exception ex) {
        throw new FlumeException(ex);
    }
}
