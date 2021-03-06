package com.mycloud.product.service;

import com.mycloud.product.domain.SKU;
import com.mycloud.product.repository.SKURepository;
import com.mycloud.product.service.dto.SKUDTO;
import com.mycloud.product.service.mapper.SKUMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Service Implementation for managing {@link SKU}.
 */
@Service
@Transactional
public class SKUService {

    private final Logger log = LoggerFactory.getLogger(SKUService.class);

    private final SKURepository sKURepository;

    private final SKUMapper sKUMapper;

    public SKUService(SKURepository sKURepository, SKUMapper sKUMapper) {
        this.sKURepository = sKURepository;
        this.sKUMapper = sKUMapper;
    }

    /**
     * Save a sKU.
     *
     * @param sKUDTO the entity to save.
     * @return the persisted entity.
     */
    public SKUDTO save(SKUDTO sKUDTO) {
        log.debug("Request to save SKU : {}", sKUDTO);
        SKU sKU = sKUMapper.toEntity(sKUDTO);
        sKU = sKURepository.save(sKU);
        return sKUMapper.toDto(sKU);
    }

    /**
     * Get all the sKUS.
     *
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public List<SKUDTO> findAll() {
        log.debug("Request to get all SKUS");
        return sKURepository.findAll().stream()
            .map(sKUMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }


    /**
     * Get one sKU by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<SKUDTO> findOne(Long id) {
        log.debug("Request to get SKU : {}", id);
        return sKURepository.findById(id)
            .map(sKUMapper::toDto);
    }

    /**
     * Delete the sKU by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete SKU : {}", id);
        sKURepository.deleteById(id);
    }
}
