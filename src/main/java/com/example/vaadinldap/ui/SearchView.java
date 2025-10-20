package com.example.vaadinldap.ui;

import com.example.vaadinldap.domain.Customer;
import com.example.vaadinldap.service.CustomerService;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.value.ValueChangeMode;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.auth.Authenticated;
import reactor.core.publisher.Flux;

@Route(value = "", layout = MainLayout.class)
@PageTitle("Search | CRM")
@Authenticated
public class SearchView extends VerticalLayout {

    private final Grid<Customer> grid = new Grid<>(Customer.class, false);
    private final TextField searchField = new TextField("Search");

    public SearchView(CustomerService service) {
        setSizeFull();
        searchField.setPlaceholder("Name or email");
        searchField.setClearButtonVisible(true);
        searchField.setValueChangeMode(ValueChangeMode.EAGER);

        grid.addColumn(Customer::getFirstName).setHeader("First").setAutoWidth(true);
        grid.addColumn(Customer::getLastName).setHeader("Last").setAutoWidth(true);
        grid.addColumn(Customer::getEmail).setHeader("Email").setAutoWidth(true);
        grid.addColumn(Customer::getPhone).setHeader("Phone").setAutoWidth(true);
        grid.setSizeFull();

        add(searchField, grid);
        setFlexGrow(1, grid);

        // Reactive updates on text change, UI-thread safe
        final UI ui = UI.getCurrent();
        searchField.addValueChangeListener(e ->
            service.search(e.getValue())
                .collectList()
                .subscribe(items -> ui.access(() -> grid.setItems(items)))
        );

        // Initial load
        service.findAll().collectList().subscribe(items -> ui.access(() -> grid.setItems(items)));
    }
}
