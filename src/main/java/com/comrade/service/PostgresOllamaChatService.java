package com.comrade.service;

import com.comrade.model.QuestionModel;
import lombok.AllArgsConstructor;
import org.springframework.ai.chat.messages.UserMessage;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.ai.chat.prompt.SystemPromptTemplate;
import org.springframework.ai.document.Document;
import org.springframework.ai.ollama.OllamaChatModel;
import org.springframework.ai.vectorstore.VectorStore;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class PostgresOllamaChatService {

    private final OllamaChatModel ollamaChatModel;
    private final VectorStore vectorStore;

    private final String template = """
                        
            You're assisting with questions about services offered by Carina.
            Carina is a two-sided healthcare marketplace focusing on home care aides (caregivers)
            and their Medicaid in-home care clients (adults and children with developmental disabilities and low income elderly population).
            Carina's mission is to build online tools to bring good jobs to care workers, so care workers can provide the
            best possible care for those who need it.
                    
            Use the information from the DOCUMENTS section to provide accurate answers but act as if you knew this information innately.
            If unsure, simply state that you don't know.
                    
            DOCUMENTS:
            {documents}
                        
            """;
    public String askQuestion(QuestionModel questionModel){
        var roiDocuments = vectorStore.similaritySearch(questionModel.getQuestion());
        var documents =roiDocuments.stream().map(Document::getContent).collect(Collectors.joining(System.lineSeparator()));
        var systemMessage = new SystemPromptTemplate(this.template).createMessage(Map.of("documents", documents));
        var userMessage = new UserMessage(questionModel.getQuestion());
        var prompt = new Prompt(List.of(systemMessage, userMessage));
        var aiResponse = ollamaChatModel.call(prompt);
        return aiResponse.getResult().getOutput().getContent();
    }
}
