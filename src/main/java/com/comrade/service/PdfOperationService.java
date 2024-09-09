package com.comrade.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.ai.document.Document;
import org.springframework.ai.reader.pdf.PagePdfDocumentReader;
import org.springframework.ai.reader.pdf.config.PdfDocumentReaderConfig;
import org.springframework.ai.reader.tika.TikaDocumentReader;
import org.springframework.ai.transformer.splitter.TokenTextSplitter;
import org.springframework.ai.vectorstore.VectorStore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
public class PdfOperationService {

    @Autowired
    PdfDocumentReaderConfig pdfDocumentReaderConfig;

    @Autowired
    JdbcTemplate jdbcTemplate;

    @Autowired
    VectorStore vectorStore;

    @Value("${pdf.path}")
    private Resource pdfPath;

    @Value("${resume.path}")
    private Resource resumePath;

    public void pdfOperationInit(){
        /*var pdfReader = new PagePdfDocumentReader(pdfPath, pdfDocumentReaderConfig);
        var textSplitter = new TokenTextSplitter();
        var docs = textSplitter.apply(pdfReader.get());*/

        TikaDocumentReader reader = new TikaDocumentReader(pdfPath);
        List<Document> documents = new ArrayList<>(reader.get());
        log.info("In :{}",documents);
        vectorStore.add(documents);
        log.info("COMPLETED");
        //vectorStore.getName();
        //vectorStore.accept(docs);
    }

    public void resumeOperationInit(){
        TikaDocumentReader reader = new TikaDocumentReader(resumePath);
        List<Document> documents = new ArrayList<>(reader.get());
        for (Document document : documents) {
            log.info("PdfOperationService::resumeOperationInit:: document :{}",document);
        }
        vectorStore.add(documents);
        log.info("PdfOperationService::resumeOperationInit::COMPLETED");
    }
}
