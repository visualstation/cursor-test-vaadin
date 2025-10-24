package com.example.vaadinlog.security;

import com.vaadin.flow.component.login.LoginI18n;
import com.vaadin.flow.component.login.LoginOverlay;
import com.vaadin.flow.router.BeforeEnterEvent;
import com.vaadin.flow.router.BeforeEnterObserver;
import com.vaadin.flow.router.Route;

@Route("login")
public class LoginView extends LoginOverlay implements BeforeEnterObserver {

    public LoginView() {
        setI18n(createFrenchI18n());
        setTitle("Log App");
        setDescription("Connexion requise");
        setAction("login");
        setOpened(true);
        setForgotPasswordButtonVisible(false);
    }

    private LoginI18n createFrenchI18n() {
        LoginI18n i18n = LoginI18n.createDefault();
        i18n.setHeader(new LoginI18n.Header());
        i18n.getHeader().setTitle("Log App");
        i18n.getHeader().setDescription("Veuillez vous connecter");
        i18n.getForm().setTitle("Connexion");
        i18n.getForm().setUsername("Utilisateur");
        i18n.getForm().setPassword("Mot de passe");
        i18n.getForm().setSubmit("Se connecter");
        i18n.getForm().setForgotPassword("Mot de passe oublié");
        i18n.getErrorMessage().setTitle("Identifiants invalides");
        i18n.getErrorMessage().setMessage("Vérifiez le nom d'utilisateur et le mot de passe");
        i18n.setAdditionalInformation(null);
        return i18n;
    }

    @Override
    public void beforeEnter(BeforeEnterEvent event) {
        if (event.getLocation().getQueryParameters().getParameters().containsKey("error")) {
            setError(true);
        }
    }
}
