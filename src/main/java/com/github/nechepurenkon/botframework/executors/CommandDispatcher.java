package com.github.nechepurenkon.botframework.executors;

public interface CommandDispatcher {
    CommandExecutor<?> getExecutor(String commandName);
}
