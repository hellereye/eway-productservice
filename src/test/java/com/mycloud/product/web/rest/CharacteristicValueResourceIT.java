package com.mycloud.product.web.rest;

import com.mycloud.product.ProductServiceApp;
import com.mycloud.product.domain.CharacteristicValue;
import com.mycloud.product.repository.CharacteristicValueRepository;
import com.mycloud.product.service.CharacteristicValueService;
import com.mycloud.product.service.dto.CharacteristicValueDTO;
import com.mycloud.product.service.mapper.CharacteristicValueMapper;

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
 * Integration tests for the {@link CharacteristicValueResource} REST controller.
 */
@SpringBootTest(classes = ProductServiceApp.class)
@AutoConfigureMockMvc
@WithMockUser
public class CharacteristicValueResourceIT {

    private static final String DEFAULT_DEFAULT_VALUE = "AAAAAAAAAA";
    private static final String UPDATED_DEFAULT_VALUE = "BBBBBBBBBB";

    private static final String DEFAULT_VALUES = "AAAAAAAAAA";
    private static final String UPDATED_VALUES = "BBBBBBBBBB";

    private static final String DEFAULT_VALUE_FORM = "AAAAAAAAAA";
    private static final String UPDATED_VALUE_FORM = "BBBBBBBBBB";

    private static final String DEFAULT_VALUE_TO = "AAAAAAAAAA";
    private static final String UPDATED_VALUE_TO = "BBBBBBBBBB";

    private static final String DEFAULT_RANGE_INTERVAL = "AAAAAAAAAA";
    private static final String UPDATED_RANGE_INTERVAL = "BBBBBBBBBB";

    @Autowired
    private CharacteristicValueRepository characteristicValueRepository;

    @Autowired
    private CharacteristicValueMapper characteristicValueMapper;

    @Autowired
    private CharacteristicValueService characteristicValueService;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restCharacteristicValueMockMvc;

    private CharacteristicValue characteristicValue;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static CharacteristicValue createEntity(EntityManager em) {
        CharacteristicValue characteristicValue = new CharacteristicValue()
            .defaultValue(DEFAULT_DEFAULT_VALUE)
            .values(DEFAULT_VALUES)
            .valueForm(DEFAULT_VALUE_FORM)
            .valueTo(DEFAULT_VALUE_TO)
            .rangeInterval(DEFAULT_RANGE_INTERVAL);
        return characteristicValue;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static CharacteristicValue createUpdatedEntity(EntityManager em) {
        CharacteristicValue characteristicValue = new CharacteristicValue()
            .defaultValue(UPDATED_DEFAULT_VALUE)
            .values(UPDATED_VALUES)
            .valueForm(UPDATED_VALUE_FORM)
            .valueTo(UPDATED_VALUE_TO)
            .rangeInterval(UPDATED_RANGE_INTERVAL);
        return characteristicValue;
    }

    @BeforeEach
    public void initTest() {
        characteristicValue = createEntity(em);
    }

    @Test
    @Transactional
    public void createCharacteristicValue() throws Exception {
        int databaseSizeBeforeCreate = characteristicValueRepository.findAll().size();
        // Create the CharacteristicValue
        CharacteristicValueDTO characteristicValueDTO = characteristicValueMapper.toDto(characteristicValue);
        restCharacteristicValueMockMvc.perform(post("/api/characteristic-values")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(characteristicValueDTO)))
            .andExpect(status().isCreated());

        // Validate the CharacteristicValue in the database
        List<CharacteristicValue> characteristicValueList = characteristicValueRepository.findAll();
        assertThat(characteristicValueList).hasSize(databaseSizeBeforeCreate + 1);
        CharacteristicValue testCharacteristicValue = characteristicValueList.get(characteristicValueList.size() - 1);
        assertThat(testCharacteristicValue.getDefaultValue()).isEqualTo(DEFAULT_DEFAULT_VALUE);
        assertThat(testCharacteristicValue.getValues()).isEqualTo(DEFAULT_VALUES);
        assertThat(testCharacteristicValue.getValueForm()).isEqualTo(DEFAULT_VALUE_FORM);
        assertThat(testCharacteristicValue.getValueTo()).isEqualTo(DEFAULT_VALUE_TO);
        assertThat(testCharacteristicValue.getRangeInterval()).isEqualTo(DEFAULT_RANGE_INTERVAL);
    }

    @Test
    @Transactional
    public void createCharacteristicValueWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = characteristicValueRepository.findAll().size();

        // Create the CharacteristicValue with an existing ID
        characteristicValue.setId(1L);
        CharacteristicValueDTO characteristicValueDTO = characteristicValueMapper.toDto(characteristicValue);

