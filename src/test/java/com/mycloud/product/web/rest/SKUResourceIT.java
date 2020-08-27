package com.mycloud.product.web.rest;

import com.mycloud.product.ProductServiceApp;
import com.mycloud.product.domain.SKU;
import com.mycloud.product.repository.SKURepository;
import com.mycloud.product.service.SKUService;
import com.mycloud.product.service.dto.SKUDTO;
import com.mycloud.product.service.mapper.SKUMapper;

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
import java.math.BigDecimal;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Integration tests for the {@link SKUResource} REST controller.
 */
@SpringBootTest(classes = ProductServiceApp.class)
@AutoConfigureMockMvc
@WithMockUser
public class SKUResourceIT {

    private static final String DEFAULT_CODE = "AAAAAAAAAA";
    private static final String UPDATED_CODE = "BBBBBBBBBB";

    private static final String DEFAULT_BAR_CODE = "AAAAAAAAAA";
    private static final String UPDATED_BAR_CODE = "BBBBBBBBBB";

    private static final BigDecimal DEFAULT_PRICE = new BigDecimal(1);
    private static final BigDecimal UPDATED_PRICE = new BigDecimal(2);

    private static final Integer DEFAULT_STOCK_QUANTIRY = 1;
    private static final Integer UPDATED_STOCK_QUANTIRY = 2;

    @Autowired
    private SKURepository sKURepository;

    @Autowired
    private SKUMapper sKUMapper;

    @Autowired
    private SKUService sKUService;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restSKUMockMvc;

    private SKU sKU;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static SKU createEntity(EntityManager em) {
        SKU sKU = new SKU()
            .code(DEFAULT_CODE)
            .barCode(DEFAULT_BAR_CODE)
            .price(DEFAULT_PRICE)
            .stockQuantiry(DEFAULT_STOCK_QUANTIRY);
        return sKU;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static SKU createUpdatedEntity(EntityManager em) {
        SKU sKU = new SKU()
            .code(UPDATED_CODE)
            .barCode(UPDATED_BAR_CODE)
            .price(UPDATED_PRICE)
            .stockQuantiry(UPDATED_STOCK_QUANTIRY);
        return sKU;
    }

    @BeforeEach
    public void initTest() {
        sKU = createEntity(em);
    }

    @Test
    @Transactional
    public void createSKU() throws Exception {
        int databaseSizeBeforeCreate = sKURepository.findAll().size();
        // Create the SKU
        SKUDTO sKUDTO = sKUMapper.toDto(sKU);
        restSKUMockMvc.perform(post("/api/skus")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(sKUDTO)))
            .andExpect(status().isCreated());

        // Validate the SKU in the database
        List<SKU> sKUList = sKURepository.findAll();
        assertThat(sKUList).hasSize(databaseSizeBeforeCreate + 1);
        SKU testSKU = sKUList.get(sKUList.size() - 1);
        assertThat(testSKU.getCode()).isEqualTo(DEFAULT_CODE);
        assertThat(testSKU.getBarCode()).isEqualTo(DEFAULT_BAR_CODE);
        assertThat(testSKU.getPrice()).isEqualTo(DEFAULT_PRICE);
        assertThat(testSKU.getStockQuantiry()).isEqualTo(DEFAULT_STOCK_QUANTIRY);
    }

    @Test
    @Transactional
    public void createSKUWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = sKURepository.findAll().size();

        // Create the SKU with an existing ID
        sKU.setId(1L);
        SKUDTO sKUDTO = sKUMapper.toDto(sKU);

