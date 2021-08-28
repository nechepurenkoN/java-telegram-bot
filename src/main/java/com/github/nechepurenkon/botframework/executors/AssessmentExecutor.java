package com.github.nechepurenkon.botframework.executors;

import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.request.AbstractSendRequest;
import com.pengrad.telegrambot.request.SendMessage;

public class AssessmentExecutor<T extends AbstractSendRequest<T>> implements CommandExecutor<T>{
    @Override
    public AbstractSendRequest<T> execute(Update update, String text) {
        return cast(
            new SendMessage(update.message().chat().id(), "assessment")
        );
    }
}
