package com.api.hispterdemo.web.rest;

import com.api.hispterdemo.JhispterDemoApp;
import com.api.hispterdemo.domain.ToChuc;
import com.api.hispterdemo.repository.ToChucRepository;
import com.api.hispterdemo.service.ToChucService;
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
 * Integration tests for the {@link ToChucResource} REST controller.
 */
@SpringBootTest(classes = JhispterDemoApp.class)
public class ToChucResourceIT {

    private static final String DEFAULT_TEN_TO_CHUC = "AAAAAAAAAA";
    private static final String UPDATED_TEN_TO_CHUC = "BBBBBBBBBB";

    @Autowired
    private ToChucRepository toChucRepository;

    @Autowired
    private ToChucService toChucService;

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

    private MockMvc restToChucMockMvc;

    private ToChuc toChuc;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final ToChucResource toChucResource = new ToChucResource(toChucService);
        this.restToChucMockMvc = MockMvcBuilders.standaloneSetup(toChucResource)
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
    public static ToChuc createEntity(EntityManager em) {
        ToChuc toChuc = new ToChuc()
            .tenToChuc(DEFAULT_TEN_TO_CHUC);
        return toChuc;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static ToChuc createUpdatedEntity(EntityManager em) {
        ToChuc toChuc = new ToChuc()
            .tenToChuc(UPDATED_TEN_TO_CHUC);
        return toChuc;
    }

    @BeforeEach
    public void initTest() {
        toChuc = createEntity(em);
    }

    @Test
    @Transactional
    public void createToChuc() throws Exception {
        int databaseSizeBeforeCreate = toChucRepository.findAll().size();

        // Create the ToChuc
        restToChucMockMvc.perform(post("/api/to-chucs")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(toChuc)))
            .andExpect(status().isCreated());

        // Validate the ToChuc in the database
        List<ToChuc> toChucList = toChucRepository.findAll();
        assertThat(toChucList).hasSize(databaseSizeBeforeCreate + 1);
        ToChuc testToChuc = toChucList.get(toChucList.size() - 1);
        assertThat(testToChuc.getTenToChuc()).isEqualTo(DEFAULT_TEN_TO_CHUC);
    }

    @Test
    @Transactional
    public void createToChucWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = toChucRepository.findAll().size();

        // Create the ToChuc with an existing ID
        toChuc.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restToChucMockMvc.perform(post("/api/to-chucs")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(toChuc)))
            .andExpect(status().isBadRequest());

        // Validate the ToChuc in the database
        List<ToChuc> toChucList = toChucRepository.findAll();
        assertThat(toChucList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void checkTenToChucIsRequired() throws Exception {
        int databaseSizeBeforeTest = toChucRepository.findAll().size();
        // set the field null
        toChuc.setTenToChuc(null);

        // Create the ToChuc, which fails.

        restToChucMockMvc.perform(post("/api/to-chucs")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(toChuc)))
            .andExpect(status().isBadRequest());

        List<ToChuc> toChucList = toChucRepository.findAll();
        assertThat(toChucList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllToChucs() throws Exception {
        // Initialize the database
        toChucRepository.saveAndFlush(toChuc);

        // Get all the toChucList
        restToChucMockMvc.perform(get("/api/to-chucs?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(toChuc.getId().intValue())))
            .andExpect(jsonPath("$.[*].tenToChuc").value(hasItem(DEFAULT_TEN_TO_CHUC)));
    }
    
    @Test
    @Transactional
    public void getToChuc() throws Exception {
        // Initialize the database
        toChucRepository.saveAndFlush(toChuc);

        // Get the toChuc
        restToChucMockMvc.perform(get("/api/to-chucs/{id}", toChuc.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(toChuc.getId().intValue()))
            .andExpect(jsonPath("$.tenToChuc").value(DEFAULT_TEN_TO_CHUC));
    }

    @Test
    @Transactional
    public void getNonExistingToChuc() throws Exception {
        // Get the toChuc
        restToChucMockMvc.perform(get("/api/to-chucs/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateToChuc() throws Exception {
        // Initialize the database
        toChucService.save(toChuc);

        int databaseSizeBeforeUpdate = toChucRepository.findAll().size();

        // Update the toChuc
        ToChuc updatedToChuc = toChucRepository.findById(toChuc.getId()).get();
        // Disconnect from session so that the updates on updatedToChuc are not directly saved in db
        em.detach(updatedToChuc);
        updatedToChuc
            .tenToChuc(UPDATED_TEN_TO_CHUC);

        restToChucMockMvc.perform(put("/api/to-chucs")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(updatedToChuc)))
            .andExpect(status().isOk());

        // Validate the ToChuc in the database
        List<ToChuc> toChucList = toChucRepository.findAll();
        assertThat(toChucList).hasSize(databaseSizeBeforeUpdate);
        ToChuc testToChuc = toChucList.get(toChucList.size() - 1);
        assertThat(testToChuc.getTenToChuc()).isEqualTo(UPDATED_TEN_TO_CHUC);
    }

    @Test
    @Transactional
    public void updateNonExistingToChuc() throws Exception {
        int databaseSizeBeforeUpdate = toChucRepository.findAll().size();

        // Create the ToChuc

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restToChucMockMvc.perform(put("/api/to-chucs")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(toChuc)))
            .andExpect(status().isBadRequest());

        // Validate the ToChuc in the database
        List<ToChuc> toChucList = toChucRepository.findAll();
        assertThat(toChucList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteToChuc() throws Exception {
        // Initialize the database
        toChucService.save(toChuc);

        int databaseSizeBeforeDelete = toChucRepository.findAll().size();

        // Delete the toChuc
        restToChucMockMvc.perform(delete("/api/to-chucs/{id}", toChuc.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<ToChuc> toChucList = toChucRepository.findAll();
        assertThat(toChucList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
