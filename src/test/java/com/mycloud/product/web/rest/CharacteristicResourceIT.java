package com.mycloud.product.web.rest;

import com.mycloud.product.ProductServiceApp;
import com.mycloud.product.domain.Characteristic;
import com.mycloud.product.repository.CharacteristicRepository;
import com.mycloud.product.service.CharacteristicService;
import com.mycloud.product.service.dto.CharacteristicDTO;
import com.mycloud.product.service.mapper.CharacteristicMapper;

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
 * Integration tests for the {@link CharacteristicResource} REST controller.
 */
@SpringBootTest(classes = ProductServiceApp.class)
@AutoConfigureMockMvc
@WithMockUser
public class CharacteristicResourceIT {

    private static final String DEFAULT_NAME = "AAAAAAAAAA";
    private static final String UPDATED_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_DECS = "AAAAAAAAAA";
    private static final String UPDATED_DECS = "BBBBBBBBBB";

    private static final Boolean DEFAULT_UNIQUED = false;
    private static final Boolean UPDATED_UNIQUED = true;

    private static final String DEFAULT_VALUE_TYPE = "AAAAAAAAAA";
    private static final String UPDATED_VALUE_TYPE = "BBBBBBBBBB";

    private static final Integer DEFAULT_MIN_CARDINALITY = 1;
    private static final Integer UPDATED_MIN_CARDINALITY = 2;

    private static final Integer DEFAULT_MAX_CARDINALITY = 1;
    private static final Integer UPDATED_MAX_CARDINALITY = 2;

    private static final Boolean DEFAULT_EXTENSIBLE = false;
    private static final Boolean UPDATED_EXTENSIBLE = true;

    @Autowired
    private CharacteristicRepository characteristicRepository;

    @Autowired
    private CharacteristicMapper characteristicMapper;

    @Autowired
    private CharacteristicService characteristicService;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restCharacteristicMockMvc;

    private Characteristic characteristic;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Characteristic createEntity(EntityManager em) {
        Characteristic characteristic = new Characteristic()
            .name(DEFAULT_NAME)
            .decs(DEFAULT_DECS)
            .uniqued(DEFAULT_UNIQUED)
            .valueType(DEFAULT_VALUE_TYPE)
            .minCardinality(DEFAULT_MIN_CARDINALITY)
            .maxCardinality(DEFAULT_MAX_CARDINALITY)
            .extensible(DEFAULT_EXTENSIBLE);
        return characteristic;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Characteristic createUpdatedEntity(EntityManager em) {
        Characteristic characteristic = new Characteristic()
            .name(UPDATED_NAME)
            .decs(UPDATED_DECS)
            .uniqued(UPDATED_UNIQUED)
            .valueType(UPDATED_VALUE_TYPE)
            .minCardinality(UPDATED_MIN_CARDINALITY)
            .maxCardinality(UPDATED_MAX_CARDINALITY)
            .extensible(UPDATED_EXTENSIBLE);
        return characteristic;
    }

    @BeforeEach
    public void initTest() {
        characteristic = createEntity(em);
    }

    @Test
    @Transactional
    public void createCharacteristic() throws Exception {
        int databaseSizeBeforeCreate = characteristicRepository.findAll().size();
        // Create the Characteristic
        CharacteristicDTO characteristicDTO = characteristicMapper.toDto(characteristic);
        restCharacteristicMockMvc.perform(post("/api/characteristics")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(characteristicDTO)))
            .andExpect(status().isCreated());

        // Validate the Characteristic in the database
        List<Characteristic> characteristicList = characteristicRepository.findAll();
        assertThat(characteristicList).hasSize(databaseSizeBeforeCreate + 1);
        Characteristic testCharacteristic = characteristicList.get(characteristicList.size() - 1);
        assertThat(testCharacteristic.getName()).isEqualTo(DEFAULT_NAME);
        assertThat(testCharacteristic.getDecs()).isEqualTo(DEFAULT_DECS);
        assertThat(testCharacteristic.isUniqued()).isEqualTo(DEFAULT_UNIQUED);
        assertThat(testCharacteristic.getValueType()).isEqualTo(DEFAULT_VALUE_TYPE);
        assertThat(testCharacteristic.getMinCardinality()).isEqualTo(DEFAULT_MIN_CARDINALITY);
        assertThat(testCharacteristic.getMaxCardinality()).isEqualTo(DEFAULT_MAX_CARDINALITY);
        assertThat(testCharacteristic.isExtensible()).isEqualTo(DEFAULT_EXTENSIBLE);
    }

    @Test
    @Transactional
    public void createCharacteristicWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = characteristicRepository.findAll().size();

        // Create the Characteristic with an existing ID
        characteristic.setId(1L);
        CharacteristicDTO characteristicDTO = characteristicMapper.toDto(characteristic);

