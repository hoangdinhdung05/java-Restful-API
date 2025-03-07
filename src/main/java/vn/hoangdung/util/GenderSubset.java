package vn.hoangdung.util;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.RetentionPolicy.RUNTIME;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

@Documented
@Target({METHOD, FIELD})
@Retention(RUNTIME)
@Constraint(validatedBy = GenderSubSetValidator.class)

public @interface GenderSubset {
    Gender[] anyOf();
    String message() default "must be any of {anyOf}";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}