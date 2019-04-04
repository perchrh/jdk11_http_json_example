package org.digitalsprouts.example.http_client;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UncheckedIOException;
import java.io.Writer;

/**
 * Extension of Jackson ObjectMapper with methods that catch IOException and
 * rethrow as UncheckedIOException
 *
 * @author mike, github.com/mblub
 */
public class UncheckedObjectMapper extends ObjectMapper {
    private static final long serialVersionUID = 1L;

    public UncheckedObjectMapper() {
        super();
    }

    @Override
    public <T> T readValue(File src, Class<T> valueType) {
        try {
            return super.readValue(src, valueType);
        } catch (IOException ioe) {
            throw new UncheckedIOException(ioe);
        }
    }

    @Override
    public void writeValue(File resultFile, Object value) {
        try {
            super.writeValue(resultFile, value);
        } catch (IOException ioe) {
            throw new UncheckedIOException(ioe);
        }
    }

    @Override
    public void writeValue(Writer writer, Object value) {
        try {
            super.writeValue(writer, value);
        } catch (IOException ioe) {
            throw new UncheckedIOException(ioe);
        }
    }

    @Override
    public void writeValue(OutputStream out, Object value) {
        try {
            super.writeValue(out, value);
        } catch (IOException ioe) {
            throw new UncheckedIOException(ioe);
        }
    }

    @Override
    public <T> T readValue(InputStream src, Class<T> valueType) {
        try {
            return super.readValue(src, valueType);
        } catch (IOException ioe) {
            throw new UncheckedIOException(ioe);
        }
    }

    @Override
    public String writeValueAsString(Object value) {
        try {
            return super.writeValueAsString(value);
        } catch (JsonProcessingException jpe) {
            throw new UncheckedIOException(jpe);
        }
    }
}