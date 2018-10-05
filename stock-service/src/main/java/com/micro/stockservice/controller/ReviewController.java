package com.micro.stockservice.controller;

import com.micro.stockservice.annotation.RestControllerApi;
import com.micro.stockservice.model.Review;
import com.micro.stockservice.service.ReviewService;
import com.micro.stockservice.util.PagedResponse;
import com.micro.stockservice.util.RestResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import static com.micro.stockservice.util.ApplicationConstants.DEFAULT_PAGE_NUMBER;
import static com.micro.stockservice.util.ApplicationConstants.DEFAULT_PAGE_SIZE;

/**
 * Movie controller that expose the movie related endpoints.
 * <p>
 * We're using our custom annotation <code>RestControllerApi</code> which using <code>RestController</code>
 * annotation and <b>"/api"</b> as base path.
 * @author Ayoub Khial
 * @version 1.0
 * @see ReviewService
 * @see RestControllerApi
 */
@RestControllerApi
public class ReviewController {

    private ReviewService reviewService;

    @Autowired
    public ReviewController(ReviewService reviewService) {
        this.reviewService = reviewService;
    }

    @GetMapping("product/{id}/reviews")
    public ResponseEntity<RestResponse<PagedResponse<Review>>> getAllReviewsByProduct(
            @PathVariable String id,
            @RequestParam(value = "page", defaultValue = DEFAULT_PAGE_NUMBER) String page,
            @RequestParam(value = "size", defaultValue = DEFAULT_PAGE_SIZE) String size,
            @RequestParam(value = "sort", defaultValue = "insertedAt") String sort,
            @RequestParam(value = "direction", defaultValue = "desc") String direction) {

        RestResponse<PagedResponse<Review>> reviewRestResponse = this.reviewService.getAllReviewsByProduct(id, page, size, sort, direction);
        return ResponseEntity.ok(reviewRestResponse);
    }
}
