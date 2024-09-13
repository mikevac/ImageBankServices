package com.vac.uilities;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import org.springframework.security.test.context.support.WithSecurityContext;

@Retention(RetentionPolicy.RUNTIME)
@WithSecurityContext(factory = WithMockIBUserSecurityContextFactory.class)
public @interface WithMockIBUser {

    String[] authorities() default { "USER" };

    String userName() default CommonTest.TEST_USER_NAME;

    String firstName() default CommonTest.TEST_FIRST_NAME;

    String lastName() default CommonTest.TEST_LAST_NAME;

    String password() default CommonTest.TEST_PASSWORD;

    String emailAddress() default CommonTest.TEST_EMAIL_ADDRESS;

    String timeZone() default CommonTest.TIME_ZONE;

}
