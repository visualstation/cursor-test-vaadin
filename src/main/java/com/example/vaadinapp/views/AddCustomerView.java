package com.example.vaadinapp.views;

import com.example.vaadinapp.model.Customer;
import com.example.vaadinapp.service.CustomerService;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.notification.NotificationVariant;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.EmailField;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.Binder;
import com.vaadin.flow.data.binder.ValidationException;
import com.vaadin.flow.data.validator.EmailValidator;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.theme.lumo.LumoUtility;
import jakarta.annotation.security.PermitAll;

@Route(value = "add-customer", layout = MainLayout.class)
@PageTitle("Add Customer | Customer Management")
@PermitAll
public class AddCustomerView extends VerticalLayout {

    private final CustomerService customerService;
    private final Binder<Customer> binder = new Binder<>(Customer.class);
    
    private TextField firstName = new TextField("First Name");
    private TextField lastName = new TextField("Last Name");
    private EmailField email = new EmailField("Email");
    private TextField phone = new TextField("Phone");
    private TextField address = new TextField("Address");
    private TextField city = new TextField("City");
    private TextField country = new TextField("Country");
    
    private Button saveButton = new Button("Save Customer");
    private Button clearButton = new Button("Clear Form");
    private Button cancelButton = new Button("Cancel");

    public AddCustomerView(CustomerService customerService) {
        this.customerService = customerService;
        
        addClassName("add-customer-view");
        setSizeFull();
        setPadding(true);

        H2 title = new H2("Add New Customer");
        title.addClassNames(LumoUtility.Margin.Bottom.MEDIUM);

        FormLayout formLayout = createForm();
        HorizontalLayout buttonLayout = createButtonLayout();
        
        VerticalLayout formContainer = new VerticalLayout(formLayout, buttonLayout);
        formContainer.setMaxWidth("800px");
        formContainer.setPadding(true);
        formContainer.getStyle()
            .set("box-shadow", "0 2px 8px rgba(0, 0, 0, 0.1)")
            .set("border-radius", "8px")
            .set("background", "var(--lumo-base-color)");

        add(title, formContainer);
        configureBinder();
    }

    private FormLayout createForm() {
        FormLayout formLayout = new FormLayout();
        
        // Configure fields
        firstName.setRequired(true);
        firstName.setPlaceholder("Enter first name");
        
        lastName.setRequired(true);
        lastName.setPlaceholder("Enter last name");
        
        email.setRequired(true);
        email.setPlaceholder("customer@example.com");
        email.setErrorMessage("Please enter a valid email address");
        
        phone.setPlaceholder("+1234567890");
        
        address.setPlaceholder("123 Main Street");
        
        city.setPlaceholder("City name");
        
        country.setPlaceholder("Country name");
        
        // Add fields to form
        formLayout.add(
            firstName,
            lastName,
            email,
            phone,
            address,
            city,
            country
        );
        
        // Make form responsive
        formLayout.setResponsiveSteps(
            new FormLayout.ResponsiveStep("0", 1),
            new FormLayout.ResponsiveStep("500px", 2)
        );
        
        formLayout.setColspan(email, 2);
        formLayout.setColspan(address, 2);
        
        return formLayout;
    }

    private HorizontalLayout createButtonLayout() {
        saveButton.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        saveButton.addClickListener(e -> saveCustomer());
        
        clearButton.addThemeVariants(ButtonVariant.LUMO_TERTIARY);
        clearButton.addClickListener(e -> clearForm());
        
        cancelButton.addThemeVariants(ButtonVariant.LUMO_TERTIARY);
        cancelButton.addClickListener(e -> navigateToSearch());
        
        HorizontalLayout buttonLayout = new HorizontalLayout(saveButton, clearButton, cancelButton);
        buttonLayout.setSpacing(true);
        buttonLayout.addClassNames(LumoUtility.Margin.Top.MEDIUM);
        
        return buttonLayout;
    }

    private void configureBinder() {
        binder.forField(firstName)
            .asRequired("First name is required")
            .withValidator(name -> name.length() >= 2, "First name must be at least 2 characters")
            .bind(Customer::getFirstName, Customer::setFirstName);
        
        binder.forField(lastName)
            .asRequired("Last name is required")
            .withValidator(name -> name.length() >= 2, "Last name must be at least 2 characters")
            .bind(Customer::getLastName, Customer::setLastName);
        
        binder.forField(email)
            .asRequired("Email is required")
            .withValidator(new EmailValidator("Please enter a valid email address"))
            .bind(Customer::getEmail, Customer::setEmail);
        
        binder.forField(phone)
            .bind(Customer::getPhone, Customer::setPhone);
        
        binder.forField(address)
            .bind(Customer::getAddress, Customer::setAddress);
        
        binder.forField(city)
            .bind(Customer::getCity, Customer::setCity);
        
        binder.forField(country)
            .bind(Customer::getCountry, Customer::setCountry);
    }

    private void saveCustomer() {
        try {
            Customer customer = new Customer();
            binder.writeBean(customer);
            
            customerService.saveCustomerBlocking(customer);
            
            Notification notification = Notification.show(
                "Customer " + customer.getFullName() + " added successfully!",
                3000,
                Notification.Position.TOP_CENTER
            );
            notification.addThemeVariants(NotificationVariant.LUMO_SUCCESS);
            
            clearForm();
            
        } catch (ValidationException e) {
            Notification notification = Notification.show(
                "Please check the form for errors",
                3000,
                Notification.Position.TOP_CENTER
            );
            notification.addThemeVariants(NotificationVariant.LUMO_ERROR);
        } catch (Exception e) {
            Notification notification = Notification.show(
                "Error saving customer: " + e.getMessage(),
                5000,
                Notification.Position.TOP_CENTER
            );
            notification.addThemeVariants(NotificationVariant.LUMO_ERROR);
        }
    }

    private void clearForm() {
        binder.readBean(null);
        firstName.focus();
    }

    private void navigateToSearch() {
        getUI().ifPresent(ui -> ui.navigate(SearchView.class));
    }
}
