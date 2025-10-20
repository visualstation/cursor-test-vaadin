package com.example.vaadinapp.views;

import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.html.Paragraph;
import com.vaadin.flow.component.login.LoginForm;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.BeforeEnterEvent;
import com.vaadin.flow.router.BeforeEnterObserver;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.auth.AnonymousAllowed;
import com.vaadin.flow.theme.lumo.LumoUtility;

@Route("login")
@PageTitle("Login | Customer Management")
@AnonymousAllowed
public class LoginView extends VerticalLayout implements BeforeEnterObserver {

    private final LoginForm login = new LoginForm();

    public LoginView() {
        addClassName("login-view");
        setSizeFull();
        setAlignItems(Alignment.CENTER);
        setJustifyContentMode(JustifyContentMode.CENTER);

        login.setAction("login");
        login.setForgotPasswordButtonVisible(false);

        // Create welcome section
        H1 title = new H1("Customer Management System");
        title.addClassNames(
            LumoUtility.TextColor.PRIMARY,
            LumoUtility.FontSize.XXXLARGE,
            LumoUtility.Margin.Bottom.NONE
        );

        H2 subtitle = new H2("Please login to continue");
        subtitle.addClassNames(
            LumoUtility.TextColor.SECONDARY,
            LumoUtility.FontSize.MEDIUM,
            LumoUtility.Margin.Top.SMALL
        );

        Paragraph instructions = new Paragraph("Use your LDAP credentials to login");
        instructions.addClassNames(
            LumoUtility.TextColor.SECONDARY,
            LumoUtility.FontSize.SMALL,
            LumoUtility.Margin.Bottom.LARGE
        );

        // Demo credentials info
        Paragraph demoInfo = new Paragraph("Demo Credentials: username: admin | password: admin123");
        demoInfo.addClassNames(
            LumoUtility.TextColor.TERTIARY,
            LumoUtility.FontSize.SMALL,
            LumoUtility.Margin.Top.MEDIUM,
            LumoUtility.Padding.MEDIUM,
            LumoUtility.Background.CONTRAST_5,
            LumoUtility.BorderRadius.MEDIUM
        );

        VerticalLayout loginLayout = new VerticalLayout();
        loginLayout.setAlignItems(Alignment.CENTER);
        loginLayout.setMaxWidth("400px");
        loginLayout.setPadding(true);
        loginLayout.add(
            title,
            subtitle,
            instructions,
            login,
            demoInfo
        );

        // Style the login layout
        loginLayout.getStyle()
            .set("box-shadow", "0 4px 8px rgba(0, 0, 0, 0.1)")
            .set("border-radius", "8px")
            .set("background", "var(--lumo-base-color)")
            .set("padding", "2rem");

        add(loginLayout);
    }

    @Override
    public void beforeEnter(BeforeEnterEvent beforeEnterEvent) {
        // Show error message if login failed
        if (beforeEnterEvent.getLocation()
                .getQueryParameters()
                .getParameters()
                .containsKey("error")) {
            login.setError(true);
        }
    }
}
