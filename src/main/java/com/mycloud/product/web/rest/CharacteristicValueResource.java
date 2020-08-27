package com.mycloud.product.web.rest;

import com.mycloud.product.service.CharacteristicValueService;
import com.mycloud.product.web.rest.errors.BadRequestAlertException;
import com.mycloud.product.service.dto.CharacteristicValueDTO;

import io.github.jhipster.web.util.HeaderUtil;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing {@link com.mycloud.product.domain.CharacteristicValue}.
 */
@RestController
@RequestMapping("/api")
public class CharacteristicValueResource {

    private final Logger log = LoggerFactory.getLogger(CharacteristicValueResource.class);

    private static final String ENTITY_NAME = "productServiceCharacteristicValue";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final CharacteristicValueService characteristicValueService;

    public CharacteristicValueResource(CharacteristicValueService characteristicValueService) {
        this.characteristicValueService = characteristicValueService;
    }

    /**
     * {@code POST  /characteristic-values} : Create a new characteristicValue.
     *
     * @param characteristicValueDTO the characteristicValueDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new characteristicValueDTO, or with status {@code 400 (Bad Request)} if the characteristicValue has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/characteristic-values")
    public ResponseEntity<CharacteristicValueDTO> createCharacteristicValue(@RequestBody CharacteristicValueDTO characteristicValueDTO) throws URISyntaxException {
        log.debug("REST request to save CharacteristicValue : {}", characteristicValueDTO);
        if (characteristicValueDTO.getId() != null) {
            throw new BadRequestAlertException("A new characteristicValue cannot already have an ID", ENTITY_NAME, "idexists");
        }
        CharacteristicValueDTO result = characteristicValueService.save(characteristicValueDTO);
        return ResponseEntity.created(new URI("/api/characteristic-values/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /characteristic-values} : Updates an existing characteristicValue.
     *
     * @param characteristicValueDTO the characteristicValueDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated characteristicValueDTO,
     * or with status {@code 400 (Bad Request)} if the characteristicValueDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the characteristicValueDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/characteristic-values")
    public ResponseEntity<CharacteristicValueDTO> updateCharacteristicValue(@RequestBody CharacteristicValueDTO characteristicValueDTO) throws URISyntaxException {
        log.debug("REST request to update CharacteristicValue : {}", characteristicValueDTO);
        if (characteristicValueDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        CharacteristicValueDTO result = characteristicValueService.save(characteristicValueDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, characteristicValueDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /characteristic-values} : get all the characteristicValues.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of characteristicValues in body.
     */
    @GetMapping("/characteristic-values")
    public List<CharacteristicValueDTO> getAllCharacteristicValues() {
        log.debug("REST request to get all CharacteristicValues");
        return characteristicValueService.findAll();
    }

    /**
     * {@code GET  /characteristic-values/:id} : get the "id" characteristicValue.
     *
     * @param id the id of the characteristicValueDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the characteristicValueDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/characteristic-values/{id}")
    public ResponseEntity<CharacteristicValueDTO> getCharacteristicValue(@PathVariable Long id) {
        log.debug("REST request to get CharacteristicValue : {}", id);
        Optional<CharacteristicValueDTO> characteristicValueDTO = characteristicValueService.findOne(id);
        return ResponseUtil.wrapOrNotFound(characteristicValueDTO);
    }

    /**
     * {@code DELETE  /characteristic-values/:id} : delete the "id" characteristicValue.
     *
     * @param id the id of the characteristicValueDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/characteristic-values/{id}")
    public ResponseEntity<Void> deleteCharacteristicValue(@PathVariable Long id) {
        log.debug("REST request to delete CharacteristicValue : {}", id);
        characteristicValueService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
