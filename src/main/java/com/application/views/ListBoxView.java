package com.application.views;

import com.application.CallMeDatabase;
import com.vaadin.flow.component.Key;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.H3;
import com.vaadin.flow.component.html.Hr;
import com.vaadin.flow.component.listbox.MultiSelectListBox;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;
import java.util.Set;

@PageTitle("List Box View")
@Route(value = "list-box", layout = MainLayout.class)
public class ListBoxView extends HorizontalLayout {

    public ListBoxView() {
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
        CallMeDatabase database = new CallMeDatabase();
        ArrayList<String> commanders = database.getAllCommanders();

        listBox.setItems(commanders);
        for (int i = 0; i < 2; i++) {
            listBox.addComponents(commanders.get(3 + (i*4)), new Hr());
        }
    }

}
