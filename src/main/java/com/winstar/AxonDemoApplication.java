package com.winstar;

import com.winstar.coreapi.CreateAccountCommand;
import com.winstar.coreapi.WithDepositMoneyCommand;
import com.winstar.coreapi.WithDrawMoneyCommand;
import org.axonframework.commandhandling.AsynchronousCommandBus;
import org.axonframework.commandhandling.CommandBus;
import org.axonframework.commandhandling.CommandCallback;
import org.axonframework.commandhandling.CommandMessage;
import org.axonframework.eventsourcing.eventstore.EventStorageEngine;
import org.axonframework.eventsourcing.eventstore.inmemory.InMemoryEventStorageEngine;
import org.axonframework.spring.config.EnableAxonAutoConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;

import static org.axonframework.commandhandling.GenericCommandMessage.asCommandMessage;

@EnableAxonAutoConfiguration
@SpringBootApplication
public class AxonDemoApplication {

    public static void main(String[] args) {
        ConfigurableApplicationContext config = SpringApplication.run(AxonDemoApplication.class, args);
        CommandBus commandBus = config.getBean(CommandBus.class);
        commandBus.dispatch(asCommandMessage(new CreateAccountCommand("4321", 500)), new CommandCallback<Object, Object>() {
            @Override
            public void onSuccess(CommandMessage<?> commandMessage, Object o) {
                commandBus.dispatch(asCommandMessage(new WithDepositMoneyCommand("4321", 2)));
                commandBus.dispatch(asCommandMessage(new WithDrawMoneyCommand("4321", 250)));
                commandBus.dispatch(asCommandMessage(new WithDrawMoneyCommand("4321", 251)));
            }

            @Override
            public void onFailure(CommandMessage<?> commandMessage, Throwable throwable) {
                throwable.fillInStackTrace();
            }
        });
    }

    @Bean
    public EventStorageEngine eventStorageEngine() {
        return new InMemoryEventStorageEngine();
    }

    @Bean
    public CommandBus commandBus() {
        return new AsynchronousCommandBus();
    }
}
