package com.github.nechepurenkon.botframework.configuration;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@PropertySource("classpath:bot.properties")
@Getter
public class TelegramBotFrameworkConfiguration {

    @Value("${name:TelegramBot}")
    protected String name;

    @Value("${username:unknown_username_bot}")
    protected String username;

    @Value("${env_key_name:null}")
    protected String keyEnvironmentVariableName;

    public static TelegramBotFrameworkConfiguration get() {
        return new AnnotationConfigApplicationContext(TelegramBotFrameworkConfiguration.class)
            .getBean(TelegramBotFrameworkConfiguration.class);
    }

    protected TelegramBotFrameworkConfiguration() {}

}
