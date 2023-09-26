package hasix.junear.springconfig.config.portfolio;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import javax.validation.Constraint;
import javax.validation.Payload;

@Documented
@Constraint(validatedBy = NotEmptyListValidator.class)
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface NotEmptyList {
    String message() default "반드시 하나 이상의 기업을 선택하셔야 합니다.";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};

}
