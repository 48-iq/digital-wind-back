package dev.digital_wind_gays.neo_story.user.security;

import org.springframework.security.authorization.AuthorizationDecision;
import org.springframework.security.authorization.AuthorizationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.access.intercept.RequestAuthorizationContext;
import org.springframework.stereotype.Component;

import java.util.function.Supplier;

@Component
public class UserAuthorizer implements AuthorizationManager<RequestAuthorizationContext> {
    @Override
    public AuthorizationDecision check(Supplier<Authentication> authenticationSupplier,
                                       RequestAuthorizationContext object) {
        Authentication authentication = authenticationSupplier.get();
        DaoUserDetails userDetails = (DaoUserDetails) authentication.getPrincipal();
        String userId = object.getVariables().get("userId");
        if (userDetails.getUser().getId().equals(userId)) {
            return new AuthorizationDecision(true);
        }
        return new AuthorizationDecision(false);
    }
}
