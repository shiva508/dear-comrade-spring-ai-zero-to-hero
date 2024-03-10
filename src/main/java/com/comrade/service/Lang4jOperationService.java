package com.comrade.service;

import com.comrade.utils.DcAiUtils;
import dev.langchain4j.data.document.Document;
import dev.langchain4j.data.document.DocumentLoader;
import dev.langchain4j.data.document.DocumentSource;
import dev.langchain4j.data.document.parser.apache.pdfbox.ApachePdfBoxDocumentParser;
import dev.langchain4j.data.embedding.Embedding;
import dev.langchain4j.data.segment.TextSegment;
import dev.langchain4j.model.embedding.EmbeddingModel;
import dev.langchain4j.store.embedding.EmbeddingMatch;
import dev.langchain4j.store.embedding.EmbeddingStore;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@Service
public class Lang4jOperationService {
    private final EmbeddingStore<TextSegment> embeddingStore;

    private final EmbeddingModel allMiniLmL6V2EmbeddingModel;
    @Value("${pdf.path}")
    private Resource pdfPath;

    public Lang4jOperationService(EmbeddingStore<TextSegment> embeddingStore,EmbeddingModel allMiniLmL6V2EmbeddingModel) {
        this.embeddingStore = embeddingStore;
        this.allMiniLmL6V2EmbeddingModel = allMiniLmL6V2EmbeddingModel;
    }


    public void lang4jOperationInit(){
        List<String> inputFeed = List.of("Tell me a joke","I like football.","The weather is good today.","A functional interface is just like any other interface,but it will have exactly one abstract method");
        inputFeed.parallelStream().forEach(feed ->{
            TextSegment segment = TextSegment.from(feed);
            Embedding embedding = allMiniLmL6V2EmbeddingModel.embed(segment).content();
            embeddingStore.add(embedding, segment);
        });
    }

    public Map<String,String> lang4jOperations(String question){
        Embedding queryEmbedding = allMiniLmL6V2EmbeddingModel.embed(question).content();
        List<EmbeddingMatch<TextSegment>> relevant = embeddingStore.findRelevant(queryEmbedding, 1);
        EmbeddingMatch<TextSegment> embeddingMatch = relevant.get(0);
        return Map.of("score",embeddingMatch.score().toString(),"answer",embeddingMatch.embedded().text());
    }

    public void documentProcessing(){
        Map<String,String> props = Map.of("owner","shiva","description",pdfPath.getDescription(),"file", Objects.requireNonNull(pdfPath.getFilename()));
        DocumentSource documentSource = null;
        try {
            documentSource = DcAiUtils.getDocumentSource(pdfPath.getInputStream(),props);
            Document document = DocumentLoader.load(documentSource, new ApachePdfBoxDocumentParser());
            System.out.println(document);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
