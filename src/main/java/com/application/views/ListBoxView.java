package com.application.views;

import com.application.model.CommanderEntity;
import com.application.repository.CommanderRepo;
import com.vaadin.flow.component.Key;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.H3;
import com.vaadin.flow.component.listbox.MultiSelectListBox;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Random;
import java.util.Set;

@Component
@PageTitle("List Box View")
@Route(value = "list-box", layout = MainLayout.class)
public class ListBoxView extends HorizontalLayout {
    private static CommanderRepo commanderRepo;

    @Autowired
    public ListBoxView(CommanderRepo commanderRepo) {
        this.commanderRepo = commanderRepo;

        add(new H3("Commanders:"));

        MultiSelectListBox<String> listBox = new MultiSelectListBox<>();
        addCommandersToListBox(listBox);

        Button generateButton = addGenerateButton(listBox);

        setMargin(true);

        add(listBox, generateButton);

    }

    private static Button addGenerateButton(MultiSelectListBox<String> listBox) {
        Button generateButton = new Button("Generate");
        generateButton.addClickListener(e -> {
            Set<String> selectedItems = listBox.getSelectedItems();
            int randomNumber = new Random().nextInt(selectedItems.size());
            Notification.show(selectedItems.toArray()[randomNumber].toString(), 5000, Notification.Position.MIDDLE);
        });
        generateButton.addClickShortcut(Key.ENTER);
        return generateButton;
    }

    private static void addCommandersToListBox(MultiSelectListBox<String> listBox) {
        CommanderEntity commanderEntity = new CommanderEntity();
        commanderEntity.setName("raynor");
        commanderRepo.save(commanderEntity);

        CommanderEntity commanderEntity2 = new CommanderEntity();
        commanderEntity2.setName("kerrigan");
        commanderRepo.save(commanderEntity2);

        CommanderEntity commanderEntity3 = new CommanderEntity();
        commanderEntity3.setName("artanis");
        commanderRepo.save(commanderEntity3);

        ArrayList<String> strings = new ArrayList<>();
        for (CommanderEntity commander : commanderRepo.findAll()) {
            strings.add(commander.getName());
        }

        listBox.setItems(strings);
    }

}