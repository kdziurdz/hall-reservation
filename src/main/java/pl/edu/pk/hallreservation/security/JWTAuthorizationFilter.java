package pl.edu.pk.hallreservation.security;


import io.jsonwebtoken.Jwts;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.event.AuthenticationFailureDisabledEvent;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import pl.edu.pk.hallreservation.exception.ObjectNotFoundException;
import pl.edu.pk.hallreservation.exception.UserExpired;
import pl.edu.pk.hallreservation.exception.UserNotEnabled;
import pl.edu.pk.hallreservation.model.user.User;
import pl.edu.pk.hallreservation.service.user.UserService;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static pl.edu.pk.hallreservation.security.SecurityConstants.HEADER_STRING;
import static pl.edu.pk.hallreservation.security.SecurityConstants.SECRET;
import static pl.edu.pk.hallreservation.security.SecurityConstants.TOKEN_PREFIX;

@Configuration
public class JWTAuthorizationFilter extends BasicAuthenticationFilter {

    private UserDetailsService userService;

    public JWTAuthorizationFilter(AuthenticationManager authManager, UserDetailsService userService) {
        super(authManager);
        this.userService = userService;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest req,
                                    HttpServletResponse res,
                                    FilterChain chain) throws IOException, ServletException {
        String header = req.getHeader(HEADER_STRING);

        if (header == null || !header.startsWith(TOKEN_PREFIX)) {
            chain.doFilter(req, res);
            return;
        }

        UsernamePasswordAuthenticationToken authentication = getAuthentication(req);

        SecurityContextHolder.getContext().setAuthentication(authentication);
        chain.doFilter(req, res);
    }

    private UsernamePasswordAuthenticationToken getAuthentication(HttpServletRequest request) {

        String token = request.getHeader(HEADER_STRING);
        if (token != null) {

            String user = Jwts.parser()
                    .setSigningKey(SECRET.getBytes())
                    .parseClaimsJws(token.replace(TOKEN_PREFIX, ""))
                    .getBody()
                    .getSubject();

            if (user != null) {

                UserDetails userDetails = userService.loadUserByUsername(user);
                if(userDetails == null){
                    return null;
                }

                if(!userDetails.isEnabled()){
                    return null;
                }

                if(!userDetails.isAccountNonExpired()){
                    return null;
                }

                return new UsernamePasswordAuthenticationToken(user, null, userDetails.getAuthorities());
            }
            return null;
        }
        return null;
    }
}