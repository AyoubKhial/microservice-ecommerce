package com.micro.stockservice.service.impl;

import com.micro.stockservice.DummyData;
import com.micro.stockservice.model.Review;
import com.micro.stockservice.repository.ReviewRepository;
import com.micro.stockservice.util.PagedResponse;
import com.micro.stockservice.util.RestResponse;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;
import org.mockito.quality.Strictness;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;

public class ReviewServiceImplTest {

    @Rule
    public MockitoRule mockitoRule = MockitoJUnit.rule().strictness(Strictness.STRICT_STUBS);

    @Mock
    private ReviewRepository reviewRepository;

    @InjectMocks
    private ReviewServiceImpl reviewService;

    private Page<Review> reviewPage;
    private RestResponse<PagedResponse<Review>> reviewRestResponse;

    @Before
    public void setUp() {
        this.reviewPage = DummyData.dummyReviewPage();
        this.reviewRestResponse = DummyData.dummyReviewRestResponse();
    }

    @Test
    public void getAllReviewsByProduct_CriteriaGiven_ShouldReturnRestResponse() {
        when(this.reviewRepository.findByProductId(anyInt(), any(PageRequest.class))).thenReturn(this.reviewPage);

        RestResponse<PagedResponse<Review>> actualResult = this.reviewService.getAllReviewsByProduct("1","0", "20", "id","asc");

        assertThat("The actual result is different than the expected.", actualResult, is(equalTo(this.reviewRestResponse)));

    }
}
