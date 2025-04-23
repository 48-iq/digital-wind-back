package dev.digital_wind_gays.neo_story.user.security;

import org.springframework.security.authorization.AuthorizationDecision;
import org.springframework.security.authorization.AuthorizationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.access.intercept.RequestAuthorizationContext;

import java.util.function.Supplier;

public class DaoEndingsAuthorizer implements AuthorizationManager<RequestAuthorizationContext> {
    @Override
    public AuthorizationDecision
    check(Supplier<Authentication> authentication, RequestAuthorizationContext object) {
        return null;
    }

    @Override
    public void verify(Supplier<Authentication> authentication, RequestAuthorizationContext object) {
        AuthorizationManager.super.verify(authentication, object);
    }
}
