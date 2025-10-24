package com.example.logviewer.view;

import com.example.logviewer.entity.Customer;
import com.example.logviewer.service.CustomerService;
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

@Route("customers")
@PageTitle("Customers")
@PermitAll
public class CustomerListView extends VerticalLayout {
    
    private final CustomerService customerService;
    private final AuthenticationContext authenticationContext;
    private final Grid<Customer> grid = new Grid<>(Customer.class, false);
    
    @Autowired
    public CustomerListView(CustomerService customerService, AuthenticationContext authenticationContext) {
        this.customerService = customerService;
        this.authenticationContext = authenticationContext;
        
        addClassName("customer-list-view");
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
        H1 title = new H1("Customers");
        
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
        sessionsButton.addClickListener(e -> getUI().ifPresent(ui -> ui.navigate("sessions")));
        
        Button customersButton = new Button("Customers");
        customersButton.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        
        HorizontalLayout nav = new HorizontalLayout(logsButton, sessionsButton, customersButton);
        nav.setAlignItems(Alignment.CENTER);
        nav.setSpacing(true);
        
        return nav;
    }
    
    private void configureGrid() {
        grid.addClassName("customer-grid");
        grid.setSizeFull();
        grid.addThemeVariants(GridVariant.LUMO_ROW_STRIPES, GridVariant.LUMO_COMPACT);
        
        grid.addColumn(Customer::getCustomerCode)
            .setHeader("Customer Code")
            .setSortable(true)
            .setWidth("150px")
            .setFlexGrow(0);
        
        grid.addColumn(Customer::getName)
            .setHeader("Name")
            .setSortable(true)
            .setAutoWidth(true)
            .setFlexGrow(1);
        
        grid.addColumn(Customer::getEmail)
            .setHeader("Email")
            .setSortable(true)
            .setAutoWidth(true)
            .setFlexGrow(1);
        
        grid.addColumn(Customer::getPhone)
            .setHeader("Phone")
            .setSortable(true)
            .setWidth("150px")
            .setFlexGrow(0);
        
        grid.addColumn(Customer::getAddress)
            .setHeader("Address")
            .setSortable(true)
            .setAutoWidth(true)
            .setFlexGrow(1);
        
        grid.addColumn(customer -> customer.getSessions().size())
            .setHeader("Sessions")
            .setSortable(true)
            .setWidth("100px")
            .setFlexGrow(0);
    }
    
    private void updateList() {
        grid.setItems(customerService.getAllCustomers());
    }
}
