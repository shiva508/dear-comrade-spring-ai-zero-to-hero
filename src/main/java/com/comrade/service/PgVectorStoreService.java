package com.comrade.service;

import lombok.RequiredArgsConstructor;
import org.springframework.ai.reader.pdf.PagePdfDocumentReader;
import org.springframework.ai.transformer.splitter.TokenTextSplitter;
import org.springframework.ai.vectorstore.VectorStore;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PgVectorStoreService {

    private final VectorStore vectorStore;

    @Value("${pdf.path}")
    Resource fileResource;

    public String loadData(){
        var pdfReader = new PagePdfDocumentReader(fileResource);
        var textSplitter = new TokenTextSplitter();
        var docs = textSplitter.apply(pdfReader.get());
        vectorStore.accept(docs);
        return "COMPLETED";
    }

}
