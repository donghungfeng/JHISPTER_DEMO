package com.api.hispterdemo.web.rest;

import com.api.hispterdemo.JhispterDemoApp;
import com.api.hispterdemo.domain.DuToan;
import com.api.hispterdemo.repository.DuToanRepository;
import com.api.hispterdemo.service.DuToanService;
import com.api.hispterdemo.web.rest.errors.ExceptionTranslator;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.web.PageableHandlerMethodArgumentResolver;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.Validator;

import javax.persistence.EntityManager;
import java.util.List;

import static com.api.hispterdemo.web.rest.TestUtil.createFormattingConversionService;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Integration tests for the {@link DuToanResource} REST controller.
 */
@SpringBootTest(classes = JhispterDemoApp.class)
public class DuToanResourceIT {

    private static final Integer DEFAULT_LOAI_DU_TOAN = 1;
    private static final Integer UPDATED_LOAI_DU_TOAN = 2;

    private static final Double DEFAULT_DU_TOAN = 1D;
    private static final Double UPDATED_DU_TOAN = 2D;

    @Autowired
    private DuToanRepository duToanRepository;

    @Autowired
    private DuToanService duToanService;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    @Autowired
    private Validator validator;

    private MockMvc restDuToanMockMvc;

    private DuToan duToan;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final DuToanResource duToanResource = new DuToanResource(duToanService);
        this.restDuToanMockMvc = MockMvcBuilders.standaloneSetup(duToanResource)
            .setCustomArgumentResolvers(pageableArgumentResolver)
            .setControllerAdvice(exceptionTranslator)
            .setConversionService(createFormattingConversionService())
            .setMessageConverters(jacksonMessageConverter)
            .setValidator(validator).build();
    }

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static DuToan createEntity(EntityManager em) {
        DuToan duToan = new DuToan()
            .loaiDuToan(DEFAULT_LOAI_DU_TOAN)
            .duToan(DEFAULT_DU_TOAN);
        return duToan;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static DuToan createUpdatedEntity(EntityManager em) {
        DuToan duToan = new DuToan()
            .loaiDuToan(UPDATED_LOAI_DU_TOAN)
            .duToan(UPDATED_DU_TOAN);
        return duToan;
    }

    @BeforeEach
    public void initTest() {
        duToan = createEntity(em);
    }

    @Test
    @Transactional
    public void createDuToan() throws Exception {
        int databaseSizeBeforeCreate = duToanRepository.findAll().size();

        // Create the DuToan
        restDuToanMockMvc.perform(post("/api/du-toans")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(duToan)))
            .andExpect(status().isCreated());

        // Validate the DuToan in the database
        List<DuToan> duToanList = duToanRepository.findAll();
        assertThat(duToanList).hasSize(databaseSizeBeforeCreate + 1);
        DuToan testDuToan = duToanList.get(duToanList.size() - 1);
        assertThat(testDuToan.getLoaiDuToan()).isEqualTo(DEFAULT_LOAI_DU_TOAN);
        assertThat(testDuToan.getDuToan()).isEqualTo(DEFAULT_DU_TOAN);
    }

    @Test
    @Transactional
    public void createDuToanWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = duToanRepository.findAll().size();

        // Create the DuToan with an existing ID
        duToan.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restDuToanMockMvc.perform(post("/api/du-toans")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(duToan)))
            .andExpect(status().isBadRequest());

        // Validate the DuToan in the database
        List<DuToan> duToanList = duToanRepository.findAll();
        assertThat(duToanList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void checkLoaiDuToanIsRequired() throws Exception {
        int databaseSizeBeforeTest = duToanRepository.findAll().size();
        // set the field null
        duToan.setLoaiDuToan(null);

        // Create the DuToan, which fails.

        restDuToanMockMvc.perform(post("/api/du-toans")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(duToan)))
            .andExpect(status().isBadRequest());

        List<DuToan> duToanList = duToanRepository.findAll();
        assertThat(duToanList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkDuToanIsRequired() throws Exception {
        int databaseSizeBeforeTest = duToanRepository.findAll().size();
        // set the field null
        duToan.setDuToan(null);

        // Create the DuToan, which fails.

        restDuToanMockMvc.perform(post("/api/du-toans")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(duToan)))
            .andExpect(status().isBadRequest());

        List<DuToan> duToanList = duToanRepository.findAll();
        assertThat(duToanList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllDuToans() throws Exception {
        // Initialize the database
        duToanRepository.saveAndFlush(duToan);

        // Get all the duToanList
        restDuToanMockMvc.perform(get("/api/du-toans?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(duToan.getId().intValue())))
            .andExpect(jsonPath("$.[*].loaiDuToan").value(hasItem(DEFAULT_LOAI_DU_TOAN)))
            .andExpect(jsonPath("$.[*].duToan").value(hasItem(DEFAULT_DU_TOAN.doubleValue())));
    }
    
    @Test
    @Transactional
    public void getDuToan() throws Exception {
        // Initialize the database
        duToanRepository.saveAndFlush(duToan);

        // Get the duToan
        restDuToanMockMvc.perform(get("/api/du-toans/{id}", duToan.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(duToan.getId().intValue()))
            .andExpect(jsonPath("$.loaiDuToan").value(DEFAULT_LOAI_DU_TOAN))
            .andExpect(jsonPath("$.duToan").value(DEFAULT_DU_TOAN.doubleValue()));
    }

    @Test
    @Transactional
    public void getNonExistingDuToan() throws Exception {
        // Get the duToan
        restDuToanMockMvc.perform(get("/api/du-toans/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateDuToan() throws Exception {
        // Initialize the database
        duToanService.save(duToan);

        int databaseSizeBeforeUpdate = duToanRepository.findAll().size();

        // Update the duToan
        DuToan updatedDuToan = duToanRepository.findById(duToan.getId()).get();
        // Disconnect from session so that the updates on updatedDuToan are not directly saved in db
        em.detach(updatedDuToan);
        updatedDuToan
            .loaiDuToan(UPDATED_LOAI_DU_TOAN)
            .duToan(UPDATED_DU_TOAN);

        restDuToanMockMvc.perform(put("/api/du-toans")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(updatedDuToan)))
            .andExpect(status().isOk());

        // Validate the DuToan in the database
        List<DuToan> duToanList = duToanRepository.findAll();
        assertThat(duToanList).hasSize(databaseSizeBeforeUpdate);
        DuToan testDuToan = duToanList.get(duToanList.size() - 1);
        assertThat(testDuToan.getLoaiDuToan()).isEqualTo(UPDATED_LOAI_DU_TOAN);
        assertThat(testDuToan.getDuToan()).isEqualTo(UPDATED_DU_TOAN);
    }

    @Test
    @Transactional
    public void updateNonExistingDuToan() throws Exception {
        int databaseSizeBeforeUpdate = duToanRepository.findAll().size();

        // Create the DuToan

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restDuToanMockMvc.perform(put("/api/du-toans")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(duToan)))
            .andExpect(status().isBadRequest());

        // Validate the DuToan in the database
        List<DuToan> duToanList = duToanRepository.findAll();
        assertThat(duToanList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteDuToan() throws Exception {
        // Initialize the database
        duToanService.save(duToan);

        int databaseSizeBeforeDelete = duToanRepository.findAll().size();

        // Delete the duToan
        restDuToanMockMvc.perform(delete("/api/du-toans/{id}", duToan.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<DuToan> duToanList = duToanRepository.findAll();
        assertThat(duToanList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
