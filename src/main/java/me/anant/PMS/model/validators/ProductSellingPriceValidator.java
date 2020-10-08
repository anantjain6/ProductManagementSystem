package me.anant.PMS.model.validators;


import me.anant.PMS.model.Product;
import me.anant.PMS.model.constraints.ValidProductSellingPrice;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class ProductSellingPriceValidator implements ConstraintValidator<ValidProductSellingPrice, Product> {
    @Override
    public boolean isValid(Product product, ConstraintValidatorContext context) {
        return product.getProductSellingPrice() <= product.getProductPrice();
    }
}
