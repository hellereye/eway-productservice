package com.mycloud.product.service.impl;

import com.mycloud.product.service.SpecificationService;
import com.mycloud.product.domain.Specification;
import com.mycloud.product.repository.SpecificationRepository;
import com.mycloud.product.service.dto.SpecificationDTO;
import com.mycloud.product.service.mapper.SpecificationMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link Specification}.
 */
@Service
@Transactional
public class SpecificationServiceImpl implements SpecificationService {

    private final Logger log = LoggerFactory.getLogger(SpecificationServiceImpl.class);

    private final SpecificationRepository specificationRepository;

    private final SpecificationMapper specificationMapper;

    public SpecificationServiceImpl(SpecificationRepository specificationRepository, SpecificationMapper specificationMapper) {
        this.specificationRepository = specificationRepository;
        this.specificationMapper = specificationMapper;
    }

    @Override
    public SpecificationDTO save(SpecificationDTO specificationDTO) {
        log.debug("Request to save Specification : {}", specificationDTO);
        Specification specification = specificationMapper.toEntity(specificationDTO);
        specification = specificationRepository.save(specification);
        return specificationMapper.toDto(specification);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<SpecificationDTO> findAll(Pageable pageable) {
        log.debug("Request to get all Specifications");
        return specificationRepository.findAll(pageable)
            .map(specificationMapper::toDto);
    }


    @Override
    @Transactional(readOnly = true)
    public Optional<SpecificationDTO> findOne(Long id) {
        log.debug("Request to get Specification : {}", id);
        return specificationRepository.findById(id)
            .map(specificationMapper::toDto);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete Specification : {}", id);
        specificationRepository.deleteById(id);
    }
}
