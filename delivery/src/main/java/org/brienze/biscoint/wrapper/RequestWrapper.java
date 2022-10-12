package org.brienze.biscoint.wrapper;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

import javax.servlet.ReadListener;
import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;

import com.fasterxml.jackson.databind.ObjectMapper;

public class RequestWrapper extends HttpServletRequestWrapper {

    private final String body;

    public RequestWrapper(HttpServletRequest request) throws IOException {
        super(request);

        StringBuilder sb = new StringBuilder();
        String line;
        BufferedReader reader = request.getReader();
        while ((line = reader.readLine()) != null) {
            sb.append(line);
        }
        String body = sb.toString();

        if (body.isBlank()) {
            this.body = "{}";
        } else {
            ObjectMapper mapper = new ObjectMapper();
            this.body = mapper.writeValueAsString(mapper.readTree(body.getBytes(StandardCharsets.UTF_8)));
        }
    }

    public String getBody() {
        return body;
    }

    @Override
    public ServletInputStream getInputStream() throws IOException {
        final ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(body.getBytes());

        return new ServletInputStream() {
            public int read() throws IOException {
                return byteArrayInputStream.read();
            }

            @Override
            public boolean isFinished() {
                return false;
            }

            @Override
            public boolean isReady() {
                return false;
            }

            @Override
            public void setReadListener(ReadListener listener) {
            }
        };
    }

}
