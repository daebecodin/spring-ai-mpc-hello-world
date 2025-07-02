package com.daebecodin.springaimpchelloworld;

import org.springframework.ai.support.ToolCallbacks;
import org.springframework.ai.tool.ToolCallback;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.List;

@SpringBootApplication
public class SpringAiMpcHelloWorldApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringAiMpcHelloWorldApplication.class, args);
    }


    /**
     * Prepares a list of methods for the AI model to execute
     * @param computerService service class with tool implementation
     * @return a list of tools for the model to call when needed
     */
    @Bean
    public List<ToolCallback> pcTools(ComputerService computerService) {
        return List.of(ToolCallbacks.from(computerService));
    }
}
