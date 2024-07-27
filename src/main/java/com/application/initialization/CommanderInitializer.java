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
    private final List<String> COMMANDERS_PRESTIGES = Arrays.asList("Renegade Commander", "Backwater Marshal", "Rough Rider", "Rebel Raider", "Queen of Blades", "Malevolent Matriarch", "Folly of Man", "Desolate Queen", "Hierarch of the Daelaam", "Valorous Inspirator", "Nexus Legate", "Arkship Commandant", "Chief Engineer", "Heavy Weapons Specialist", "Grease Monkey", "Payload Director", "Swarm Broodmother", "Scourge Queen", "Mother of Constructs", "Apex Predator", "Matriarch of the Nerazim", "Spirit of Respite", "Withering Siphon", "Keeper of Shadows", "Khalai Phase-Smith", "Architect of War", "Templar Apparent", "Solarite Celestial", "Evolution Master", "Essence Hoarder", "Tunneling Horror", "The Limitless", "Highlord of the Tal'darim", "Artificer of Souls", "Tyrant Ascendant", "Shadow of Death", "Dominion Ghost", "Soldier of Fortune", "Tactical Dispatcher", "Infiltration Specialist", "Infested Admiral", "Frightful Fleshwelder", "Plague Warden", "Lord of the Horde", "Purifier Executor", "Akhundelar", "Network Administrator", "Unconquered Spirit", "Primal Pack Leader", "Devouring One", "Primal Contender", "Broodbrother", "Mercenary Leader and Dominion Admiral", "Chaotic Power Couple", "Wing Commanders", "Galactic Gunrunners", "Legendary Outlaw", "Technical Recruiter", "Lone Wolf", "Dutiful Dogwalker", "Dark Prelate", "Anakh Sun", "Knowledge Seeker", "Herald of the Void", "Hero Genius (Henius)", "Signal Savant", "Best Buddy", "Oil Baron", "Emperor of the Dominion", "Toxic Tyrant", "Principal Proletariat", "Merchant of Death");

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
                        commanderEntity.setPrestige("P%d %s".formatted(i, COMMANDERS_PRESTIGES.get(COMMANDER_NAMES.indexOf(commanderName) * 4 + i)));
                        commanderRepo.save(commanderEntity);
                    }
                }
        );
    }
}
