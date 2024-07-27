package com.application.views;

import com.application.repository.CommanderRepo;
import com.vaadin.flow.component.Key;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.combobox.MultiSelectComboBox;
import com.vaadin.flow.component.html.H3;
import com.vaadin.flow.component.html.Hr;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.shared.Tooltip;
import com.vaadin.flow.component.textfield.TextArea;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Set;
import java.util.stream.Collectors;

@Component
@PageTitle("Multi-Select Combo Box View")
@Route(value = "multi-select", layout = MainLayout.class)
public class MultiSelectComboBoxView extends VerticalLayout {
    private static CommanderRepo commanderRepo;

    public MultiSelectComboBoxView(CommanderRepo commanderRepo) {
        this.commanderRepo = commanderRepo;

        setMargin(true);

        add(new H3("Commanders"));

        MultiSelectComboBox<String> comboBox = new MultiSelectComboBox<>();
        addCommandersToComboBox(comboBox);

        TextArea selectedCommanders = new TextArea();
        selectedCommanders.setWidth("200px");
        selectedCommanders.setReadOnly(true);

        setUpComboBox(comboBox, selectedCommanders);

        Button generateButton = addGenerateButton(comboBox);

        HorizontalLayout row = new HorizontalLayout();
        row.add(comboBox, selectedCommanders, generateButton);

        add(row);
    }

    private Button addGenerateButton(MultiSelectComboBox<String> comboBox) {
        Button generateButton = new Button("Generate");
        generateButton.addClickListener(
                e -> {
                    Set<String> selectedItems = comboBox.getSelectedItems();
                    int randomNumber = new Random().nextInt(selectedItems.size());
                    Notification.show(selectedItems.toArray()[randomNumber].toString(),
                            5000, Notification.Position.MIDDLE);
                }
        );

        generateButton.addClickShortcut(Key.ENTER);
        generateButton.setTooltipText("Executable by ENTER key")
                .withHoverDelay(600)
                .withHideDelay(200)
                .setPosition(Tooltip.TooltipPosition.BOTTOM);

        return generateButton;
    }

    private void setUpComboBox(MultiSelectComboBox<String> comboBox, TextArea selected) {
        comboBox.setWidth("400px");
        comboBox.setAutoExpand(MultiSelectComboBox.AutoExpandMode.BOTH);
        comboBox.addValueChangeListener(
                e -> {
                    String userSelection = e.getValue().stream().map(String::toString).sorted().collect(Collectors.joining("\n"));
                    selected.setValue(userSelection);
                }
        );
    }

    private void addCommandersToComboBox(MultiSelectComboBox<String> comboBox) {
        List<String> commanders = new ArrayList<>();
        commanderRepo.findAll().forEach(
                commanderEntity -> commanders.add(commanderEntity.getName() + " - " + commanderEntity.getPrestige())
        );

        comboBox.setItems(commanders);
    }
}
