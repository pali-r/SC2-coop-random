package com.application.initialization;

import com.application.model.CommanderEntity;
import com.application.repository.CommanderRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

@Component
public class CommanderInitializer {
    private static CommanderRepo commanderRepo;
    private final List<String> COMMANDER_NAMES = Arrays.asList("Raynor", "Kerrigan", "Artanis", "Swann", "Zagara", "Vorazun", "Karax", "Abathur", "Alarak", "Nova", "Stukov", "Fenix", "Dehaka", "Han and Horner", "Tychus", "Zeratul");

    @Autowired
    public CommanderInitializer(CommanderRepo commanderRepo) {
        this.commanderRepo = commanderRepo;
    }

    public void initializeCommanders() {
        COMMANDER_NAMES.forEach(
                commanderName -> {
                    CommanderEntity commanderEntity = new CommanderEntity();
                    commanderEntity.setName(commanderName);
                    commanderRepo.save(commanderEntity);
                }
        );
    }
}
