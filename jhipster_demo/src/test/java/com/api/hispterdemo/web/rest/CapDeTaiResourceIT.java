package com.api.hispterdemo.web.rest;

import com.api.hispterdemo.JhispterDemoApp;
import com.api.hispterdemo.domain.CapDeTai;
import com.api.hispterdemo.repository.CapDeTaiRepository;
import com.api.hispterdemo.service.CapDeTaiService;
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
 * Integration tests for the {@link CapDeTaiResource} REST controller.
 */
@SpringBootTest(classes = JhispterDemoApp.class)
public class CapDeTaiResourceIT {

    private static final String DEFAULT_TEN_CAP_DE_TAI = "AAAAAAAAAA";
    private static final String UPDATED_TEN_CAP_DE_TAI = "BBBBBBBBBB";

    @Autowired
    private CapDeTaiRepository capDeTaiRepository;

    @Autowired
    private CapDeTaiService capDeTaiService;

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

    private MockMvc restCapDeTaiMockMvc;

    private CapDeTai capDeTai;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final CapDeTaiResource capDeTaiResource = new CapDeTaiResource(capDeTaiService);
        this.restCapDeTaiMockMvc = MockMvcBuilders.standaloneSetup(capDeTaiResource)
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
    public static CapDeTai createEntity(EntityManager em) {
        CapDeTai capDeTai = new CapDeTai()
            .tenCapDeTai(DEFAULT_TEN_CAP_DE_TAI);
        return capDeTai;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static CapDeTai createUpdatedEntity(EntityManager em) {
        CapDeTai capDeTai = new CapDeTai()
            .tenCapDeTai(UPDATED_TEN_CAP_DE_TAI);
        return capDeTai;
    }

    @BeforeEach
    public void initTest() {
        capDeTai = createEntity(em);
    }

    @Test
    @Transactional
    public void createCapDeTai() throws Exception {
        int databaseSizeBeforeCreate = capDeTaiRepository.findAll().size();

        // Create the CapDeTai
        restCapDeTaiMockMvc.perform(post("/api/cap-de-tais")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(capDeTai)))
            .andExpect(status().isCreated());

        // Validate the CapDeTai in the database
        List<CapDeTai> capDeTaiList = capDeTaiRepository.findAll();
        assertThat(capDeTaiList).hasSize(databaseSizeBeforeCreate + 1);
        CapDeTai testCapDeTai = capDeTaiList.get(capDeTaiList.size() - 1);
        assertThat(testCapDeTai.getTenCapDeTai()).isEqualTo(DEFAULT_TEN_CAP_DE_TAI);
    }

    @Test
    @Transactional
    public void createCapDeTaiWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = capDeTaiRepository.findAll().size();

        // Create the CapDeTai with an existing ID
        capDeTai.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restCapDeTaiMockMvc.perform(post("/api/cap-de-tais")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(capDeTai)))
            .andExpect(status().isBadRequest());

        // Validate the CapDeTai in the database
        List<CapDeTai> capDeTaiList = capDeTaiRepository.findAll();
        assertThat(capDeTaiList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void checkTenCapDeTaiIsRequired() throws Exception {
        int databaseSizeBeforeTest = capDeTaiRepository.findAll().size();
        // set the field null
        capDeTai.setTenCapDeTai(null);

        // Create the CapDeTai, which fails.

        restCapDeTaiMockMvc.perform(post("/api/cap-de-tais")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(capDeTai)))
            .andExpect(status().isBadRequest());

        List<CapDeTai> capDeTaiList = capDeTaiRepository.findAll();
        assertThat(capDeTaiList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllCapDeTais() throws Exception {
        // Initialize the database
        capDeTaiRepository.saveAndFlush(capDeTai);

        // Get all the capDeTaiList
        restCapDeTaiMockMvc.perform(get("/api/cap-de-tais?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(capDeTai.getId().intValue())))
            .andExpect(jsonPath("$.[*].tenCapDeTai").value(hasItem(DEFAULT_TEN_CAP_DE_TAI)));
    }
    
    @Test
    @Transactional
    public void getCapDeTai() throws Exception {
        // Initialize the database
        capDeTaiRepository.saveAndFlush(capDeTai);

        // Get the capDeTai
        restCapDeTaiMockMvc.perform(get("/api/cap-de-tais/{id}", capDeTai.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(capDeTai.getId().intValue()))
            .andExpect(jsonPath("$.tenCapDeTai").value(DEFAULT_TEN_CAP_DE_TAI));
    }

    @Test
    @Transactional
    public void getNonExistingCapDeTai() throws Exception {
        // Get the capDeTai
        restCapDeTaiMockMvc.perform(get("/api/cap-de-tais/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateCapDeTai() throws Exception {
        // Initialize the database
        capDeTaiService.save(capDeTai);

        int databaseSizeBeforeUpdate = capDeTaiRepository.findAll().size();

        // Update the capDeTai
        CapDeTai updatedCapDeTai = capDeTaiRepository.findById(capDeTai.getId()).get();
        // Disconnect from session so that the updates on updatedCapDeTai are not directly saved in db
        em.detach(updatedCapDeTai);
        updatedCapDeTai
            .tenCapDeTai(UPDATED_TEN_CAP_DE_TAI);

        restCapDeTaiMockMvc.perform(put("/api/cap-de-tais")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(updatedCapDeTai)))
            .andExpect(status().isOk());

        // Validate the CapDeTai in the database
        List<CapDeTai> capDeTaiList = capDeTaiRepository.findAll();
        assertThat(capDeTaiList).hasSize(databaseSizeBeforeUpdate);
        CapDeTai testCapDeTai = capDeTaiList.get(capDeTaiList.size() - 1);
        assertThat(testCapDeTai.getTenCapDeTai()).isEqualTo(UPDATED_TEN_CAP_DE_TAI);
    }

    @Test
    @Transactional
    public void updateNonExistingCapDeTai() throws Exception {
        int databaseSizeBeforeUpdate = capDeTaiRepository.findAll().size();

        // Create the CapDeTai

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restCapDeTaiMockMvc.perform(put("/api/cap-de-tais")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(capDeTai)))
            .andExpect(status().isBadRequest());

        // Validate the CapDeTai in the database
        List<CapDeTai> capDeTaiList = capDeTaiRepository.findAll();
        assertThat(capDeTaiList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteCapDeTai() throws Exception {
        // Initialize the database
        capDeTaiService.save(capDeTai);

        int databaseSizeBeforeDelete = capDeTaiRepository.findAll().size();

        // Delete the capDeTai
        restCapDeTaiMockMvc.perform(delete("/api/cap-de-tais/{id}", capDeTai.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<CapDeTai> capDeTaiList = capDeTaiRepository.findAll();
        assertThat(capDeTaiList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
