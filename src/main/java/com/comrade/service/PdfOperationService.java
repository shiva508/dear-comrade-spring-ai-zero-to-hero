package com.comrade.service;

import org.springframework.ai.reader.pdf.PagePdfDocumentReader;
import org.springframework.ai.reader.pdf.config.PdfDocumentReaderConfig;
import org.springframework.ai.transformer.splitter.TokenTextSplitter;
import org.springframework.ai.vectorstore.VectorStore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

@Service
public class PdfOperationService {

    @Autowired
    PdfDocumentReaderConfig pdfDocumentReaderConfig;
    @Autowired
    JdbcTemplate jdbcTemplate;
    @Autowired
    VectorStore vectorStore;
    @Value("${pdf.path}")
    private Resource pdfPath;

    public void pdfOperationInit(){
        var pdfReader = new PagePdfDocumentReader(pdfPath, pdfDocumentReaderConfig);
        var textSplitter = new TokenTextSplitter();
        var docs = textSplitter.apply(pdfReader.get());
        vectorStore.accept(docs);
    }
}
