package com.github.nechepurenkon.botframework.executors;

import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.request.AbstractSendRequest;
import com.pengrad.telegrambot.request.SendMessage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component("emptyExecutor")
public class EmptyCommandExecutor<T extends AbstractSendRequest<T>> implements CommandExecutor<T> {
    @Override
    public AbstractSendRequest<T> execute(Update update, String text) {
        return cast(
            false ? transferHandle() : noCommandSuppliedMessage(update, text)
        );
    }

    private SendMessage noCommandSuppliedMessage(Update update, String text) {
        log.info("Returning no command supplied message to {}", update.message().chat().username());
        return new SendMessage(update.message().chat().id(), "No command supplied, do not know how to react :(");
    }

    private<U> U transferHandle() {
        return null;
    }
}
