package org.blackist.web.springbootor.model.security;

import org.blackist.web.springbootor.model.entity.system.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;

import java.util.Collection;

public class SecurityModelFactory {

    public static UserDetailImpl create(User user) {

        Collection<? extends GrantedAuthority> authorities;

        try {
            authorities = AuthorityUtils.commaSeparatedStringToAuthorityList(user.getAuthority());
        } catch (Exception e) {
            authorities = null;
        }


        return new UserDetailImpl(
                user.getId(),
                user.getUsername(),
                user.getPassword(),
                user.getLastPasswordReset(),
                user.isEnable(),
                authorities
        );
    }
}
