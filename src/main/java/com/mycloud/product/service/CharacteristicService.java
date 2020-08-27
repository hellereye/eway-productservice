package com.mycloud.product.service;

import com.mycloud.product.domain.Characteristic;
import com.mycloud.product.repository.CharacteristicRepository;
import com.mycloud.product.service.dto.CharacteristicDTO;
import com.mycloud.product.service.mapper.CharacteristicMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Service Implementation for managing {@link Characteristic}.
 */
@Service
@Transactional
public class CharacteristicService {

    private final Logger log = LoggerFactory.getLogger(CharacteristicService.class);

    private final CharacteristicRepository characteristicRepository;

    private final CharacteristicMapper characteristicMapper;

    public CharacteristicService(CharacteristicRepository characteristicRepository, CharacteristicMapper characteristicMapper) {
        this.characteristicRepository = characteristicRepository;
        this.characteristicMapper = characteristicMapper;
    }

    /**
     * Save a characteristic.
     *
     * @param characteristicDTO the entity to save.
     * @return the persisted entity.
     */
    public CharacteristicDTO save(CharacteristicDTO characteristicDTO) {
        log.debug("Request to save Characteristic : {}", characteristicDTO);
        Characteristic characteristic = characteristicMapper.toEntity(characteristicDTO);
        characteristic = characteristicRepository.save(characteristic);
        return characteristicMapper.toDto(characteristic);
    }

    /**
     * Get all the characteristics.
     *
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public List<CharacteristicDTO> findAll() {
        log.debug("Request to get all Characteristics");
        return characteristicRepository.findAll().stream()
            .map(characteristicMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }


    /**
     * Get one characteristic by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<CharacteristicDTO> findOne(Long id) {
        log.debug("Request to get Characteristic : {}", id);
        return characteristicRepository.findById(id)
            .map(characteristicMapper::toDto);
    }

    /**
     * Delete the characteristic by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete Characteristic : {}", id);
        characteristicRepository.deleteById(id);
    }
}
