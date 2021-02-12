package com.devonfw.application.jtqj.general.common.api.validation;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;
import java.lang.annotation.ElementType;
import java.lang.annotation.RetentionPolicy;

import javax.validation.Constraint;
import javax.validation.Payload;
import javax.validation.constraints.Email;
import javax.validation.constraints.Pattern;

@Email
@Pattern(regexp = ".+@.+\\..+", message = "Email must specify a domain")
@Target({ ElementType.METHOD, ElementType.FIELD, ElementType.ANNOTATION_TYPE })
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = {})
@Documented
public @interface EmailExtended {
  String message() default "Please provide a valid email address";

  Class<?>[] groups() default {};

  Class<? extends Payload>[] payload() default {};
}
