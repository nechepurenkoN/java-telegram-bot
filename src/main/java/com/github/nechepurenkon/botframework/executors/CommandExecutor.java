package com.github.nechepurenkon.botframework.executors;

import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.request.AbstractSendRequest;

public interface CommandExecutor<T extends AbstractSendRequest<T>> {
    AbstractSendRequest<T> execute(Update update, String text);

    default <U,R> R cast(U obj) {
        return (R) obj;
    }
}
