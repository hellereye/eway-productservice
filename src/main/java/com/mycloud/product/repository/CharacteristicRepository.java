package com.mycloud.product.repository;

import com.mycloud.product.domain.Characteristic;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the Characteristic entity.
 */
@SuppressWarnings("unused")
@Repository
public interface CharacteristicRepository extends JpaRepository<Characteristic, Long> {
}
