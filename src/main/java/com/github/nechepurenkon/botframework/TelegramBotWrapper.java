package com.github.nechepurenkon.botframework;

import com.github.nechepurenkon.botframework.executors.CommandDispatcher;
import com.github.nechepurenkon.botframework.configuration.TelegramBotFrameworkConfiguration;
import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.UpdatesListener;
import com.pengrad.telegrambot.model.Update;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import kotlin.Pair;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class TelegramBotWrapper {

    @Autowired
    @Qualifier("botConfiguration")
    protected TelegramBotFrameworkConfiguration botConfiguration;

    @Autowired
    @Qualifier("apiWrapper")
    protected TelegramBot apiWrapper;

    @Autowired
    @Qualifier("commandDispatcher")
    protected CommandDispatcher commandDispatcher;

    protected final Thread workingThread;

    public TelegramBotWrapper() {
        workingThread = new Thread(this::mainLoop);
    }

    public void initialize() {
        log.info("Initializing {} bot", botConfiguration.getName());
        apiWrapper.setUpdatesListener(this::updatesHandler);
    }

    protected int updatesHandler(List<Update> updates) {
        log.trace("{} received updates", botConfiguration.getName());
        updates.forEach(update -> CompletableFuture.runAsync(() -> handleUpdate(update)));
        return UpdatesListener.CONFIRMED_UPDATES_ALL;
    }

    private void handleUpdate(Update update) {
        Pair<String, String> parsedMessageText = getCommand(update.message().text());
        log.trace("{} started handling update {}", botConfiguration.getName(), update);
        log.info("Received command {} from {}", parsedMessageText.getFirst(), update.message().chat().username());
        apiWrapper.execute(commandDispatcher.getExecutor(parsedMessageText.getFirst())
                                            .execute(update, parsedMessageText.getSecond()));
    }

    private void mainLoop() {
        try {
            while (true) {
                pollEvents();
            }
        } catch (InterruptedException e) {
            log.info("Thread {} handling {} was ended by interruption", Thread.currentThread().getName(),
                botConfiguration.getName());
        } catch (Exception e) {
            log.error("Thread {} handling {} was ended by exception:", Thread.currentThread().getName(),
                botConfiguration.getName());
            e.printStackTrace();
        } finally {
            log.info("Thread {} handling {} was ended", Thread.currentThread().getName(),
                botConfiguration.getName());
        }
    }

    private void pollEvents() throws InterruptedException {

    }

    private Pair<String, String> getCommand(String text) {
        Pattern p = Pattern.compile("/(\\w+)( (.*))*");
        Matcher m = p.matcher(text);
        return m.matches() ? new Pair<>(
            m.group(1),
            m.group(3) != null ? m.group(3) : ""
        ) : new Pair<>(
            "empty",
            text
        );
    }

    @SneakyThrows
    public void start() {
        log.info("Starting working thread for {}", botConfiguration.getName());
        workingThread.start();
    }

    public void stop() {
        log.info("Stopping working thread for {}", botConfiguration.getName());
        workingThread.interrupt();
    }
}
