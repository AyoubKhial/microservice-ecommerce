package com.micro.stockservice.service.impl;

import com.micro.stockservice.model.Review;
import com.micro.stockservice.repository.ReviewRepository;
import com.micro.stockservice.service.ReviewService;
import com.micro.stockservice.util.HTTPCode;
import com.micro.stockservice.util.PagedResponse;
import com.micro.stockservice.util.RestResponse;
import com.micro.stockservice.util.ValidatingRequestParameters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;


/**
 * {@inheritDoc}
 */
@Service
public class ReviewServiceImpl implements ReviewService {

    private ReviewRepository reviewRepository;

    @Autowired
    public ReviewServiceImpl(ReviewRepository reviewRepository) {
        this.reviewRepository = reviewRepository;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public RestResponse<PagedResponse<Review>> getAllReviewsByProduct(String productId, String page, String size,
                                                                      String sort, String direction) {
        // validate the request parameters
        ValidatingRequestParameters.parameterShouldBeInteger("id", productId);
        ValidatingRequestParameters.parameterShouldBeInteger("page", page);
        ValidatingRequestParameters.parameterShouldBeInteger("size", size);
        ValidatingRequestParameters.validatePageNumberParameter(page);
        ValidatingRequestParameters.validatePageSizeParameter(size);
        ValidatingRequestParameters.validateSortAndDirection(sort, direction, Review.class);

        // get the sort direction from the arguments and convert it to Sort.Direction
        Sort.Direction sortDirection;
        if(direction.equalsIgnoreCase("asc")) {
            sortDirection = Sort.Direction.ASC;
        }
        else {
            sortDirection = Sort.Direction.DESC;
        }

        // create a pageable from the arguments
        Pageable pageable = PageRequest.of(Integer.parseInt(page), Integer.parseInt(size), sortDirection, sort);

        // get the reviews as a page from the repository
        Page<Review> reviewPage = this.reviewRepository.findByProductId(Integer.parseInt(productId), pageable);

        // return the review paged response
        PagedResponse<Review> reviewPagedResponse = new PagedResponse<>(reviewPage.getContent(), reviewPage.getNumber(),
                reviewPage.getSize(), reviewPage.getTotalElements(), reviewPage.getTotalPages(), reviewPage.isLast());

        // response message
        String message = "The request was executed successfully.";

        return new RestResponse<>(HTTPCode.OK.getValue(), HTTPCode.OK.getKey(), message, reviewPagedResponse);
    }
}
