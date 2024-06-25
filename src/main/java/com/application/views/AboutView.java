package com.application.views;

import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.html.H3;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.router.RouteAlias;
import com.vaadin.flow.theme.lumo.LumoUtility.Margin;

@PageTitle("About")
@Route(value = "", layout = MainLayout.class)
@RouteAlias(value = "", layout = MainLayout.class)
public class AboutView extends VerticalLayout {

    public AboutView() {
        setSpacing(false);

        Image img = new Image("images/sc2 coop random image.png", "placeholder plant");
        img.setWidth("250px");
        add(img);

        H2 header = new H2("This is a Vaadin application with Spring Boot to generate random StarCraft2 Co-op Commander based on your selection");
        header.addClassNames(Margin.Top.XLARGE, Margin.Bottom.XLARGE);
        add(header);
        add(new H3("Current possible selections:"));
        add(new H3("- List Box"));

        setSizeFull();
        setJustifyContentMode(JustifyContentMode.CENTER);
        setDefaultHorizontalComponentAlignment(Alignment.CENTER);
        getStyle().set("text-align", "center");
    }

}
