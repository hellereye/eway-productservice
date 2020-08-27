package com.mycloud.product.repository;

import com.mycloud.product.domain.Specification;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the Specification entity.
 */
@SuppressWarnings("unused")
@Repository
public interface SpecificationRepository extends JpaRepository<Specification, Long> {
}