        // An entity with an existing ID cannot be created, so this API call must fail
        restCharacteristicValueMockMvc.perform(post("/api/characteristic-values")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(characteristicValueDTO)))
            .andExpect(status().isBadRequest());

        // Validate the CharacteristicValue in the database
        List<CharacteristicValue> characteristicValueList = characteristicValueRepository.findAll();
        assertThat(characteristicValueList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllCharacteristicValues() throws Exception {
        // Initialize the database
        characteristicValueRepository.saveAndFlush(characteristicValue);

        // Get all the characteristicValueList
        restCharacteristicValueMockMvc.perform(get("/api/characteristic-values?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(characteristicValue.getId().intValue())))
            .andExpect(jsonPath("$.[*].defaultValue").value(hasItem(DEFAULT_DEFAULT_VALUE)))
            .andExpect(jsonPath("$.[*].values").value(hasItem(DEFAULT_VALUES)))
            .andExpect(jsonPath("$.[*].valueForm").value(hasItem(DEFAULT_VALUE_FORM)))
            .andExpect(jsonPath("$.[*].valueTo").value(hasItem(DEFAULT_VALUE_TO)))
            .andExpect(jsonPath("$.[*].rangeInterval").value(hasItem(DEFAULT_RANGE_INTERVAL)));
    }
    
    @Test
    @Transactional
    public void getCharacteristicValue() throws Exception {
        // Initialize the database
        characteristicValueRepository.saveAndFlush(characteristicValue);

        // Get the characteristicValue
        restCharacteristicValueMockMvc.perform(get("/api/characteristic-values/{id}", characteristicValue.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(characteristicValue.getId().intValue()))
            .andExpect(jsonPath("$.defaultValue").value(DEFAULT_DEFAULT_VALUE))
            .andExpect(jsonPath("$.values").value(DEFAULT_VALUES))
            .andExpect(jsonPath("$.valueForm").value(DEFAULT_VALUE_FORM))
            .andExpect(jsonPath("$.valueTo").value(DEFAULT_VALUE_TO))
            .andExpect(jsonPath("$.rangeInterval").value(DEFAULT_RANGE_INTERVAL));
    }
    @Test
    @Transactional
    public void getNonExistingCharacteristicValue() throws Exception {
        // Get the characteristicValue
        restCharacteristicValueMockMvc.perform(get("/api/characteristic-values/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateCharacteristicValue() throws Exception {
        // Initialize the database
        characteristicValueRepository.saveAndFlush(characteristicValue);

        int databaseSizeBeforeUpdate = characteristicValueRepository.findAll().size();

        // Update the characteristicValue
        CharacteristicValue updatedCharacteristicValue = characteristicValueRepository.findById(characteristicValue.getId()).get();
        // Disconnect from session so that the updates on updatedCharacteristicValue are not directly saved in db
        em.detach(updatedCharacteristicValue);
        updatedCharacteristicValue
            .defaultValue(UPDATED_DEFAULT_VALUE)
            .values(UPDATED_VALUES)
            .valueForm(UPDATED_VALUE_FORM)
            .valueTo(UPDATED_VALUE_TO)
            .rangeInterval(UPDATED_RANGE_INTERVAL);
        CharacteristicValueDTO characteristicValueDTO = characteristicValueMapper.toDto(updatedCharacteristicValue);

        restCharacteristicValueMockMvc.perform(put("/api/characteristic-values")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(characteristicValueDTO)))
            .andExpect(status().isOk());

        // Validate the CharacteristicValue in the database
        List<CharacteristicValue> characteristicValueList = characteristicValueRepository.findAll();
        assertThat(characteristicValueList).hasSize(databaseSizeBeforeUpdate);
        CharacteristicValue testCharacteristicValue = characteristicValueList.get(characteristicValueList.size() - 1);
        assertThat(testCharacteristicValue.getDefaultValue()).isEqualTo(UPDATED_DEFAULT_VALUE);
        assertThat(testCharacteristicValue.getValues()).isEqualTo(UPDATED_VALUES);
        assertThat(testCharacteristicValue.getValueForm()).isEqualTo(UPDATED_VALUE_FORM);
        assertThat(testCharacteristicValue.getValueTo()).isEqualTo(UPDATED_VALUE_TO);
        assertThat(testCharacteristicValue.getRangeInterval()).isEqualTo(UPDATED_RANGE_INTERVAL);
    }

    @Test
    @Transactional
    public void updateNonExistingCharacteristicValue() throws Exception {
        int databaseSizeBeforeUpdate = characteristicValueRepository.findAll().size();

        // Create the CharacteristicValue
        CharacteristicValueDTO characteristicValueDTO = characteristicValueMapper.toDto(characteristicValue);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restCharacteristicValueMockMvc.perform(put("/api/characteristic-values")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(characteristicValueDTO)))
            .andExpect(status().isBadRequest());

        // Validate the CharacteristicValue in the database
        List<CharacteristicValue> characteristicValueList = characteristicValueRepository.findAll();
        assertThat(characteristicValueList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteCharacteristicValue() throws Exception {
        // Initialize the database
        characteristicValueRepository.saveAndFlush(characteristicValue);

        int databaseSizeBeforeDelete = characteristicValueRepository.findAll().size();

        // Delete the characteristicValue
        restCharacteristicValueMockMvc.perform(delete("/api/characteristic-values/{id}", characteristicValue.getId())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<CharacteristicValue> characteristicValueList = characteristicValueRepository.findAll();
        assertThat(characteristicValueList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
