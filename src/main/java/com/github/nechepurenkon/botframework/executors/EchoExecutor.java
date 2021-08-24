package com.github.nechepurenkon.botframework.executors;

import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.request.AbstractSendRequest;
import com.pengrad.telegrambot.request.SendMessage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component("echoExecutor")
public class EchoExecutor<T extends AbstractSendRequest<T>> implements CommandExecutor<T> {

    @Override
    public AbstractSendRequest<T> execute(Update update, String text) {
        log.info("Echoing {} to {}", text, update.message().chat().username());
        return cast(
            new SendMessage(update.message().chat().id(), text)
        );
    }
}
