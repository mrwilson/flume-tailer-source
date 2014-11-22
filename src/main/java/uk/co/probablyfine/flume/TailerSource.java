package uk.co.probablyfine.flume;

import org.apache.commons.io.input.Tailer;
import org.apache.flume.Context;
import org.apache.flume.EventDrivenSource;
import org.apache.flume.conf.Configurable;
import org.apache.flume.source.AbstractSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;

public class TailerSource extends AbstractSource implements EventDrivenSource, Configurable {

    private static final Logger log = LoggerFactory.getLogger(TailerSource.class);

    private String filePath;
    private Tailer tailer;

    public void configure(Context context) {
        this.filePath = context.getString("filePath");
    }

    @Override
    public synchronized void start() {
        log.info("Starting {} with {} ...", this);

        this.tailer = new Tailer(new File(filePath), new FlumeTailerSourceListener(this.getChannelProcessor()));
        this.tailer.run();
        super.start();
    }

    @Override
    public synchronized void stop() {
        log.info("Stopping ...");
        this.tailer.stop();
        super.stop();
    }
}


