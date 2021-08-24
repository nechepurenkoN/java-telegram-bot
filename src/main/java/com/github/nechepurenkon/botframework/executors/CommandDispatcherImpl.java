package com.github.nechepurenkon.botframework.executors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component("commandDispatcher")
public class CommandDispatcherImpl implements CommandDispatcher {

    @Autowired
    protected CommandExecutor<?> echoExecutor;

    @Autowired
    protected CommandExecutor<?> emptyExecutor;

    @Override
    public CommandExecutor<?> getExecutor(String commandName) {
        if (commandName.equals("echo"))
            return echoExecutor;
        return emptyExecutor;
    }
}
