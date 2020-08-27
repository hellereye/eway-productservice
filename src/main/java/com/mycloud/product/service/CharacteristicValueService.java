package com.mycloud.product.service;

import com.mycloud.product.domain.CharacteristicValue;
import com.mycloud.product.repository.CharacteristicValueRepository;
import com.mycloud.product.service.dto.CharacteristicValueDTO;
import com.mycloud.product.service.mapper.CharacteristicValueMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Service Implementation for managing {@link CharacteristicValue}.
 */
@Service
@Transactional
public class CharacteristicValueService {

    private final Logger log = LoggerFactory.getLogger(CharacteristicValueService.class);

    private final CharacteristicValueRepository characteristicValueRepository;

    private final CharacteristicValueMapper characteristicValueMapper;

    public CharacteristicValueService(CharacteristicValueRepository characteristicValueRepository, CharacteristicValueMapper characteristicValueMapper) {
        this.characteristicValueRepository = characteristicValueRepository;
        this.characteristicValueMapper = characteristicValueMapper;
    }

    /**
     * Save a characteristicValue.
     *
     * @param characteristicValueDTO the entity to save.
     * @return the persisted entity.
     */
    public CharacteristicValueDTO save(CharacteristicValueDTO characteristicValueDTO) {
        log.debug("Request to save CharacteristicValue : {}", characteristicValueDTO);
        CharacteristicValue characteristicValue = characteristicValueMapper.toEntity(characteristicValueDTO);
        characteristicValue = characteristicValueRepository.save(characteristicValue);
        return characteristicValueMapper.toDto(characteristicValue);
    }

    /**
     * Get all the characteristicValues.
     *
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public List<CharacteristicValueDTO> findAll() {
        log.debug("Request to get all CharacteristicValues");
        return characteristicValueRepository.findAll().stream()
            .map(characteristicValueMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }


    /**
     * Get one characteristicValue by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<CharacteristicValueDTO> findOne(Long id) {
        log.debug("Request to get CharacteristicValue : {}", id);
        return characteristicValueRepository.findById(id)
            .map(characteristicValueMapper::toDto);
    }

    /**
     * Delete the characteristicValue by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete CharacteristicValue : {}", id);
        characteristicValueRepository.deleteById(id);
    }
}
