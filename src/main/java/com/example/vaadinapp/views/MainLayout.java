package com.example.vaadinapp.views;

import com.vaadin.flow.component.applayout.AppLayout;
import com.vaadin.flow.component.applayout.DrawerToggle;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.RouterLink;
import com.vaadin.flow.spring.security.AuthenticationContext;
import com.vaadin.flow.theme.lumo.LumoUtility;

public class MainLayout extends AppLayout {

    private final transient AuthenticationContext authenticationContext;

    public MainLayout(AuthenticationContext authenticationContext) {
        this.authenticationContext = authenticationContext;
        createHeader();
        createDrawer();
    }

    private void createHeader() {
        H1 logo = new H1("Customer Management System");
        logo.addClassNames(
            LumoUtility.FontSize.LARGE,
            LumoUtility.Margin.MEDIUM
        );

        String username = authenticationContext.getAuthenticatedUser(org.springframework.security.core.userdetails.UserDetails.class)
            .map(user -> user.getUsername())
            .orElse("Anonymous");

        Span userInfo = new Span("User: " + username);
        userInfo.addClassNames(LumoUtility.FontSize.SMALL, LumoUtility.TextColor.SECONDARY);

        Button logout = new Button("Logout", new Icon(VaadinIcon.SIGN_OUT));
        logout.addThemeVariants(ButtonVariant.LUMO_TERTIARY);
        logout.addClickListener(e -> {
            authenticationContext.logout();
        });

        HorizontalLayout userLayout = new HorizontalLayout(userInfo, logout);
        userLayout.setAlignItems(FlexComponent.Alignment.CENTER);
        userLayout.setSpacing(true);

        HorizontalLayout header = new HorizontalLayout(
            new DrawerToggle(),
            logo
        );
        header.setDefaultVerticalComponentAlignment(FlexComponent.Alignment.CENTER);
        header.expand(logo);
        header.setWidthFull();
        header.addClassNames(
            LumoUtility.Padding.Vertical.NONE,
            LumoUtility.Padding.Horizontal.MEDIUM
        );

        // Combine header and user info
        HorizontalLayout topBar = new HorizontalLayout(header, userLayout);
        topBar.setWidthFull();
        topBar.setJustifyContentMode(FlexComponent.JustifyContentMode.BETWEEN);
        topBar.setAlignItems(FlexComponent.Alignment.CENTER);

        addToNavbar(topBar);
    }

    private void createDrawer() {
        RouterLink searchLink = new RouterLink("Search Customers", SearchView.class);
        searchLink.setHighlightAction(true);
        Icon searchIcon = VaadinIcon.SEARCH.create();
        searchLink.add(searchIcon);
        searchLink.addClassNames(LumoUtility.Display.FLEX, LumoUtility.Gap.SMALL);

        RouterLink addLink = new RouterLink("Add Customer", AddCustomerView.class);
        addLink.setHighlightAction(true);
        Icon addIcon = VaadinIcon.PLUS_CIRCLE.create();
        addLink.add(addIcon);
        addLink.addClassNames(LumoUtility.Display.FLEX, LumoUtility.Gap.SMALL);

        VerticalLayout drawerLayout = new VerticalLayout(
            searchLink,
            addLink
        );
        drawerLayout.setPadding(true);
        drawerLayout.setSpacing(true);

        addToDrawer(drawerLayout);
    }
}
