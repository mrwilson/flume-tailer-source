package uk.co.probablyfine.flume;

import org.apache.flume.Event;
import org.apache.flume.FlumeException;
import org.apache.flume.channel.ChannelProcessor;
import org.junit.Test;

import java.io.IOException;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

public class FlumeTailerSourceListenerTest {

    @Test
    public void shouldSubmitEventForEachLineInFile() {
        final String input = "foo";

        ChannelProcessor stubProcessor = new ChannelProcessor(null) {
            @Override
            public void processEvent(Event event) {
                assertThat(new String(event.getBody()), is(input));
            }
        };

        FlumeTailerSourceListener listener = new FlumeTailerSourceListener(stubProcessor);

        listener.handle(input);
    }

    @Test(expected=FlumeException.class)
    public void shouldRethrowExceptionsAsFlumeExceptions() {
        FlumeTailerSourceListener listener = new FlumeTailerSourceListener(null);

        listener.handle(new IOException("Some exception"));
    }

}