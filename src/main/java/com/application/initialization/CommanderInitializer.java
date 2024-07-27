package com.application.initialization;

import com.application.model.CommanderEntity;
import com.application.repository.CommanderRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

@Component
public class CommanderInitializer {
    private final List<String> COMMANDER_NAMES = Arrays.asList("Raynor", "Kerrigan", "Artanis", "Swann", "Zagara", "Vorazun", "Karax", "Abathur", "Alarak", "Nova", "Stukov", "Fenix", "Dehaka", "Han and Horner", "Tychus", "Zeratul");

    private static CommanderRepo commanderRepo;

    @Autowired
    public CommanderInitializer(CommanderRepo commanderRepo) {
        this.commanderRepo = commanderRepo;

        initializeCommanders();
    }

    private void initializeCommanders() {
        COMMANDER_NAMES.forEach(
                commanderName -> {
                    CommanderEntity commanderEntity;
                    for (int i = 0; i < 4; i++) {
                        commanderEntity = new CommanderEntity();
                        commanderEntity.setName(commanderName);
                        commanderEntity.setPrestige("P" + i);
                        commanderRepo.save(commanderEntity);
                    }
                }
        );
    }
}
