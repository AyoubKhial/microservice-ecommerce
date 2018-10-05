package com.micro.stockservice;

import com.micro.stockservice.model.Review;
import com.micro.stockservice.util.HTTPCode;
import com.micro.stockservice.util.PagedResponse;
import com.micro.stockservice.util.RestResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;

import java.util.Collections;
import java.util.Date;

public interface DummyData {

    static Review dummyReview() {
        return new Review(1, "Good product", "This is a good product", 8, new Date(1970), new Date(1970), 1);
    }

    static Page<Review> dummyReviewPage() {
        return new PageImpl<>(Collections.singletonList(dummyReview()));
    }

    static PagedResponse<Review> dummyReviewPagedResponse() {
        return new PagedResponse<>(dummyReviewPage().getContent(), dummyReviewPage().getNumber(), dummyReviewPage().getSize(),
                dummyReviewPage().getTotalElements(), dummyReviewPage().getTotalPages(), dummyReviewPage().isLast());
    }

    static RestResponse<PagedResponse<Review>> dummyReviewRestResponse() {
        String message = "The request was executed successfully.";
        return new RestResponse<>(HTTPCode.OK.getValue(), HTTPCode.OK.getKey(), message, dummyReviewPagedResponse());

    }
}
