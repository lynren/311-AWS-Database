package com.meme.db.getmeme;

import java.io.IOException;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;


import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import com.amazonaws.services.lambda.runtime.Context;

/**
 * A simple test harness for locally invoking your Lambda function handler.
 */
public class getmemeTest {

    private static final String SAMPLE_INPUT_STRING = "{\"foo\": \"bar\"}";
    private static final String EXPECTED_OUTPUT_STRING = "{\"FOO\": \"BAR\"}";

    @Test
    public void testLambdaFunctionHandler() throws IOException {
        getmeme handler = new getmeme();

        InputStream input = new ByteArrayInputStream(SAMPLE_INPUT_STRING.getBytes());;
        OutputStream output = new ByteArrayOutputStream();

        handler.handleRequest(input, output, null);

        // TODO: validate output here if needed.
        String sampleOutputString = output.toString();
        System.out.println(sampleOutputString);
        Assert.assertEquals(EXPECTED_OUTPUT_STRING, sampleOutputString);
    }
}
