package com.mycloud.product.service;

import com.mycloud.product.service.dto.SpecificationDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing {@link com.mycloud.product.domain.Specification}.
 */
public interface SpecificationService {

    /**
     * Save a specification.
     *
     * @param specificationDTO the entity to save.
     * @return the persisted entity.
     */
    SpecificationDTO save(SpecificationDTO specificationDTO);

    /**
     * Get all the specifications.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<SpecificationDTO> findAll(Pageable pageable);


    /**
     * Get the "id" specification.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<SpecificationDTO> findOne(Long id);

    /**
     * Delete the "id" specification.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
