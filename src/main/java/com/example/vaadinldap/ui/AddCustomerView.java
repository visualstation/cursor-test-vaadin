package com.example.vaadinldap.ui;

import com.example.vaadinldap.domain.Customer;
import com.example.vaadinldap.service.CustomerService;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.EmailField;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.auth.Authenticated;
import reactor.core.publisher.Mono;

@Route(value = "add", layout = MainLayout.class)
@PageTitle("Add Customer | CRM")
@Authenticated
public class AddCustomerView extends VerticalLayout {

    public AddCustomerView(CustomerService service) {
        setSizeFull();

        TextField firstName = new TextField("First name");
        TextField lastName = new TextField("Last name");
        EmailField email = new EmailField("Email");
        TextField phone = new TextField("Phone");

        Button save = new Button("Save");

        FormLayout form = new FormLayout(firstName, lastName, email, phone, save);
        add(form);

        save.addClickListener(e -> {
            Customer customer = new Customer(null, firstName.getValue(), lastName.getValue(), email.getValue(), phone.getValue());
            Mono<Customer> saved = service.save(customer);
            saved.subscribe(c -> Notification.show("Saved customer #" + c.getId()));
            firstName.clear();
            lastName.clear();
            email.clear();
            phone.clear();
        });
    }
}
