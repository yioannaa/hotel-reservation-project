package jk.hotelreservationproject.validation;

import javax.validation.Constraint;
import javax.validation.Payload;

import javax.validation.Constraint;
import java.lang.annotation.*;

@Target({ElementType.TYPE, ElementType.ANNOTATION_TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = {DateRangeValidator.class})
@Documented
public @interface ValidateDateRange {

    String message() default "{validation.date.range_error}";
    /**
     * start date
     *
     * @return
     */
    String start();
    /**
     * end date
     *
     * @return
     */
    String end();
    /**
     * use this property to let the start and date be equal
     *
     * @return
     */
    boolean equal() default false;
    Class<? extends Payload>[] payload() default {};
    Class<?>[] groups() default {};
}
