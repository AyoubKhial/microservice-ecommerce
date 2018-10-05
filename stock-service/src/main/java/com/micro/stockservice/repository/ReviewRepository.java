package com.micro.stockservice.repository;

import com.micro.stockservice.model.Review;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 * Manage the <code>review</code> entity.
 * <p>
 * By extending <code>JpaRepository</code> we get a bunch of generic CRUD methods to create, update, delete, and find
 * reviews.
 * <p>
 * <code>JpaRepository</code> extends <code>PagingAndSortingRepository</code> which in turn extends CrudRepository
 * interface. So, JpaRepository inherits all the methods from those two interfaces.
 * @author Ayoub Khial
 * @version 1.0
 * @see JpaRepository
 * @see Review
 */
@Repository
public interface ReviewRepository extends JpaRepository<Review, Integer> {

    /**
     * Retrieve all reviews of a specific product based on the product id.
     * <p>
     * Using the Query annotation to select only the fields we need in the response.
     * @param productId the id of the product we want to fetch his reviews.
     * @param pageable the page information.
     * @return A page of reviews.
     */
    @Query("SELECT new Review(r.id, r.title, r.description, r.rating, r.insertedAt, r.updatedAt, r.userId) " +
            "FROM Review r " +
            "WHERE r.product.id = :id")
    Page<Review> findByProductId(@Param("id") int productId, Pageable pageable);
}
