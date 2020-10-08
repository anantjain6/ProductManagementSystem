package me.anant.PMS.model.constraints;

import me.anant.PMS.model.validators.ProductSellingPriceValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = ProductSellingPriceValidator.class)
@Documented
public @interface ValidProductSellingPrice {
    String message() default
            "Product selling price must be less or equal that product price";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
