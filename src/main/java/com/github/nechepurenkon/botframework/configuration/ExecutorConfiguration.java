package com.github.nechepurenkon.botframework.configuration;

import com.github.nechepurenkon.botframework.executors.AssessmentExecutor;
import com.github.nechepurenkon.botframework.executors.CommandExecutor;
import com.github.nechepurenkon.botframework.executors.EchoExecutor;
import com.github.nechepurenkon.botframework.executors.EmptyCommandExecutor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ExecutorConfiguration {
    @Bean
    public CommandExecutor<?> echoExecutor() {
        return new EchoExecutor<>();
    }

    @Bean
    public CommandExecutor<?> emptyExecutor() {
        return new EmptyCommandExecutor<>();
    }

    @Bean
    public CommandExecutor<?> assessmentExecutor() {
        return new AssessmentExecutor<>();
    }
}
