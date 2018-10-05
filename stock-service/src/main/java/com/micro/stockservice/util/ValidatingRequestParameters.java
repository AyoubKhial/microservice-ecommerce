package com.micro.stockservice.util;

import com.micro.stockservice.exception.BadRequestException;

import java.lang.reflect.Field;
import java.util.List;

import static com.micro.stockservice.util.ApplicationConstants.MAX_PAGE_SIZE;

/**
 * This class check the validation of the parameters given in each request.
 * @author Ayoub Khial
 * @version 1.0
 */

public interface ValidatingRequestParameters {

    static void parameterShouldBeInteger(String parameterName, String parameterValue) {
        try {
            Integer.parseInt(parameterValue);
        } catch (NumberFormatException ex) {
            throw new BadRequestException(parameterName + " parameter accept integers only, '" + parameterValue + "' is not an integer.");
        }
    }

    static void validatePageSizeParameter(String size) {
        int sizeInt = Integer.parseInt(size);
        if(sizeInt > MAX_PAGE_SIZE ) {
            throw new BadRequestException("Page size must not be greater than " + MAX_PAGE_SIZE + ".");
        }
        if(sizeInt < 0) {
            throw new BadRequestException("Page size must not be lower than 0.");
        }
    }

    static void validatePageNumberParameter(String page) {
        int PageInt = Integer.parseInt(page);
        if(PageInt < 0) {
            throw new BadRequestException("Page number cannot be less than zero.");
        }
    }


    static void validateSortAndDirection(String sort, String direction, Class<?> clazz) {
        boolean fieldFound = false;
        for(Field field: clazz.getDeclaredFields()) {
            if(field.getName().equals(sort) && field.getType() != List.class) {
                fieldFound = true;
                break;
            }
        }
        if(!fieldFound) {
            for (Field field : clazz.getSuperclass().getDeclaredFields()) {
                if (field.getName().equals(sort) && field.getType() != List.class) {
                    fieldFound = true;
                    break;
                }
            }
        }

        if(!fieldFound) {
            throw new BadRequestException("You can't sort by '" + sort + "', please choose a valid field to sort with.");
        }

        if(!direction.equalsIgnoreCase("asc") && !direction.equalsIgnoreCase("desc")) {
            throw new BadRequestException("The direction parameter can be 'asc' or 'desc'.");
        }
    }
}
