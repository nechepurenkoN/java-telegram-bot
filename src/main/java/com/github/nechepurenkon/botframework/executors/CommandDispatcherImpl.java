package com.github.nechepurenkon.botframework.executors;

import com.github.nechepurenkon.botframework.configuration.ExecutorConfiguration;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import lombok.SneakyThrows;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Component;

@Component("commandDispatcher")
public class CommandDispatcherImpl implements CommandDispatcher {

    private final Map<String, CommandExecutor<?>> commandToExecutor;
    private final AnnotationConfigApplicationContext ctx;
    private final Properties commandToExecutorName;

    public CommandDispatcherImpl() {
        commandToExecutorName = loadProperties();
        commandToExecutor = new HashMap<>();
        ctx = new AnnotationConfigApplicationContext(ExecutorConfiguration.class);
        ctx.getBeansOfType(CommandExecutor.class).forEach((beanName, bean) -> {
            commandToExecutorName.forEach((command, executorPrefix) -> {
                if (beanName.equals(String.format("%sExecutor", executorPrefix))) {
                    commandToExecutor.put((String) command, bean);
                }
            });
        });
    }

    @SneakyThrows
    private Properties loadProperties() {
        Properties properties = new Properties();
        properties.load(this.getClass().getResourceAsStream("/executors.properties"));
        return properties;
    }

    @Override
    public CommandExecutor<?> getExecutor(String commandName) {
        return commandToExecutor.getOrDefault(commandName, (CommandExecutor<?>) ctx.getBean("emptyExecutor"));
    }
}
