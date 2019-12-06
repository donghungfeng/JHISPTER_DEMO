package com.api.hispterdemo.web.rest;

import com.api.hispterdemo.JhispterDemoApp;
import com.api.hispterdemo.domain.Officer;
import com.api.hispterdemo.repository.OfficerRepository;
import com.api.hispterdemo.service.OfficerService;
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
 * Integration tests for the {@link OfficerResource} REST controller.
 */
@SpringBootTest(classes = JhispterDemoApp.class)
public class OfficerResourceIT {

    private static final String DEFAULT_TEN_NHAN_SU = "AAAAAAAAAA";
    private static final String UPDATED_TEN_NHAN_SU = "BBBBBBBBBB";

    private static final String DEFAULT_DIEN_THOAI = "AAAAAAAAAA";
    private static final String UPDATED_DIEN_THOAI = "BBBBBBBBBB";

    private static final String DEFAULT_EMAIL = "AAAAAAAAAA";
    private static final String UPDATED_EMAIL = "BBBBBBBBBB";

    private static final String DEFAULT_FAX = "AAAAAAAAAA";
    private static final String UPDATED_FAX = "BBBBBBBBBB";

    private static final String DEFAULT_DIA_CHI = "AAAAAAAAAA";
    private static final String UPDATED_DIA_CHI = "BBBBBBBBBB";

    @Autowired
    private OfficerRepository officerRepository;

    @Autowired
    private OfficerService officerService;

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

    private MockMvc restOfficerMockMvc;

    private Officer officer;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final OfficerResource officerResource = new OfficerResource(officerService);
        this.restOfficerMockMvc = MockMvcBuilders.standaloneSetup(officerResource)
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
    public static Officer createEntity(EntityManager em) {
        Officer officer = new Officer()
            .tenNhanSu(DEFAULT_TEN_NHAN_SU)
            .dienThoai(DEFAULT_DIEN_THOAI)
            .email(DEFAULT_EMAIL)
            .fax(DEFAULT_FAX)
            .diaChi(DEFAULT_DIA_CHI);
        return officer;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Officer createUpdatedEntity(EntityManager em) {
        Officer officer = new Officer()
            .tenNhanSu(UPDATED_TEN_NHAN_SU)
            .dienThoai(UPDATED_DIEN_THOAI)
            .email(UPDATED_EMAIL)
            .fax(UPDATED_FAX)
            .diaChi(UPDATED_DIA_CHI);
        return officer;
    }

    @BeforeEach
    public void initTest() {
        officer = createEntity(em);
    }

    @Test
    @Transactional
    public void createOfficer() throws Exception {
        int databaseSizeBeforeCreate = officerRepository.findAll().size();

        // Create the Officer
        restOfficerMockMvc.perform(post("/api/officers")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(officer)))
            .andExpect(status().isCreated());

        // Validate the Officer in the database
        List<Officer> officerList = officerRepository.findAll();
        assertThat(officerList).hasSize(databaseSizeBeforeCreate + 1);
        Officer testOfficer = officerList.get(officerList.size() - 1);
        assertThat(testOfficer.getTenNhanSu()).isEqualTo(DEFAULT_TEN_NHAN_SU);
        assertThat(testOfficer.getDienThoai()).isEqualTo(DEFAULT_DIEN_THOAI);
        assertThat(testOfficer.getEmail()).isEqualTo(DEFAULT_EMAIL);
        assertThat(testOfficer.getFax()).isEqualTo(DEFAULT_FAX);
        assertThat(testOfficer.getDiaChi()).isEqualTo(DEFAULT_DIA_CHI);
    }

    @Test
    @Transactional
    public void createOfficerWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = officerRepository.findAll().size();

