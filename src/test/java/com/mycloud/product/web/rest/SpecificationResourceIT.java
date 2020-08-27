package com.mycloud.product.web.rest;

import com.mycloud.product.ProductServiceApp;
import com.mycloud.product.domain.Specification;
import com.mycloud.product.repository.SpecificationRepository;
import com.mycloud.product.service.SpecificationService;
import com.mycloud.product.service.dto.SpecificationDTO;
import com.mycloud.product.service.mapper.SpecificationMapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;
import javax.persistence.EntityManager;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Integration tests for the {@link SpecificationResource} REST controller.
 */
@SpringBootTest(classes = ProductServiceApp.class)
@AutoConfigureMockMvc
@WithMockUser
public class SpecificationResourceIT {

    private static final String DEFAULT_NAME = "AAAAAAAAAA";
    private static final String UPDATED_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_DESC = "AAAAAAAAAA";
    private static final String UPDATED_DESC = "BBBBBBBBBB";

    @Autowired
    private SpecificationRepository specificationRepository;

    @Autowired
    private SpecificationMapper specificationMapper;

    @Autowired
    private SpecificationService specificationService;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restSpecificationMockMvc;

    private Specification specification;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Specification createEntity(EntityManager em) {
        Specification specification = new Specification()
            .name(DEFAULT_NAME)
            .desc(DEFAULT_DESC);
        return specification;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Specification createUpdatedEntity(EntityManager em) {
        Specification specification = new Specification()
            .name(UPDATED_NAME)
            .desc(UPDATED_DESC);
        return specification;
    }

    @BeforeEach
    public void initTest() {
        specification = createEntity(em);
    }

    @Test
    @Transactional
    public void createSpecification() throws Exception {
        int databaseSizeBeforeCreate = specificationRepository.findAll().size();
        // Create the Specification
        SpecificationDTO specificationDTO = specificationMapper.toDto(specification);
        restSpecificationMockMvc.perform(post("/api/specifications")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(specificationDTO)))
            .andExpect(status().isCreated());

        // Validate the Specification in the database
        List<Specification> specificationList = specificationRepository.findAll();
        assertThat(specificationList).hasSize(databaseSizeBeforeCreate + 1);
        Specification testSpecification = specificationList.get(specificationList.size() - 1);
        assertThat(testSpecification.getName()).isEqualTo(DEFAULT_NAME);
        assertThat(testSpecification.getDesc()).isEqualTo(DEFAULT_DESC);
    }

    @Test
    @Transactional
    public void createSpecificationWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = specificationRepository.findAll().size();

        // Create the Specification with an existing ID
        specification.setId(1L);
        SpecificationDTO specificationDTO = specificationMapper.toDto(specification);

        // An entity with an existing ID cannot be created, so this API call must fail
        restSpecificationMockMvc.perform(post("/api/specifications")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(specificationDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Specification in the database
        List<Specification> specificationList = specificationRepository.findAll();
        assertThat(specificationList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void checkNameIsRequired() throws Exception {
        int databaseSizeBeforeTest = specificationRepository.findAll().size();
        // set the field null
        specification.setName(null);

        // Create the Specification, which fails.
        SpecificationDTO specificationDTO = specificationMapper.toDto(specification);


        restSpecificationMockMvc.perform(post("/api/specifications")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(specificationDTO)))
            .andExpect(status().isBadRequest());

        List<Specification> specificationList = specificationRepository.findAll();
        assertThat(specificationList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllSpecifications() throws Exception {
        // Initialize the database
        specificationRepository.saveAndFlush(specification);

        // Get all the specificationList
        restSpecificationMockMvc.perform(get("/api/specifications?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(specification.getId().intValue())))
            .andExpect(jsonPath("$.[*].name").value(hasItem(DEFAULT_NAME)))
            .andExpect(jsonPath("$.[*].desc").value(hasItem(DEFAULT_DESC)));
    }
    
    @Test
    @Transactional
    public void getSpecification() throws Exception {
        // Initialize the database
        specificationRepository.saveAndFlush(specification);

        // Get the specification
        restSpecificationMockMvc.perform(get("/api/specifications/{id}", specification.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(specification.getId().intValue()))
            .andExpect(jsonPath("$.name").value(DEFAULT_NAME))
            .andExpect(jsonPath("$.desc").value(DEFAULT_DESC));
    }
    @Test
    @Transactional
    public void getNonExistingSpecification() throws Exception {
        // Get the specification
        restSpecificationMockMvc.perform(get("/api/specifications/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateSpecification() throws Exception {
        // Initialize the database
        specificationRepository.saveAndFlush(specification);

        int databaseSizeBeforeUpdate = specificationRepository.findAll().size();

        // Update the specification
        Specification updatedSpecification = specificationRepository.findById(specification.getId()).get();
        // Disconnect from session so that the updates on updatedSpecification are not directly saved in db
        em.detach(updatedSpecification);
        updatedSpecification
            .name(UPDATED_NAME)
            .desc(UPDATED_DESC);
        SpecificationDTO specificationDTO = specificationMapper.toDto(updatedSpecification);

        restSpecificationMockMvc.perform(put("/api/specifications")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(specificationDTO)))
            .andExpect(status().isOk());

        // Validate the Specification in the database
        List<Specification> specificationList = specificationRepository.findAll();
        assertThat(specificationList).hasSize(databaseSizeBeforeUpdate);
        Specification testSpecification = specificationList.get(specificationList.size() - 1);
        assertThat(testSpecification.getName()).isEqualTo(UPDATED_NAME);
        assertThat(testSpecification.getDesc()).isEqualTo(UPDATED_DESC);
    }

    @Test
    @Transactional
    public void updateNonExistingSpecification() throws Exception {
        int databaseSizeBeforeUpdate = specificationRepository.findAll().size();

        // Create the Specification
        SpecificationDTO specificationDTO = specificationMapper.toDto(specification);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restSpecificationMockMvc.perform(put("/api/specifications")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(specificationDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Specification in the database
        List<Specification> specificationList = specificationRepository.findAll();
        assertThat(specificationList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteSpecification() throws Exception {
        // Initialize the database
        specificationRepository.saveAndFlush(specification);

        int databaseSizeBeforeDelete = specificationRepository.findAll().size();

        // Delete the specification
        restSpecificationMockMvc.perform(delete("/api/specifications/{id}", specification.getId())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<Specification> specificationList = specificationRepository.findAll();
        assertThat(specificationList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
