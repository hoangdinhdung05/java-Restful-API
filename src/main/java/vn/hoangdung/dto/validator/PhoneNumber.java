package vn.hoangdung.dto.validator;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import java.lang.annotation.*;


//Định nghĩa annotation PhoneNumber
//target: chỉ định nơi sử dụng annotation này (FIELD: chỉ sử dụng cho field), METHOD, PARAMETER, TYPE, ANNOTATION_TYPE, CONSTRUCTOR, LOCAL_VARIABLE, PACKAGE, MODULE
//retention: chỉ định thời gian sử dụng annotation này (RUNTIME: sử dụng khi chạy ứng dụng), SOURCE, CLASS
//documented: chỉ định có nên tạo javadoc cho annotation này hay không

@Documented
@Constraint(validatedBy = PhoneValidator.class)
@Target( { ElementType.METHOD, ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
public @interface PhoneNumber {
    String message() default "Invalid phone number";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
