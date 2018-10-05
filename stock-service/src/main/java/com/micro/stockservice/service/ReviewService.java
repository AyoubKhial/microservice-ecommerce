package com.micro.stockservice.service;

import com.micro.stockservice.model.Review;
import com.micro.stockservice.util.PagedResponse;
import com.micro.stockservice.util.RestResponse;

/**
 * Mostly used as a facade for all <code>Review</code> controller.
 * @author Ayoub Khial
 * @version 1.0
 * @see Review
 */
public interface ReviewService {

    /**
     * @param productId the id of the product we want to fetch his reviews.
     * @param page the number of the page.
     * @param size the size of each page.
     * @param sort the field we want to sort the page with.
     * @param direction the direction of sorting, can be either <b>desc</b> or b>asc</b>.
     * @return a rest response of paged reviews.
     */
    RestResponse<PagedResponse<Review>> getAllReviewsByProduct(String productId, String page, String size, String sort,
                                                               String direction);
}
