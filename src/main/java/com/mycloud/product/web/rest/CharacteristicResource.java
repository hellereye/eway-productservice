package com.mycloud.product.web.rest;

import com.mycloud.product.service.CharacteristicService;
import com.mycloud.product.web.rest.errors.BadRequestAlertException;
import com.mycloud.product.service.dto.CharacteristicDTO;

import io.github.jhipster.web.util.HeaderUtil;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing {@link com.mycloud.product.domain.Characteristic}.
 */
@RestController
@RequestMapping("/api")
public class CharacteristicResource {

    private final Logger log = LoggerFactory.getLogger(CharacteristicResource.class);

    private static final String ENTITY_NAME = "productServiceCharacteristic";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final CharacteristicService characteristicService;

    public CharacteristicResource(CharacteristicService characteristicService) {
        this.characteristicService = characteristicService;
    }

    /**
     * {@code POST  /characteristics} : Create a new characteristic.
     *
     * @param characteristicDTO the characteristicDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new characteristicDTO, or with status {@code 400 (Bad Request)} if the characteristic has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/characteristics")
    public ResponseEntity<CharacteristicDTO> createCharacteristic(@Valid @RequestBody CharacteristicDTO characteristicDTO) throws URISyntaxException {
        log.debug("REST request to save Characteristic : {}", characteristicDTO);
        if (characteristicDTO.getId() != null) {
            throw new BadRequestAlertException("A new characteristic cannot already have an ID", ENTITY_NAME, "idexists");
        }
        CharacteristicDTO result = characteristicService.save(characteristicDTO);
        return ResponseEntity.created(new URI("/api/characteristics/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /characteristics} : Updates an existing characteristic.
     *
     * @param characteristicDTO the characteristicDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated characteristicDTO,
     * or with status {@code 400 (Bad Request)} if the characteristicDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the characteristicDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/characteristics")
    public ResponseEntity<CharacteristicDTO> updateCharacteristic(@Valid @RequestBody CharacteristicDTO characteristicDTO) throws URISyntaxException {
        log.debug("REST request to update Characteristic : {}", characteristicDTO);
        if (characteristicDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        CharacteristicDTO result = characteristicService.save(characteristicDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, characteristicDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /characteristics} : get all the characteristics.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of characteristics in body.
     */
    @GetMapping("/characteristics")
    public List<CharacteristicDTO> getAllCharacteristics() {
        log.debug("REST request to get all Characteristics");
        return characteristicService.findAll();
    }

    /**
     * {@code GET  /characteristics/:id} : get the "id" characteristic.
     *
     * @param id the id of the characteristicDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the characteristicDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/characteristics/{id}")
    public ResponseEntity<CharacteristicDTO> getCharacteristic(@PathVariable Long id) {
        log.debug("REST request to get Characteristic : {}", id);
        Optional<CharacteristicDTO> characteristicDTO = characteristicService.findOne(id);
        return ResponseUtil.wrapOrNotFound(characteristicDTO);
    }

    /**
     * {@code DELETE  /characteristics/:id} : delete the "id" characteristic.
     *
     * @param id the id of the characteristicDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/characteristics/{id}")
    public ResponseEntity<Void> deleteCharacteristic(@PathVariable Long id) {
        log.debug("REST request to delete Characteristic : {}", id);
        characteristicService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
