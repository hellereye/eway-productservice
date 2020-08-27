package com.mycloud.product.web.rest;

import com.mycloud.product.service.SKUService;
import com.mycloud.product.web.rest.errors.BadRequestAlertException;
import com.mycloud.product.service.dto.SKUDTO;

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
 * REST controller for managing {@link com.mycloud.product.domain.SKU}.
 */
@RestController
@RequestMapping("/api")
public class SKUResource {

    private final Logger log = LoggerFactory.getLogger(SKUResource.class);

    private static final String ENTITY_NAME = "productServiceSku";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final SKUService sKUService;

    public SKUResource(SKUService sKUService) {
        this.sKUService = sKUService;
    }

    /**
     * {@code POST  /skus} : Create a new sKU.
     *
     * @param sKUDTO the sKUDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new sKUDTO, or with status {@code 400 (Bad Request)} if the sKU has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/skus")
    public ResponseEntity<SKUDTO> createSKU(@Valid @RequestBody SKUDTO sKUDTO) throws URISyntaxException {
        log.debug("REST request to save SKU : {}", sKUDTO);
        if (sKUDTO.getId() != null) {
            throw new BadRequestAlertException("A new sKU cannot already have an ID", ENTITY_NAME, "idexists");
        }
        SKUDTO result = sKUService.save(sKUDTO);
        return ResponseEntity.created(new URI("/api/skus/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /skus} : Updates an existing sKU.
     *
     * @param sKUDTO the sKUDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated sKUDTO,
     * or with status {@code 400 (Bad Request)} if the sKUDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the sKUDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/skus")
    public ResponseEntity<SKUDTO> updateSKU(@Valid @RequestBody SKUDTO sKUDTO) throws URISyntaxException {
        log.debug("REST request to update SKU : {}", sKUDTO);
        if (sKUDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        SKUDTO result = sKUService.save(sKUDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, sKUDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /skus} : get all the sKUS.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of sKUS in body.
     */
    @GetMapping("/skus")
    public List<SKUDTO> getAllSKUS() {
        log.debug("REST request to get all SKUS");
        return sKUService.findAll();
    }

    /**
     * {@code GET  /skus/:id} : get the "id" sKU.
     *
     * @param id the id of the sKUDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the sKUDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/skus/{id}")
    public ResponseEntity<SKUDTO> getSKU(@PathVariable Long id) {
        log.debug("REST request to get SKU : {}", id);
        Optional<SKUDTO> sKUDTO = sKUService.findOne(id);
        return ResponseUtil.wrapOrNotFound(sKUDTO);
    }

    /**
     * {@code DELETE  /skus/:id} : delete the "id" sKU.
     *
     * @param id the id of the sKUDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/skus/{id}")
    public ResponseEntity<Void> deleteSKU(@PathVariable Long id) {
        log.debug("REST request to delete SKU : {}", id);
        sKUService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
