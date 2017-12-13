package pl.edu.pk.hallreservation.security;


import org.springframework.security.access.PermissionEvaluator;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.io.Serializable;
import java.util.Collection;

@Component
class DomainAwarePermissionEvaluator implements PermissionEvaluator {

    @Override
    public boolean hasPermission(Authentication authentication, Object targetDomainObject, Object permission) {
//        if ("place-order".equals(permission)) {
//            Order order = (Order) targetDomainObject;
//            if (order.getAmount() > 500) {
//                return hasRole("ROLE_ADMIN", authentication);
//            }
//        }

        return true; // todo wtf
    }

    @Override
    public boolean hasPermission(Authentication authentication, Serializable targetId, String targetType,
                                 Object permission) {
        return hasPermission(authentication, null, permission);
    }

    private boolean hasRole(String role, Authentication auth) {

        if (auth == null || auth.getPrincipal() == null) {
            return false;
        }

        Collection<? extends GrantedAuthority> authorities = auth.getAuthorities();

        if (CollectionUtils.isEmpty(authorities)) {
            return false;
        }

        return authorities.stream().anyMatch(ga -> role.equals(ga.getAuthority()));
    }
}