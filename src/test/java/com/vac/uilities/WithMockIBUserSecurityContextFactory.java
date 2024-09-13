package com.vac.uilities;

import java.util.ArrayList;
import java.util.List;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.test.context.support.WithSecurityContextFactory;

import com.vac.main.model.IBUser;

public class WithMockIBUserSecurityContextFactory implements WithSecurityContextFactory<WithMockIBUser> {

    @Override
    public SecurityContext createSecurityContext(WithMockIBUser ibUser) {
        SecurityContext ctx = SecurityContextHolder.createEmptyContext();
        List<SimpleGrantedAuthority> roles = new ArrayList<>();
        for (String role : ibUser.authorities()) {
            roles.add(new SimpleGrantedAuthority(role));
        }

        IBUser principal = new IBUser(CommonTest.TEST_USER_ID, ibUser.userName(), ibUser.password(),
                ibUser.authorities(), ibUser.firstName(), ibUser.lastName(), ibUser.emailAddress(), ibUser.timeZone());

        Authentication auth = new UsernamePasswordAuthenticationToken(principal, null, principal.getAuthorities());
        ctx.setAuthentication(auth);
        return ctx;
    }

}
