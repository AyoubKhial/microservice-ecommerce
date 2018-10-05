package com.micro.stockservice.util;

import com.micro.stockservice.exception.BadRequestException;
import com.micro.stockservice.model.Review;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

public class ValidatingRequestParametersTest {

    @Rule
    public ExpectedException expectedException = ExpectedException.none();

    @Before
    public void setUp() {
        this.expectedException.expect(BadRequestException.class);
    }

    @Test
    public void parameterShouldBeInteger_NonIntegerParameter_ShouldThrowBadRequestException() {
        this.expectedException.expectMessage("id parameter accept integers only, '2.5' is not an integer.");
        ValidatingRequestParameters.parameterShouldBeInteger("id", "2.5");
    }

    @Test
    public void validatePageSizeParameter_ParameterGreaterThanMax_ShouldThrowBadRequestException() {
        this.expectedException.expectMessage("Page size must not be greater than 50.");
        ValidatingRequestParameters.validatePageSizeParameter("80");
    }

    @Test
    public void validatePageSizeParameter_ParameterLowerThanZero_ShouldThrowBadRequestException() {
        this.expectedException.expectMessage("Page size must not be lower than 0.");
        ValidatingRequestParameters.validatePageSizeParameter("-1");
    }

    @Test
    public void validatePageNumberParameter_ParameterLowerThanZero_ShouldThrowBadRequestException() {
        this.expectedException.expectMessage("Page number cannot be less than zero.");
        ValidatingRequestParameters.validatePageNumberParameter("-1");
    }

    @Test
    public void ValidateSortAndDirection_SortFieldNotExist_ShouldThrowBadRequestException() {
        this.expectedException.expectMessage("You can't sort by 'size', please choose a valid field to sort with.");
        ValidatingRequestParameters.validateSortAndDirection("size","desc", Review.class);
    }

    @Test
    public void ValidateSortAndDirection_InvalidSortDirection_ShouldThrowBadRequestException() {
        this.expectedException.expectMessage("The direction parameter can be 'asc' or 'desc'.");
        ValidatingRequestParameters.validateSortAndDirection("rating","fromTop", Review.class);
    }
}
