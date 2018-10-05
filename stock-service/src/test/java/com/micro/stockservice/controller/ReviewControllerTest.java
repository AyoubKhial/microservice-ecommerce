package com.micro.stockservice.controller;

import com.micro.stockservice.DummyData;
import com.micro.stockservice.model.Review;
import com.micro.stockservice.service.ReviewService;
import com.micro.stockservice.util.PagedResponse;
import com.micro.stockservice.util.RestResponse;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(ReviewController.class)
public class ReviewControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ReviewService reviewService;

    private RestResponse<PagedResponse<Review>> reviewRestResponse;

    @Before
    public void setUp() {
        this.reviewRestResponse = DummyData.dummyReviewRestResponse();
    }
    @Test
    public void getAllReviewsByProduct_CriteriaGiven_ShouldReturnHttpResponse() throws Exception {
        given(this.reviewService.getAllReviewsByProduct(anyString(), anyString(), anyString(), anyString(), anyString()))
                .willReturn(this.reviewRestResponse);

        mockMvc.perform(get("/api/product/1/reviews").accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.status", is("OK")))
                .andExpect(jsonPath("$.code", is(200)))
                .andExpect(jsonPath("$.message", is("The request was executed successfully.")))
                .andExpect(jsonPath("$.result.content", hasSize(1)))
                .andExpect(jsonPath("$.result.content[0].id", is(1)))
                .andExpect(jsonPath("$.result.content[0].title", is("Good product")))
                .andExpect(jsonPath("$.result.content[0].description", is("This is a good product")))
                .andExpect(jsonPath("$.result.content[0].rating", is(8)))
                .andExpect(jsonPath("$.result.content[0].insertedAt", is("01-01-1970")))
                .andExpect(jsonPath("$.result.content[0].updatedAt", is("01-01-1970")))
                .andExpect(jsonPath("$.result.content[0].userId", is(1)));

        verify(this.reviewService, times(1))
                .getAllReviewsByProduct(anyString(), anyString(), anyString(), anyString(), anyString());
        verifyNoMoreInteractions(this.reviewService);
    }
}
