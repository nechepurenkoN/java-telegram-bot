package com.github.nechepurenkon.botframework.factory;

import com.github.nechepurenkon.botframework.TelegramBotWrapper;
import com.github.nechepurenkon.botframework.configuration.AppConfiguration;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class TelegramBotFactory {
    public static TelegramBotWrapper create() {
        TelegramBotWrapper wrapper = new AnnotationConfigApplicationContext(AppConfiguration.class)
            .getBean(TelegramBotWrapper.class);
        wrapper.initialize();
        return wrapper;
    }
}
