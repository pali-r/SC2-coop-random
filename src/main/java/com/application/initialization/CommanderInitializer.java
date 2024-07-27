package com.application.initialization;

import com.application.model.CommanderEntity;
import com.application.repository.CommanderRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CommanderInitializer {
    private static CommanderRepo commanderRepo;

    @Autowired
    public CommanderInitializer(CommanderRepo commanderRepo) {
        this.commanderRepo = commanderRepo;
    }

    public void initializeCommanders() {
        CommanderEntity commanderEntity = new CommanderEntity();
        commanderEntity.setName("raynor");
        commanderRepo.save(commanderEntity);

        commanderEntity = new CommanderEntity();
        commanderEntity.setName("kerrigan");
        commanderRepo.save(commanderEntity);

        commanderEntity = new CommanderEntity();
        commanderEntity.setName("artanis");
        commanderRepo.save(commanderEntity);
    }
}
