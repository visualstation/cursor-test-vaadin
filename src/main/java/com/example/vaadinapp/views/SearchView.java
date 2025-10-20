package com.example.vaadinapp.views;

import com.example.vaadinapp.model.Customer;
import com.example.vaadinapp.service.CustomerService;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.confirmdialog.ConfirmDialog;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.grid.GridVariant;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.notification.NotificationVariant;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.value.ValueChangeMode;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.spring.security.AuthenticationContext;
import com.vaadin.flow.theme.lumo.LumoUtility;
import jakarta.annotation.security.PermitAll;

import java.util.List;

@Route(value = "", layout = MainLayout.class)
@PageTitle("Search Customers | Customer Management")
@PermitAll
public class SearchView extends VerticalLayout {

    private final CustomerService customerService;
    private final Grid<Customer> grid = new Grid<>(Customer.class, false);
    private final TextField searchField = new TextField();

    public SearchView(CustomerService customerService, AuthenticationContext authenticationContext) {
        this.customerService = customerService;
        
        addClassName("search-view");
        setSizeFull();
        setPadding(true);

        configureGrid();
        configureSearchField();

        H2 title = new H2("Customer Search");
        title.addClassNames(LumoUtility.Margin.Bottom.MEDIUM);

        HorizontalLayout toolbar = createToolbar();
        
        add(title, toolbar, grid);
        updateList();
    }

    private void configureSearchField() {
        searchField.setPlaceholder("Search customers...");
        searchField.setPrefixComponent(new Icon(VaadinIcon.SEARCH));
        searchField.setValueChangeMode(ValueChangeMode.LAZY);
        searchField.addValueChangeListener(e -> updateList());
        searchField.setWidth("300px");
    }

    private HorizontalLayout createToolbar() {
        Button clearButton = new Button("Clear", new Icon(VaadinIcon.REFRESH));
        clearButton.addThemeVariants(ButtonVariant.LUMO_TERTIARY);
        clearButton.addClickListener(e -> {
            searchField.clear();
            updateList();
        });

        HorizontalLayout toolbar = new HorizontalLayout(searchField, clearButton);
        toolbar.setAlignItems(Alignment.BASELINE);
        toolbar.setWidthFull();
        
        return toolbar;
    }

    private void configureGrid() {
        grid.addClassName("customer-grid");
        grid.setSizeFull();
        
        grid.addColumn(Customer::getId)
            .setHeader("ID")
            .setAutoWidth(true)
            .setFlexGrow(0)
            .setSortable(true);
        
        grid.addColumn(Customer::getFirstName)
            .setHeader("First Name")
            .setAutoWidth(true)
            .setSortable(true);
        
        grid.addColumn(Customer::getLastName)
            .setHeader("Last Name")
            .setAutoWidth(true)
            .setSortable(true);
        
        grid.addColumn(Customer::getEmail)
            .setHeader("Email")
            .setAutoWidth(true)
            .setSortable(true);
        
        grid.addColumn(Customer::getPhone)
            .setHeader("Phone")
            .setAutoWidth(true);
        
        grid.addColumn(Customer::getCity)
            .setHeader("City")
            .setAutoWidth(true)
            .setSortable(true);
        
        grid.addColumn(Customer::getCountry)
            .setHeader("Country")
            .setAutoWidth(true)
            .setSortable(true);

        grid.addComponentColumn(customer -> {
            Button deleteButton = new Button(new Icon(VaadinIcon.TRASH));
            deleteButton.addThemeVariants(ButtonVariant.LUMO_ERROR, ButtonVariant.LUMO_SMALL);
            deleteButton.addClickListener(e -> deleteCustomer(customer));
            return deleteButton;
        }).setHeader("Actions").setAutoWidth(true).setFlexGrow(0);

        grid.addThemeVariants(
            GridVariant.LUMO_ROW_STRIPES,
            GridVariant.LUMO_WRAP_CELL_CONTENT,
            GridVariant.LUMO_COLUMN_BORDERS
        );
    }

    private void deleteCustomer(Customer customer) {
        ConfirmDialog dialog = new ConfirmDialog();
        dialog.setHeader("Delete Customer?");
        dialog.setText("Are you sure you want to delete " + customer.getFullName() + "?");
        
        dialog.setCancelable(true);
        dialog.setConfirmText("Delete");
        dialog.setConfirmButtonTheme("error primary");
        
        dialog.addConfirmListener(event -> {
            try {
                customerService.deleteCustomerBlocking(customer.getId());
                updateList();
                
                Notification notification = Notification.show(
                    "Customer deleted successfully",
                    3000,
                    Notification.Position.TOP_CENTER
                );
                notification.addThemeVariants(NotificationVariant.LUMO_SUCCESS);
            } catch (Exception e) {
                Notification notification = Notification.show(
                    "Error deleting customer: " + e.getMessage(),
                    5000,
                    Notification.Position.TOP_CENTER
                );
                notification.addThemeVariants(NotificationVariant.LUMO_ERROR);
            }
        });
        
        dialog.open();
    }

    private void updateList() {
        try {
            String searchTerm = searchField.getValue();
            List<Customer> customers;
            
            if (searchTerm == null || searchTerm.trim().isEmpty()) {
                customers = customerService.getAllCustomersBlocking();
            } else {
                customers = customerService.searchCustomersBlocking(searchTerm);
            }
            
            grid.setItems(customers);
        } catch (Exception e) {
            Notification notification = Notification.show(
                "Error loading customers: " + e.getMessage(),
                5000,
                Notification.Position.TOP_CENTER
            );
            notification.addThemeVariants(NotificationVariant.LUMO_ERROR);
        }
    }
}
