package com.api.hispterdemo.web.rest;

import com.api.hispterdemo.JhispterDemoApp;
import com.api.hispterdemo.domain.NhanSuTG;
import com.api.hispterdemo.repository.NhanSuTGRepository;
import com.api.hispterdemo.service.NhanSuTGService;
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
 * Integration tests for the {@link NhanSuTGResource} REST controller.
 */
@SpringBootTest(classes = JhispterDemoApp.class)
public class NhanSuTGResourceIT {

    private static final String DEFAULT_VAI_TRO = "AAAAAAAAAA";
    private static final String UPDATED_VAI_TRO = "BBBBBBBBBB";

    @Autowired
    private NhanSuTGRepository nhanSuTGRepository;

    @Autowired
    private NhanSuTGService nhanSuTGService;

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

    private MockMvc restNhanSuTGMockMvc;

    private NhanSuTG nhanSuTG;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final NhanSuTGResource nhanSuTGResource = new NhanSuTGResource(nhanSuTGService);
        this.restNhanSuTGMockMvc = MockMvcBuilders.standaloneSetup(nhanSuTGResource)
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
    public static NhanSuTG createEntity(EntityManager em) {
        NhanSuTG nhanSuTG = new NhanSuTG()
            .vaiTro(DEFAULT_VAI_TRO);
        return nhanSuTG;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static NhanSuTG createUpdatedEntity(EntityManager em) {
        NhanSuTG nhanSuTG = new NhanSuTG()
            .vaiTro(UPDATED_VAI_TRO);
        return nhanSuTG;
    }

    @BeforeEach
    public void initTest() {
        nhanSuTG = createEntity(em);
    }

    @Test
    @Transactional
    public void createNhanSuTG() throws Exception {
        int databaseSizeBeforeCreate = nhanSuTGRepository.findAll().size();

        // Create the NhanSuTG
        restNhanSuTGMockMvc.perform(post("/api/nhan-su-tgs")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(nhanSuTG)))
            .andExpect(status().isCreated());

        // Validate the NhanSuTG in the database
        List<NhanSuTG> nhanSuTGList = nhanSuTGRepository.findAll();
        assertThat(nhanSuTGList).hasSize(databaseSizeBeforeCreate + 1);
        NhanSuTG testNhanSuTG = nhanSuTGList.get(nhanSuTGList.size() - 1);
        assertThat(testNhanSuTG.getVaiTro()).isEqualTo(DEFAULT_VAI_TRO);
    }

    @Test
    @Transactional
    public void createNhanSuTGWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = nhanSuTGRepository.findAll().size();

        // Create the NhanSuTG with an existing ID
        nhanSuTG.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restNhanSuTGMockMvc.perform(post("/api/nhan-su-tgs")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(nhanSuTG)))
            .andExpect(status().isBadRequest());

        // Validate the NhanSuTG in the database
        List<NhanSuTG> nhanSuTGList = nhanSuTGRepository.findAll();
        assertThat(nhanSuTGList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void checkVaiTroIsRequired() throws Exception {
        int databaseSizeBeforeTest = nhanSuTGRepository.findAll().size();
        // set the field null
        nhanSuTG.setVaiTro(null);

        // Create the NhanSuTG, which fails.

        restNhanSuTGMockMvc.perform(post("/api/nhan-su-tgs")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(nhanSuTG)))
            .andExpect(status().isBadRequest());

        List<NhanSuTG> nhanSuTGList = nhanSuTGRepository.findAll();
        assertThat(nhanSuTGList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllNhanSuTGS() throws Exception {
        // Initialize the database
        nhanSuTGRepository.saveAndFlush(nhanSuTG);

        // Get all the nhanSuTGList
        restNhanSuTGMockMvc.perform(get("/api/nhan-su-tgs?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(nhanSuTG.getId().intValue())))
            .andExpect(jsonPath("$.[*].vaiTro").value(hasItem(DEFAULT_VAI_TRO)));
    }
    
    @Test
    @Transactional
    public void getNhanSuTG() throws Exception {
        // Initialize the database
        nhanSuTGRepository.saveAndFlush(nhanSuTG);

        // Get the nhanSuTG
        restNhanSuTGMockMvc.perform(get("/api/nhan-su-tgs/{id}", nhanSuTG.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(nhanSuTG.getId().intValue()))
            .andExpect(jsonPath("$.vaiTro").value(DEFAULT_VAI_TRO));
    }

    @Test
    @Transactional
    public void getNonExistingNhanSuTG() throws Exception {
        // Get the nhanSuTG
        restNhanSuTGMockMvc.perform(get("/api/nhan-su-tgs/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateNhanSuTG() throws Exception {
        // Initialize the database
        nhanSuTGService.save(nhanSuTG);

        int databaseSizeBeforeUpdate = nhanSuTGRepository.findAll().size();

        // Update the nhanSuTG
        NhanSuTG updatedNhanSuTG = nhanSuTGRepository.findById(nhanSuTG.getId()).get();
        // Disconnect from session so that the updates on updatedNhanSuTG are not directly saved in db
        em.detach(updatedNhanSuTG);
        updatedNhanSuTG
            .vaiTro(UPDATED_VAI_TRO);

        restNhanSuTGMockMvc.perform(put("/api/nhan-su-tgs")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(updatedNhanSuTG)))
            .andExpect(status().isOk());

        // Validate the NhanSuTG in the database
        List<NhanSuTG> nhanSuTGList = nhanSuTGRepository.findAll();
        assertThat(nhanSuTGList).hasSize(databaseSizeBeforeUpdate);
        NhanSuTG testNhanSuTG = nhanSuTGList.get(nhanSuTGList.size() - 1);
        assertThat(testNhanSuTG.getVaiTro()).isEqualTo(UPDATED_VAI_TRO);
    }

    @Test
    @Transactional
    public void updateNonExistingNhanSuTG() throws Exception {
        int databaseSizeBeforeUpdate = nhanSuTGRepository.findAll().size();

        // Create the NhanSuTG

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restNhanSuTGMockMvc.perform(put("/api/nhan-su-tgs")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(nhanSuTG)))
            .andExpect(status().isBadRequest());

        // Validate the NhanSuTG in the database
        List<NhanSuTG> nhanSuTGList = nhanSuTGRepository.findAll();
        assertThat(nhanSuTGList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteNhanSuTG() throws Exception {
        // Initialize the database
        nhanSuTGService.save(nhanSuTG);

        int databaseSizeBeforeDelete = nhanSuTGRepository.findAll().size();

        // Delete the nhanSuTG
        restNhanSuTGMockMvc.perform(delete("/api/nhan-su-tgs/{id}", nhanSuTG.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<NhanSuTG> nhanSuTGList = nhanSuTGRepository.findAll();
        assertThat(nhanSuTGList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
