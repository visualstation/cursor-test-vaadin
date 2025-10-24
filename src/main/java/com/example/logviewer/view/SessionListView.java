package com.example.logviewer.view;

import com.example.logviewer.entity.Session;
import com.example.logviewer.service.SessionService;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.grid.GridVariant;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.spring.security.AuthenticationContext;
import jakarta.annotation.security.PermitAll;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.format.DateTimeFormatter;

@Route("sessions")
@PageTitle("Sessions")
@PermitAll
public class SessionListView extends VerticalLayout {
    
    private final SessionService sessionService;
    private final AuthenticationContext authenticationContext;
    private final Grid<Session> grid = new Grid<>(Session.class, false);
    
    private static final DateTimeFormatter DATE_FORMATTER = 
            DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
    
    @Autowired
    public SessionListView(SessionService sessionService, AuthenticationContext authenticationContext) {
        this.sessionService = sessionService;
        this.authenticationContext = authenticationContext;
        
        addClassName("session-list-view");
        setSizeFull();
        
        configureGrid();
        
        add(
            createHeader(),
            createNavigation(),
            grid
        );
        
        updateList();
    }
    
    private HorizontalLayout createHeader() {
        H1 title = new H1("Sessions");
        
        Button logoutButton = new Button("Logout");
        logoutButton.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        logoutButton.addClickListener(e -> {
            authenticationContext.logout();
        });
        
        HorizontalLayout header = new HorizontalLayout(title, logoutButton);
        header.setWidthFull();
        header.setJustifyContentMode(JustifyContentMode.BETWEEN);
        header.setAlignItems(Alignment.CENTER);
        
        return header;
    }
    
    private HorizontalLayout createNavigation() {
        Button logsButton = new Button("Intervention Logs");
        logsButton.addClickListener(e -> getUI().ifPresent(ui -> ui.navigate("")));
        
        Button sessionsButton = new Button("Sessions");
        sessionsButton.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        
        Button customersButton = new Button("Customers");
        customersButton.addClickListener(e -> getUI().ifPresent(ui -> ui.navigate("customers")));
        
        HorizontalLayout nav = new HorizontalLayout(logsButton, sessionsButton, customersButton);
        nav.setAlignItems(Alignment.CENTER);
        nav.setSpacing(true);
        
        return nav;
    }
    
    private void configureGrid() {
        grid.addClassName("session-grid");
        grid.setSizeFull();
        grid.addThemeVariants(GridVariant.LUMO_ROW_STRIPES, GridVariant.LUMO_COMPACT);
        
        grid.addColumn(Session::getSessionId)
            .setHeader("Session ID")
            .setSortable(true)
            .setWidth("150px")
            .setFlexGrow(0);
        
        grid.addColumn(session -> session.getCustomer().getName())
            .setHeader("Customer")
            .setSortable(true)
            .setWidth("200px")
            .setFlexGrow(0);
        
        grid.addColumn(session -> session.getStartTime().format(DATE_FORMATTER))
            .setHeader("Start Time")
            .setSortable(true)
            .setWidth("150px")
            .setFlexGrow(0);
        
        grid.addColumn(session -> session.getEndTime() != null ? 
                session.getEndTime().format(DATE_FORMATTER) : "N/A")
            .setHeader("End Time")
            .setSortable(true)
            .setWidth("150px")
            .setFlexGrow(0);
        
        grid.addColumn(Session::getStatus)
            .setHeader("Status")
            .setSortable(true)
            .setWidth("120px")
            .setFlexGrow(0);
        
        grid.addColumn(Session::getDescription)
            .setHeader("Description")
            .setSortable(true)
            .setAutoWidth(true)
            .setFlexGrow(1);
        
        grid.addColumn(session -> session.getInterventionLogs().size())
            .setHeader("Logs")
            .setSortable(true)
            .setWidth("80px")
            .setFlexGrow(0);
    }
    
    private void updateList() {
        grid.setItems(sessionService.getAllSessions());
    }
}
