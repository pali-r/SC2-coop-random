package com.application.views;

import com.application.initialization.CommanderInitializer;
import com.application.repository.CommanderRepo;
import com.vaadin.flow.component.Key;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.H3;
import com.vaadin.flow.component.html.Hr;
import com.vaadin.flow.component.listbox.MultiSelectListBox;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.shared.Tooltip;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
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
        generateButton.addClickListener(
                e -> {
                    Set<String> selectedItems = listBox.getSelectedItems();
                    int randomNumber = new Random().nextInt(selectedItems.size());
                    Notification.show(selectedItems.toArray()[randomNumber].toString(),
                            5000, Notification.Position.MIDDLE);
                });
        generateButton.addClickShortcut(Key.ENTER);
        generateButton.setTooltipText("Executable by ENTER key")
                .withHoverDelay(600)
                .withHideDelay(200)
                .setPosition(Tooltip.TooltipPosition.BOTTOM);
        return generateButton;
    }

    private static void addCommandersToListBox(MultiSelectListBox<String> listBox) {
        List<String> commanders = new ArrayList<>();
        commanderRepo.findAll().forEach(
                commanderEntity -> commanders.add(commanderEntity.getName() + " - " + commanderEntity.getPrestige())
        );

//        listBox.setRenderer(new ComponentRenderer<>(
//                commander -> {
//                    HorizontalLayout row = new HorizontalLayout();
//                    row.setAlignItems(Alignment.CENTER);
//
//                    String[] splited = commander.split(" - ");
//                    Span name = new Span(splited[0]);
//                    Span prestige = new Span(splited[1]);
//                    prestige.getStyle()
//                            .set("font-size", "var(--lumo-font-size-s)");
//
//                    VerticalLayout column = new VerticalLayout(name, prestige);
//                    column.setPadding(false);
//                    column.setSpacing(false);
//
//                    row.add(column);
//
//                    return row;
//                }
//        ));

        listBox.setItems(commanders);

        for (int i = 3; i < commanders.size(); i++) {
            listBox.addComponents(commanders.get(i), new Hr());
            i += 3;
        }
    }
}
