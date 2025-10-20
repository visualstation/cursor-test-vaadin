package com.example.vaadinldap.ui;

import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.login.LoginI18n;
import com.vaadin.flow.component.login.LoginOverlay;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.auth.AnonymousAllowed;

@Route("login")
@AnonymousAllowed
@PageTitle("Sign in | CRM")
public class LoginView extends VerticalLayout {

    public LoginView() {
        setSizeFull();
        setJustifyContentMode(JustifyContentMode.CENTER);
        setAlignItems(Alignment.CENTER);

        LoginOverlay login = new LoginOverlay();
        LoginI18n i18n = LoginI18n.createDefault();
        i18n.setHeader(new LoginI18n.Header());
        i18n.getHeader().setTitle("Reactive CRM");
        i18n.getHeader().setDescription("Sign in with LDAP");
        i18n.getForm().setTitle("Welcome");
        i18n.getForm().setUsername("Username");
        i18n.getForm().setPassword("Password");
        i18n.getForm().setSubmit("Sign in");
        login.setI18n(i18n);
        login.setAction("login");
        login.setOpened(true);
        login.setForgotPasswordButtonVisible(false);
        add(new H1(""));
        add(login);
    }
}