        // Create the Officer with an existing ID
        officer.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restOfficerMockMvc.perform(post("/api/officers")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(officer)))
            .andExpect(status().isBadRequest());

        // Validate the Officer in the database
        List<Officer> officerList = officerRepository.findAll();
        assertThat(officerList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void checkTenNhanSuIsRequired() throws Exception {
        int databaseSizeBeforeTest = officerRepository.findAll().size();
        // set the field null
        officer.setTenNhanSu(null);

        // Create the Officer, which fails.

        restOfficerMockMvc.perform(post("/api/officers")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(officer)))
            .andExpect(status().isBadRequest());

        List<Officer> officerList = officerRepository.findAll();
        assertThat(officerList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkDienThoaiIsRequired() throws Exception {
        int databaseSizeBeforeTest = officerRepository.findAll().size();
        // set the field null
        officer.setDienThoai(null);

        // Create the Officer, which fails.

        restOfficerMockMvc.perform(post("/api/officers")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(officer)))
            .andExpect(status().isBadRequest());

        List<Officer> officerList = officerRepository.findAll();
        assertThat(officerList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkEmailIsRequired() throws Exception {
        int databaseSizeBeforeTest = officerRepository.findAll().size();
        // set the field null
        officer.setEmail(null);

        // Create the Officer, which fails.

        restOfficerMockMvc.perform(post("/api/officers")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(officer)))
            .andExpect(status().isBadRequest());

        List<Officer> officerList = officerRepository.findAll();
        assertThat(officerList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkDiaChiIsRequired() throws Exception {
        int databaseSizeBeforeTest = officerRepository.findAll().size();
        // set the field null
        officer.setDiaChi(null);

        // Create the Officer, which fails.

        restOfficerMockMvc.perform(post("/api/officers")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(officer)))
            .andExpect(status().isBadRequest());

        List<Officer> officerList = officerRepository.findAll();
        assertThat(officerList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllOfficers() throws Exception {
        // Initialize the database
        officerRepository.saveAndFlush(officer);

        // Get all the officerList
        restOfficerMockMvc.perform(get("/api/officers?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(officer.getId().intValue())))
            .andExpect(jsonPath("$.[*].tenNhanSu").value(hasItem(DEFAULT_TEN_NHAN_SU)))
            .andExpect(jsonPath("$.[*].dienThoai").value(hasItem(DEFAULT_DIEN_THOAI)))
            .andExpect(jsonPath("$.[*].email").value(hasItem(DEFAULT_EMAIL)))
            .andExpect(jsonPath("$.[*].fax").value(hasItem(DEFAULT_FAX)))
            .andExpect(jsonPath("$.[*].diaChi").value(hasItem(DEFAULT_DIA_CHI)));
    }
    
    @Test
    @Transactional
    public void getOfficer() throws Exception {
        // Initialize the database
        officerRepository.saveAndFlush(officer);

        // Get the officer
        restOfficerMockMvc.perform(get("/api/officers/{id}", officer.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(officer.getId().intValue()))
            .andExpect(jsonPath("$.tenNhanSu").value(DEFAULT_TEN_NHAN_SU))
            .andExpect(jsonPath("$.dienThoai").value(DEFAULT_DIEN_THOAI))
            .andExpect(jsonPath("$.email").value(DEFAULT_EMAIL))
            .andExpect(jsonPath("$.fax").value(DEFAULT_FAX))
            .andExpect(jsonPath("$.diaChi").value(DEFAULT_DIA_CHI));
    }

    @Test
    @Transactional
    public void getNonExistingOfficer() throws Exception {
        // Get the officer
        restOfficerMockMvc.perform(get("/api/officers/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateOfficer() throws Exception {
        // Initialize the database
        officerService.save(officer);

        int databaseSizeBeforeUpdate = officerRepository.findAll().size();

        // Update the officer
        Officer updatedOfficer = officerRepository.findById(officer.getId()).get();
        // Disconnect from session so that the updates on updatedOfficer are not directly saved in db
        em.detach(updatedOfficer);
        updatedOfficer
            .tenNhanSu(UPDATED_TEN_NHAN_SU)
            .dienThoai(UPDATED_DIEN_THOAI)
            .email(UPDATED_EMAIL)
            .fax(UPDATED_FAX)
            .diaChi(UPDATED_DIA_CHI);

        restOfficerMockMvc.perform(put("/api/officers")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(updatedOfficer)))
            .andExpect(status().isOk());

        // Validate the Officer in the database
        List<Officer> officerList = officerRepository.findAll();
        assertThat(officerList).hasSize(databaseSizeBeforeUpdate);
        Officer testOfficer = officerList.get(officerList.size() - 1);
        assertThat(testOfficer.getTenNhanSu()).isEqualTo(UPDATED_TEN_NHAN_SU);
        assertThat(testOfficer.getDienThoai()).isEqualTo(UPDATED_DIEN_THOAI);
        assertThat(testOfficer.getEmail()).isEqualTo(UPDATED_EMAIL);
        assertThat(testOfficer.getFax()).isEqualTo(UPDATED_FAX);
        assertThat(testOfficer.getDiaChi()).isEqualTo(UPDATED_DIA_CHI);
    }

    @Test
    @Transactional
    public void updateNonExistingOfficer() throws Exception {
        int databaseSizeBeforeUpdate = officerRepository.findAll().size();

        // Create the Officer

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restOfficerMockMvc.perform(put("/api/officers")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(officer)))
            .andExpect(status().isBadRequest());

        // Validate the Officer in the database
        List<Officer> officerList = officerRepository.findAll();
        assertThat(officerList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteOfficer() throws Exception {
        // Initialize the database
        officerService.save(officer);

        int databaseSizeBeforeDelete = officerRepository.findAll().size();

        // Delete the officer
        restOfficerMockMvc.perform(delete("/api/officers/{id}", officer.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<Officer> officerList = officerRepository.findAll();
        assertThat(officerList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
