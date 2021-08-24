package com.github.nechepurenkon.botframework.configuration;

import com.github.nechepurenkon.botframework.TelegramBotWrapper;
import com.github.nechepurenkon.botframework.executors.CommandDispatcher;
import com.github.nechepurenkon.botframework.executors.CommandDispatcherImpl;
import com.pengrad.telegrambot.TelegramBot;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfiguration extends TelegramBotFrameworkConfiguration {

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
