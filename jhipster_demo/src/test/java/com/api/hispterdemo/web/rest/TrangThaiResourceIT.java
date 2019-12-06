package com.api.hispterdemo.web.rest;

import com.api.hispterdemo.JhispterDemoApp;
import com.api.hispterdemo.domain.TrangThai;
import com.api.hispterdemo.repository.TrangThaiRepository;
import com.api.hispterdemo.service.TrangThaiService;
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
 * Integration tests for the {@link TrangThaiResource} REST controller.
 */
@SpringBootTest(classes = JhispterDemoApp.class)
public class TrangThaiResourceIT {

    private static final String DEFAULT_TEN_TRANG_THAI = "AAAAAAAAAA";
    private static final String UPDATED_TEN_TRANG_THAI = "BBBBBBBBBB";

    @Autowired
    private TrangThaiRepository trangThaiRepository;

    @Autowired
    private TrangThaiService trangThaiService;

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

    private MockMvc restTrangThaiMockMvc;

    private TrangThai trangThai;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final TrangThaiResource trangThaiResource = new TrangThaiResource(trangThaiService);
        this.restTrangThaiMockMvc = MockMvcBuilders.standaloneSetup(trangThaiResource)
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
    public static TrangThai createEntity(EntityManager em) {
        TrangThai trangThai = new TrangThai()
            .tenTrangThai(DEFAULT_TEN_TRANG_THAI);
        return trangThai;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static TrangThai createUpdatedEntity(EntityManager em) {
        TrangThai trangThai = new TrangThai()
            .tenTrangThai(UPDATED_TEN_TRANG_THAI);
        return trangThai;
    }

    @BeforeEach
    public void initTest() {
        trangThai = createEntity(em);
    }

    @Test
    @Transactional
    public void createTrangThai() throws Exception {
        int databaseSizeBeforeCreate = trangThaiRepository.findAll().size();

        // Create the TrangThai
        restTrangThaiMockMvc.perform(post("/api/trang-thais")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(trangThai)))
            .andExpect(status().isCreated());

        // Validate the TrangThai in the database
        List<TrangThai> trangThaiList = trangThaiRepository.findAll();
        assertThat(trangThaiList).hasSize(databaseSizeBeforeCreate + 1);
        TrangThai testTrangThai = trangThaiList.get(trangThaiList.size() - 1);
        assertThat(testTrangThai.getTenTrangThai()).isEqualTo(DEFAULT_TEN_TRANG_THAI);
    }

    @Test
    @Transactional
    public void createTrangThaiWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = trangThaiRepository.findAll().size();

        // Create the TrangThai with an existing ID
        trangThai.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restTrangThaiMockMvc.perform(post("/api/trang-thais")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(trangThai)))
            .andExpect(status().isBadRequest());

        // Validate the TrangThai in the database
        List<TrangThai> trangThaiList = trangThaiRepository.findAll();
        assertThat(trangThaiList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void checkTenTrangThaiIsRequired() throws Exception {
        int databaseSizeBeforeTest = trangThaiRepository.findAll().size();
        // set the field null
        trangThai.setTenTrangThai(null);

        // Create the TrangThai, which fails.

        restTrangThaiMockMvc.perform(post("/api/trang-thais")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(trangThai)))
            .andExpect(status().isBadRequest());

        List<TrangThai> trangThaiList = trangThaiRepository.findAll();
        assertThat(trangThaiList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllTrangThais() throws Exception {
        // Initialize the database
        trangThaiRepository.saveAndFlush(trangThai);

        // Get all the trangThaiList
        restTrangThaiMockMvc.perform(get("/api/trang-thais?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(trangThai.getId().intValue())))
            .andExpect(jsonPath("$.[*].tenTrangThai").value(hasItem(DEFAULT_TEN_TRANG_THAI)));
    }
    
    @Test
    @Transactional
    public void getTrangThai() throws Exception {
        // Initialize the database
        trangThaiRepository.saveAndFlush(trangThai);

        // Get the trangThai
        restTrangThaiMockMvc.perform(get("/api/trang-thais/{id}", trangThai.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(trangThai.getId().intValue()))
            .andExpect(jsonPath("$.tenTrangThai").value(DEFAULT_TEN_TRANG_THAI));
    }

    @Test
    @Transactional
    public void getNonExistingTrangThai() throws Exception {
        // Get the trangThai
        restTrangThaiMockMvc.perform(get("/api/trang-thais/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateTrangThai() throws Exception {
        // Initialize the database
        trangThaiService.save(trangThai);

        int databaseSizeBeforeUpdate = trangThaiRepository.findAll().size();

        // Update the trangThai
        TrangThai updatedTrangThai = trangThaiRepository.findById(trangThai.getId()).get();
        // Disconnect from session so that the updates on updatedTrangThai are not directly saved in db
        em.detach(updatedTrangThai);
        updatedTrangThai
            .tenTrangThai(UPDATED_TEN_TRANG_THAI);

        restTrangThaiMockMvc.perform(put("/api/trang-thais")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(updatedTrangThai)))
            .andExpect(status().isOk());

        // Validate the TrangThai in the database
        List<TrangThai> trangThaiList = trangThaiRepository.findAll();
        assertThat(trangThaiList).hasSize(databaseSizeBeforeUpdate);
        TrangThai testTrangThai = trangThaiList.get(trangThaiList.size() - 1);
        assertThat(testTrangThai.getTenTrangThai()).isEqualTo(UPDATED_TEN_TRANG_THAI);
    }

    @Test
    @Transactional
    public void updateNonExistingTrangThai() throws Exception {
        int databaseSizeBeforeUpdate = trangThaiRepository.findAll().size();

        // Create the TrangThai

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restTrangThaiMockMvc.perform(put("/api/trang-thais")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(trangThai)))
            .andExpect(status().isBadRequest());

        // Validate the TrangThai in the database
        List<TrangThai> trangThaiList = trangThaiRepository.findAll();
        assertThat(trangThaiList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteTrangThai() throws Exception {
        // Initialize the database
        trangThaiService.save(trangThai);

        int databaseSizeBeforeDelete = trangThaiRepository.findAll().size();

        // Delete the trangThai
        restTrangThaiMockMvc.perform(delete("/api/trang-thais/{id}", trangThai.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<TrangThai> trangThaiList = trangThaiRepository.findAll();
        assertThat(trangThaiList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
