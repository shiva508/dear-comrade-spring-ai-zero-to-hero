package com.comrade.utils;

import dev.langchain4j.data.document.DocumentSource;
import dev.langchain4j.data.document.Metadata;

import java.io.IOException;
import java.io.InputStream;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Map;

public class DcAiUtils {
    public static Path toPath(String fileName) {
        try {
            URL fileUrl = ClassLoader.getSystemResource(fileName);
            return Paths.get(fileUrl.toURI());
        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        }
    }

    public static DocumentSource getDocumentSource(InputStream inputStream, Map<String,String> props) {
        return new DocumentSource() {
            @Override
            public InputStream inputStream() throws IOException {
                return inputStream;
            }
            @Override
            public Metadata metadata() {
                return new Metadata(props);
            }
        };
    }
}
