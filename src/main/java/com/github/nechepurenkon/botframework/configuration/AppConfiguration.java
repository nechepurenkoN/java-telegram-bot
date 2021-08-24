package com.github.nechepurenkon.botframework.configuration;

import com.github.nechepurenkon.botframework.TelegramBotWrapper;
import com.github.nechepurenkon.botframework.executors.CommandDispatcher;
import com.github.nechepurenkon.botframework.executors.CommandDispatcherImpl;
import com.github.nechepurenkon.botframework.executors.CommandExecutor;
import com.github.nechepurenkon.botframework.executors.EchoExecutor;
import com.github.nechepurenkon.botframework.executors.EmptyCommandExecutor;
import com.pengrad.telegrambot.TelegramBot;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfiguration extends TelegramBotFrameworkConfiguration {

    @Bean
    public CommandExecutor<?> echoExecutor() {
        return new EchoExecutor<>();
    }

    @Bean
    public CommandExecutor<?> emptyExecutor() {
        return new EmptyCommandExecutor<>();
    }

    @Bean
    public CommandDispatcher commandDispatcher() {
        return new CommandDispatcherImpl();
    }

    @Bean
    public TelegramBotFrameworkConfiguration botConfiguration() {
        return TelegramBotFrameworkConfiguration.get();
    }

    @Bean
    public TelegramBot apiWrapper() {
        return new TelegramBot(System.getenv(keyEnvironmentVariableName));
    }

    @Bean
    public TelegramBotWrapper telegramBotWrapper() {
        return new TelegramBotWrapper();
    }
}
