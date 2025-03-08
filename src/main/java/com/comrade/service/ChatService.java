package com.comrade.service;

import com.comrade.model.ChatRequestModel;
import lombok.RequiredArgsConstructor;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.client.advisor.QuestionAnswerAdvisor;
import org.springframework.ai.chat.messages.UserMessage;
import org.springframework.ai.chat.model.ChatResponse;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.ai.chat.prompt.PromptTemplate;
import org.springframework.ai.document.Document;
import org.springframework.ai.ollama.OllamaChatModel;
import org.springframework.ai.vectorstore.SearchRequest;
import org.springframework.ai.vectorstore.VectorStore;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

import java.util.List;
import java.util.Map;
import java.util.Properties;

@Service
@RequiredArgsConstructor
public class ChatService {

    private final OllamaChatModel ollamaChatModel;

    private final VectorStore vectorStore;

    private final ChatClient chatClient;

    public Map<String,String> generate(ChatRequestModel chatRequestModel) {
        return Map.of("generation", this.ollamaChatModel.call(chatRequestModel.getChatMessage()));
    }
    public Flux<ChatResponse> generateStream( ChatRequestModel chatRequestModel) {
        Prompt prompt = new Prompt(new UserMessage(chatRequestModel.getChatMessage()));
        return ollamaChatModel.stream(prompt);
    }

    public List<Document> vectorStoreChat(ChatRequestModel chatRequestModel){
        SearchRequest searchRequest = SearchRequest.builder().query(chatRequestModel.getChatMessage()).build();
        return vectorStore.similaritySearch(searchRequest);
    }

    public String vectorStorePromptChat(ChatRequestModel chatRequestModel){

        PromptTemplate promptTemplate = new PromptTemplate("""
                {query}
                """,Map.of("query", chatRequestModel.getChatMessage()));
        Prompt prompt = new  Prompt(promptTemplate.createMessage());
        return chatClient.prompt(prompt).advisors(new QuestionAnswerAdvisor(vectorStore)).call().content();
    }


}
