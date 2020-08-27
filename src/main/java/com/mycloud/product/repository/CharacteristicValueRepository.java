package com.mycloud.product.repository;

import com.mycloud.product.domain.CharacteristicValue;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the CharacteristicValue entity.
 */
@SuppressWarnings("unused")
@Repository
public interface CharacteristicValueRepository extends JpaRepository<CharacteristicValue, Long> {
}
