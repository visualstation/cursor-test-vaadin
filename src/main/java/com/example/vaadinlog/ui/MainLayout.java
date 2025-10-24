package com.example.vaadinlog.ui;

import com.example.vaadinlog.ui.LogsView;
import com.vaadin.flow.component.applayout.AppLayout;
import com.vaadin.flow.component.html.Anchor;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.router.RouterLink;

public class MainLayout extends AppLayout {

    public MainLayout() {
        createHeader();
    }

    private void createHeader() {
        H1 logo = new H1("Log App");
        logo.getStyle().set("font-size", "var(--lumo-font-size-l)");
        logo.getStyle().set("margin", "0");

        RouterLink logsLink = new RouterLink("Logs", LogsView.class);
        Anchor logout = new Anchor("/logout", "Logout");

        HorizontalLayout header = new HorizontalLayout(logo, logsLink, logout);
        header.setDefaultVerticalComponentAlignment(FlexComponent.Alignment.CENTER);
        header.expand(logo);
        header.setWidthFull();
        header.getStyle().set("padding", "var(--lumo-space-m)");

        addToNavbar(header);
    }
}
