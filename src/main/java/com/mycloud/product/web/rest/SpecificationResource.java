package com.mycloud.product.web.rest;

import com.mycloud.product.service.SpecificationService;
import com.mycloud.product.web.rest.errors.BadRequestAlertException;
import com.mycloud.product.service.dto.SpecificationDTO;

import io.github.jhipster.web.util.HeaderUtil;
import io.github.jhipster.web.util.PaginationUtil;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing {@link com.mycloud.product.domain.Specification}.
 */
@RestController
@RequestMapping("/api")
public class SpecificationResource {

    private final Logger log = LoggerFactory.getLogger(SpecificationResource.class);

    private static final String ENTITY_NAME = "productServiceSpecification";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final SpecificationService specificationService;

    public SpecificationResource(SpecificationService specificationService) {
        this.specificationService = specificationService;
    }

    /**
     * {@code POST  /specifications} : Create a new specification.
     *
     * @param specificationDTO the specificationDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new specificationDTO, or with status {@code 400 (Bad Request)} if the specification has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/specifications")
    public ResponseEntity<SpecificationDTO> createSpecification(@Valid @RequestBody SpecificationDTO specificationDTO) throws URISyntaxException {
        log.debug("REST request to save Specification : {}", specificationDTO);
        if (specificationDTO.getId() != null) {
            throw new BadRequestAlertException("A new specification cannot already have an ID", ENTITY_NAME, "idexists");
        }
        SpecificationDTO result = specificationService.save(specificationDTO);
        return ResponseEntity.created(new URI("/api/specifications/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /specifications} : Updates an existing specification.
     *
     * @param specificationDTO the specificationDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated specificationDTO,
     * or with status {@code 400 (Bad Request)} if the specificationDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the specificationDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/specifications")
    public ResponseEntity<SpecificationDTO> updateSpecification(@Valid @RequestBody SpecificationDTO specificationDTO) throws URISyntaxException {
        log.debug("REST request to update Specification : {}", specificationDTO);
        if (specificationDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        SpecificationDTO result = specificationService.save(specificationDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, specificationDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /specifications} : get all the specifications.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of specifications in body.
     */
    @GetMapping("/specifications")
    public ResponseEntity<List<SpecificationDTO>> getAllSpecifications(Pageable pageable) {
        log.debug("REST request to get a page of Specifications");
        Page<SpecificationDTO> page = specificationService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /specifications/:id} : get the "id" specification.
     *
     * @param id the id of the specificationDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the specificationDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/specifications/{id}")
    public ResponseEntity<SpecificationDTO> getSpecification(@PathVariable Long id) {
        log.debug("REST request to get Specification : {}", id);
        Optional<SpecificationDTO> specificationDTO = specificationService.findOne(id);
        return ResponseUtil.wrapOrNotFound(specificationDTO);
    }

    /**
     * {@code DELETE  /specifications/:id} : delete the "id" specification.
     *
     * @param id the id of the specificationDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/specifications/{id}")
    public ResponseEntity<Void> deleteSpecification(@PathVariable Long id) {
        log.debug("REST request to delete Specification : {}", id);
        specificationService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
