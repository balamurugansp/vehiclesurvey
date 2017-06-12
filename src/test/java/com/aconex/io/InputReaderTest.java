package com.aconex.io;

import org.junit.Test;

import java.io.IOException;
import java.io.Reader;
import java.io.StringReader;
import java.util.List;

import static org.junit.Assert.assertTrue;

public class InputReaderTest {

    @Test
    public void shouldGetListOfInputLinesForValidInput() throws Exception {
        String input = "A268981\nB269123\nA604957";
        StringReader reader = new StringReader(input);

        List<String> output = new InputReader(reader).getAllInputLines();
        assertTrue(output.contains("A268981"));
        assertTrue(output.contains("B269123"));
        assertTrue(output.contains("A604957"));
    }

    @Test
    public void shouldGetEmptyListIfInputContainsInvalidEntry() throws Exception {
        String input = "A268981\nC269123\nA604957";
        StringReader reader = new StringReader(input);

        List<String> output = new InputReader(reader).getAllInputLines();
        assertTrue(output.isEmpty());
    }

    @Test
    public void shouldGetEmptyListIfFileContentIsInvalid() throws Exception {
        FakeReader reader = new FakeReader() ;

        List<String> output = new InputReader(reader).getAllInputLines();
        assertTrue(output.isEmpty());
    }

    @Test
    public void shouldCapitalizeDirectionAlphabet() throws Exception {
        String input = "a268981\nb269123\na604957";
        StringReader reader = new StringReader(input);

        List<String> output = new InputReader(reader).getAllInputLines();
        assertTrue(output.contains("A268981"));
        assertTrue(output.contains("B269123"));
        assertTrue(output.contains("A604957"));
    }

    private class FakeReader extends Reader {

        @Override
        public int read(char[] cbuf, int off, int len) throws IOException {
            throw new IOException();
        }

        @Override
        public void close() throws IOException {

        }

    }
}