        // An entity with an existing ID cannot be created, so this API call must fail
        restSKUMockMvc.perform(post("/api/skus")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(sKUDTO)))
            .andExpect(status().isBadRequest());

        // Validate the SKU in the database
        List<SKU> sKUList = sKURepository.findAll();
        assertThat(sKUList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void checkCodeIsRequired() throws Exception {
        int databaseSizeBeforeTest = sKURepository.findAll().size();
        // set the field null
        sKU.setCode(null);

        // Create the SKU, which fails.
        SKUDTO sKUDTO = sKUMapper.toDto(sKU);


        restSKUMockMvc.perform(post("/api/skus")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(sKUDTO)))
            .andExpect(status().isBadRequest());

        List<SKU> sKUList = sKURepository.findAll();
        assertThat(sKUList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkPriceIsRequired() throws Exception {
        int databaseSizeBeforeTest = sKURepository.findAll().size();
        // set the field null
        sKU.setPrice(null);

        // Create the SKU, which fails.
        SKUDTO sKUDTO = sKUMapper.toDto(sKU);


        restSKUMockMvc.perform(post("/api/skus")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(sKUDTO)))
            .andExpect(status().isBadRequest());

        List<SKU> sKUList = sKURepository.findAll();
        assertThat(sKUList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkStockQuantiryIsRequired() throws Exception {
        int databaseSizeBeforeTest = sKURepository.findAll().size();
        // set the field null
        sKU.setStockQuantiry(null);

        // Create the SKU, which fails.
        SKUDTO sKUDTO = sKUMapper.toDto(sKU);


        restSKUMockMvc.perform(post("/api/skus")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(sKUDTO)))
            .andExpect(status().isBadRequest());

        List<SKU> sKUList = sKURepository.findAll();
        assertThat(sKUList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllSKUS() throws Exception {
        // Initialize the database
        sKURepository.saveAndFlush(sKU);

        // Get all the sKUList
        restSKUMockMvc.perform(get("/api/skus?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(sKU.getId().intValue())))
            .andExpect(jsonPath("$.[*].code").value(hasItem(DEFAULT_CODE)))
            .andExpect(jsonPath("$.[*].barCode").value(hasItem(DEFAULT_BAR_CODE)))
            .andExpect(jsonPath("$.[*].price").value(hasItem(DEFAULT_PRICE.intValue())))
            .andExpect(jsonPath("$.[*].stockQuantiry").value(hasItem(DEFAULT_STOCK_QUANTIRY)));
    }
    
    @Test
    @Transactional
    public void getSKU() throws Exception {
        // Initialize the database
        sKURepository.saveAndFlush(sKU);

        // Get the sKU
        restSKUMockMvc.perform(get("/api/skus/{id}", sKU.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(sKU.getId().intValue()))
            .andExpect(jsonPath("$.code").value(DEFAULT_CODE))
            .andExpect(jsonPath("$.barCode").value(DEFAULT_BAR_CODE))
            .andExpect(jsonPath("$.price").value(DEFAULT_PRICE.intValue()))
            .andExpect(jsonPath("$.stockQuantiry").value(DEFAULT_STOCK_QUANTIRY));
    }
    @Test
    @Transactional
    public void getNonExistingSKU() throws Exception {
        // Get the sKU
        restSKUMockMvc.perform(get("/api/skus/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateSKU() throws Exception {
        // Initialize the database
        sKURepository.saveAndFlush(sKU);

        int databaseSizeBeforeUpdate = sKURepository.findAll().size();

        // Update the sKU
        SKU updatedSKU = sKURepository.findById(sKU.getId()).get();
        // Disconnect from session so that the updates on updatedSKU are not directly saved in db
        em.detach(updatedSKU);
        updatedSKU
            .code(UPDATED_CODE)
            .barCode(UPDATED_BAR_CODE)
            .price(UPDATED_PRICE)
            .stockQuantiry(UPDATED_STOCK_QUANTIRY);
        SKUDTO sKUDTO = sKUMapper.toDto(updatedSKU);

        restSKUMockMvc.perform(put("/api/skus")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(sKUDTO)))
            .andExpect(status().isOk());

        // Validate the SKU in the database
        List<SKU> sKUList = sKURepository.findAll();
        assertThat(sKUList).hasSize(databaseSizeBeforeUpdate);
        SKU testSKU = sKUList.get(sKUList.size() - 1);
        assertThat(testSKU.getCode()).isEqualTo(UPDATED_CODE);
        assertThat(testSKU.getBarCode()).isEqualTo(UPDATED_BAR_CODE);
        assertThat(testSKU.getPrice()).isEqualTo(UPDATED_PRICE);
        assertThat(testSKU.getStockQuantiry()).isEqualTo(UPDATED_STOCK_QUANTIRY);
    }

    @Test
    @Transactional
    public void updateNonExistingSKU() throws Exception {
        int databaseSizeBeforeUpdate = sKURepository.findAll().size();

        // Create the SKU
        SKUDTO sKUDTO = sKUMapper.toDto(sKU);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restSKUMockMvc.perform(put("/api/skus")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(sKUDTO)))
            .andExpect(status().isBadRequest());

        // Validate the SKU in the database
        List<SKU> sKUList = sKURepository.findAll();
        assertThat(sKUList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteSKU() throws Exception {
        // Initialize the database
        sKURepository.saveAndFlush(sKU);

        int databaseSizeBeforeDelete = sKURepository.findAll().size();

        // Delete the sKU
        restSKUMockMvc.perform(delete("/api/skus/{id}", sKU.getId())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<SKU> sKUList = sKURepository.findAll();
        assertThat(sKUList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
