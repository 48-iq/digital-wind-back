package dev.digital_wind_gays.neo_story.user.security;

import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import dev.digital_wind_gays.neo_story.user.entity.User;
import dev.digital_wind_gays.neo_story.user.exceptions.UserNotFoundException;
import dev.digital_wind_gays.neo_story.user.repository.UserRepository;
import dev.digital_wind_gays.neo_story.user.services.JwtService;
import jakarta.annotation.Nonnull;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.ZoneId;

public class JwtFilter extends OncePerRequestFilter {
    @Autowired
    private JwtService jwtService;

    @Autowired
    private UserRepository userRepository;

    @Override
    protected void doFilterInternal(@Nonnull HttpServletRequest request,
                                    @Nonnull HttpServletResponse response,
                                    @Nonnull FilterChain filterChain) throws ServletException, IOException {
        String authorizationHeader = request.getHeader("Authorization");
        try {
            if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
                String token = authorizationHeader.substring(7);

                DecodedJWT decodedJWT = jwtService.verify(token);
                String userId = decodedJWT.getClaim("userId").asString();
                LocalDateTime issuedAt = decodedJWT.getIssuedAt().toInstant()
                        .atZone(ZoneId.systemDefault()).toLocalDateTime();

                User user = userRepository.findById(userId).orElseThrow(
                        () -> new UserNotFoundException("User with id " + userId + " not found")
                );

                UserDetails userDetails = new DaoUserDetails(user);
                Authentication authentication = new UsernamePasswordAuthenticationToken(
                        userDetails, null, userDetails.getAuthorities()
                );
                authentication.setAuthenticated(true);
                if (SecurityContextHolder.getContext().getAuthentication() == null ||
                        !SecurityContextHolder.getContext().getAuthentication().isAuthenticated()) {
                    SecurityContextHolder.getContext().setAuthentication(authentication);
                }
            }
            filterChain.doFilter(request, response);
        } catch (JWTVerificationException e) {
            response.getWriter().write("Invalid authorization JWT");
        } catch (UserNotFoundException e) {
            response.getWriter().write(e.getMessage());
        }
    }
}
