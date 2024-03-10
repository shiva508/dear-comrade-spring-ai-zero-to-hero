package com.comrade.config.lang4j;

import dev.langchain4j.data.segment.TextSegment;
import dev.langchain4j.model.embedding.AllMiniLmL6V2EmbeddingModel;
import dev.langchain4j.model.embedding.EmbeddingModel;
import dev.langchain4j.store.embedding.EmbeddingStore;
import dev.langchain4j.store.embedding.neo4j.Neo4jEmbeddingStore;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DcLang4jConfiguration {

    @Value("${neo4j.uri}")
    public String neo4jUri;
    @Value("${neo4j.username}")
    public String neo4jUsername;
    @Value("${neo4j.password}")
    public String neo4jPassword;
    @Value("${neo4j.dimension}")
    public Integer neo4jDimension;

    @Bean
    public EmbeddingStore<TextSegment> embeddingStore (){
        return Neo4jEmbeddingStore.builder()
                .withBasicAuth(neo4jUri, neo4jUsername, neo4jPassword)
                .dimension(neo4jDimension)
                .build();
    }

    @Bean
    public EmbeddingModel allMiniLmL6V2EmbeddingModel (){
        return new AllMiniLmL6V2EmbeddingModel();
    }

//    @Bean
//    public VectorStore vectorStore(Driver driver, EmbeddingClient embeddingClient) {
//
//        return new Neo4jVectorStore(driver, embeddingClient,
//                Neo4jVectorStore.Neo4jVectorStoreConfig.defaultConfig());
//    }
//
//    @Bean
//    public Driver driver() {
//        return GraphDatabase.driver(neo4jUri, AuthTokens.basic(neo4jUsername, neo4jPassword));
//    }
//
//    @Bean
//    public EmbeddingClient embeddingClient() {
//        return new OpenAiEmbeddingClient(new OpenAiApi(System.getenv("OPENAI_API_KEY")));
//    }

}