        // An entity with an existing ID cannot be created, so this API call must fail
        restCharacteristicMockMvc.perform(post("/api/characteristics")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(characteristicDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Characteristic in the database
        List<Characteristic> characteristicList = characteristicRepository.findAll();
        assertThat(characteristicList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void checkNameIsRequired() throws Exception {
        int databaseSizeBeforeTest = characteristicRepository.findAll().size();
        // set the field null
        characteristic.setName(null);

        // Create the Characteristic, which fails.
        CharacteristicDTO characteristicDTO = characteristicMapper.toDto(characteristic);


        restCharacteristicMockMvc.perform(post("/api/characteristics")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(characteristicDTO)))
            .andExpect(status().isBadRequest());

        List<Characteristic> characteristicList = characteristicRepository.findAll();
        assertThat(characteristicList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllCharacteristics() throws Exception {
        // Initialize the database
        characteristicRepository.saveAndFlush(characteristic);

        // Get all the characteristicList
        restCharacteristicMockMvc.perform(get("/api/characteristics?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(characteristic.getId().intValue())))
            .andExpect(jsonPath("$.[*].name").value(hasItem(DEFAULT_NAME)))
            .andExpect(jsonPath("$.[*].decs").value(hasItem(DEFAULT_DECS)))
            .andExpect(jsonPath("$.[*].uniqued").value(hasItem(DEFAULT_UNIQUED.booleanValue())))
            .andExpect(jsonPath("$.[*].valueType").value(hasItem(DEFAULT_VALUE_TYPE)))
            .andExpect(jsonPath("$.[*].minCardinality").value(hasItem(DEFAULT_MIN_CARDINALITY)))
            .andExpect(jsonPath("$.[*].maxCardinality").value(hasItem(DEFAULT_MAX_CARDINALITY)))
            .andExpect(jsonPath("$.[*].extensible").value(hasItem(DEFAULT_EXTENSIBLE.booleanValue())));
    }
    
    @Test
    @Transactional
    public void getCharacteristic() throws Exception {
        // Initialize the database
        characteristicRepository.saveAndFlush(characteristic);

        // Get the characteristic
        restCharacteristicMockMvc.perform(get("/api/characteristics/{id}", characteristic.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(characteristic.getId().intValue()))
            .andExpect(jsonPath("$.name").value(DEFAULT_NAME))
            .andExpect(jsonPath("$.decs").value(DEFAULT_DECS))
            .andExpect(jsonPath("$.uniqued").value(DEFAULT_UNIQUED.booleanValue()))
            .andExpect(jsonPath("$.valueType").value(DEFAULT_VALUE_TYPE))
            .andExpect(jsonPath("$.minCardinality").value(DEFAULT_MIN_CARDINALITY))
            .andExpect(jsonPath("$.maxCardinality").value(DEFAULT_MAX_CARDINALITY))
            .andExpect(jsonPath("$.extensible").value(DEFAULT_EXTENSIBLE.booleanValue()));
    }
    @Test
    @Transactional
    public void getNonExistingCharacteristic() throws Exception {
        // Get the characteristic
        restCharacteristicMockMvc.perform(get("/api/characteristics/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateCharacteristic() throws Exception {
        // Initialize the database
        characteristicRepository.saveAndFlush(characteristic);

        int databaseSizeBeforeUpdate = characteristicRepository.findAll().size();

        // Update the characteristic
        Characteristic updatedCharacteristic = characteristicRepository.findById(characteristic.getId()).get();
        // Disconnect from session so that the updates on updatedCharacteristic are not directly saved in db
        em.detach(updatedCharacteristic);
        updatedCharacteristic
            .name(UPDATED_NAME)
            .decs(UPDATED_DECS)
            .uniqued(UPDATED_UNIQUED)
            .valueType(UPDATED_VALUE_TYPE)
            .minCardinality(UPDATED_MIN_CARDINALITY)
            .maxCardinality(UPDATED_MAX_CARDINALITY)
            .extensible(UPDATED_EXTENSIBLE);
        CharacteristicDTO characteristicDTO = characteristicMapper.toDto(updatedCharacteristic);

        restCharacteristicMockMvc.perform(put("/api/characteristics")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(characteristicDTO)))
            .andExpect(status().isOk());

        // Validate the Characteristic in the database
        List<Characteristic> characteristicList = characteristicRepository.findAll();
        assertThat(characteristicList).hasSize(databaseSizeBeforeUpdate);
        Characteristic testCharacteristic = characteristicList.get(characteristicList.size() - 1);
        assertThat(testCharacteristic.getName()).isEqualTo(UPDATED_NAME);
        assertThat(testCharacteristic.getDecs()).isEqualTo(UPDATED_DECS);
        assertThat(testCharacteristic.isUniqued()).isEqualTo(UPDATED_UNIQUED);
        assertThat(testCharacteristic.getValueType()).isEqualTo(UPDATED_VALUE_TYPE);
        assertThat(testCharacteristic.getMinCardinality()).isEqualTo(UPDATED_MIN_CARDINALITY);
        assertThat(testCharacteristic.getMaxCardinality()).isEqualTo(UPDATED_MAX_CARDINALITY);
        assertThat(testCharacteristic.isExtensible()).isEqualTo(UPDATED_EXTENSIBLE);
    }

    @Test
    @Transactional
    public void updateNonExistingCharacteristic() throws Exception {
        int databaseSizeBeforeUpdate = characteristicRepository.findAll().size();

        // Create the Characteristic
        CharacteristicDTO characteristicDTO = characteristicMapper.toDto(characteristic);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restCharacteristicMockMvc.perform(put("/api/characteristics")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(characteristicDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Characteristic in the database
        List<Characteristic> characteristicList = characteristicRepository.findAll();
        assertThat(characteristicList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteCharacteristic() throws Exception {
        // Initialize the database
        characteristicRepository.saveAndFlush(characteristic);

        int databaseSizeBeforeDelete = characteristicRepository.findAll().size();

        // Delete the characteristic
        restCharacteristicMockMvc.perform(delete("/api/characteristics/{id}", characteristic.getId())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<Characteristic> characteristicList = characteristicRepository.findAll();
        assertThat(characteristicList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
