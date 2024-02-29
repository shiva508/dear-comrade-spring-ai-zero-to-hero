package com.comrade.config.pdf;

import org.springframework.ai.document.MetadataMode;
import org.springframework.ai.embedding.EmbeddingClient;
import org.springframework.ai.postgresml.PostgresMlEmbeddingClient;
import org.springframework.ai.postgresml.PostgresMlEmbeddingOptions;
import org.springframework.ai.reader.ExtractedTextFormatter;
import org.springframework.ai.reader.pdf.config.PdfDocumentReaderConfig;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class PdfReadWriteConfiguration {

    @Bean
    public PdfDocumentReaderConfig pdfDocumentReaderConfig(){
        return PdfDocumentReaderConfig
                .builder()
                .withPageExtractedTextFormatter(new ExtractedTextFormatter.Builder()
                        .withNumberOfBottomTextLinesToDelete(3)
                        .build()
                )
                .build();
    }

//    @Bean
//    public VectorStore vectorStore(JdbcTemplate jdbcTemplate, EmbeddingClient embeddingClient) {
//        return new PgVectorStore(jdbcTemplate, embeddingClient);
//    }
//@Bean
//public EmbeddingClient embeddingClient(JdbcTemplate jdbcTemplate) {
//    return new PostgresMlEmbeddingClient(jdbcTemplate,
//            PostgresMlEmbeddingOptions.builder()
//                    .withVectorType(PostgresMlEmbeddingClient.VectorType.PG_ARRAY)
//                    .withTransformer("distilbert-base-uncased")
//                    .withMetadataMode(MetadataMode.EMBED)
//                    .withKwargs(Map.of("device", "gpu"))
//            .build());
//}

}
