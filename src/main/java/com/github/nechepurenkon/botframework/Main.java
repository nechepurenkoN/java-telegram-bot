package com.github.nechepurenkon.botframework;

import com.github.nechepurenkon.botframework.factory.TelegramBotFactory;

public class Main {
    public static void main(String[] args) {
        var telegramBot = TelegramBotFactory.create();
        telegramBot.start();
    }
}
