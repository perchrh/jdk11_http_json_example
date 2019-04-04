package org.digitalsprouts.example.http_client;

import com.fasterxml.jackson.core.JsonEncoding;
import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.ObjectWriter;

import java.io.IOException;
import java.io.InputStream;
import java.io.PipedInputStream;
import java.io.PipedOutputStream;
import java.util.function.Supplier;

/**
 * @Author Per Christian Henden
 * Free for all use
 */
public class JsonBodyPublisher implements Supplier<InputStream> {

    private final static ObjectWriter OBJECT_WRITER = Application.createObjectMapper().writer();
    private final static JsonFactory JSON_FACTORY = OBJECT_WRITER.getFactory();

    private final Object requestBody;

    public JsonBodyPublisher(Object requestBody) {
        this.requestBody = requestBody;
    }

    @Override
    public InputStream get() {
        PipedInputStream in = new PipedInputStream();
        new Thread(
                () -> {
                    try {
                        PipedOutputStream out = new PipedOutputStream(in);
                        JsonGenerator jGenerator = JSON_FACTORY.createGenerator(out, JsonEncoding.UTF8);
                        // put data on output stream
                        OBJECT_WRITER.writeValue(jGenerator, requestBody);
                    } catch (IOException e) {
                        throw new RuntimeException("Error writing object as json");
                    }
                }
        ).start();
        // return input stream for processing
        return in;
    }
}
