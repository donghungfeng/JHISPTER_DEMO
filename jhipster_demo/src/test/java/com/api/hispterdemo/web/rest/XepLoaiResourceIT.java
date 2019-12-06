package com.api.hispterdemo.web.rest;

import com.api.hispterdemo.JhispterDemoApp;
import com.api.hispterdemo.domain.XepLoai;
import com.api.hispterdemo.repository.XepLoaiRepository;
import com.api.hispterdemo.service.XepLoaiService;
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
 * Integration tests for the {@link XepLoaiResource} REST controller.
 */
@SpringBootTest(classes = JhispterDemoApp.class)
public class XepLoaiResourceIT {

    private static final String DEFAULT_TEN_XEP_LOAI = "AAAAAAAAAA";
    private static final String UPDATED_TEN_XEP_LOAI = "BBBBBBBBBB";

    @Autowired
    private XepLoaiRepository xepLoaiRepository;

    @Autowired
    private XepLoaiService xepLoaiService;

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

    private MockMvc restXepLoaiMockMvc;

    private XepLoai xepLoai;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final XepLoaiResource xepLoaiResource = new XepLoaiResource(xepLoaiService);
        this.restXepLoaiMockMvc = MockMvcBuilders.standaloneSetup(xepLoaiResource)
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
    public static XepLoai createEntity(EntityManager em) {
        XepLoai xepLoai = new XepLoai()
            .tenXepLoai(DEFAULT_TEN_XEP_LOAI);
        return xepLoai;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static XepLoai createUpdatedEntity(EntityManager em) {
        XepLoai xepLoai = new XepLoai()
            .tenXepLoai(UPDATED_TEN_XEP_LOAI);
        return xepLoai;
    }

    @BeforeEach
    public void initTest() {
        xepLoai = createEntity(em);
    }

    @Test
    @Transactional
    public void createXepLoai() throws Exception {
        int databaseSizeBeforeCreate = xepLoaiRepository.findAll().size();

        // Create the XepLoai
        restXepLoaiMockMvc.perform(post("/api/xep-loais")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(xepLoai)))
            .andExpect(status().isCreated());

        // Validate the XepLoai in the database
        List<XepLoai> xepLoaiList = xepLoaiRepository.findAll();
        assertThat(xepLoaiList).hasSize(databaseSizeBeforeCreate + 1);
        XepLoai testXepLoai = xepLoaiList.get(xepLoaiList.size() - 1);
        assertThat(testXepLoai.getTenXepLoai()).isEqualTo(DEFAULT_TEN_XEP_LOAI);
    }

    @Test
    @Transactional
    public void createXepLoaiWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = xepLoaiRepository.findAll().size();

        // Create the XepLoai with an existing ID
        xepLoai.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restXepLoaiMockMvc.perform(post("/api/xep-loais")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(xepLoai)))
            .andExpect(status().isBadRequest());

        // Validate the XepLoai in the database
        List<XepLoai> xepLoaiList = xepLoaiRepository.findAll();
        assertThat(xepLoaiList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void checkTenXepLoaiIsRequired() throws Exception {
        int databaseSizeBeforeTest = xepLoaiRepository.findAll().size();
        // set the field null
        xepLoai.setTenXepLoai(null);

        // Create the XepLoai, which fails.

        restXepLoaiMockMvc.perform(post("/api/xep-loais")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(xepLoai)))
            .andExpect(status().isBadRequest());

        List<XepLoai> xepLoaiList = xepLoaiRepository.findAll();
        assertThat(xepLoaiList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllXepLoais() throws Exception {
        // Initialize the database
        xepLoaiRepository.saveAndFlush(xepLoai);

        // Get all the xepLoaiList
        restXepLoaiMockMvc.perform(get("/api/xep-loais?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(xepLoai.getId().intValue())))
            .andExpect(jsonPath("$.[*].tenXepLoai").value(hasItem(DEFAULT_TEN_XEP_LOAI)));
    }
    
    @Test
    @Transactional
    public void getXepLoai() throws Exception {
        // Initialize the database
        xepLoaiRepository.saveAndFlush(xepLoai);

        // Get the xepLoai
        restXepLoaiMockMvc.perform(get("/api/xep-loais/{id}", xepLoai.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(xepLoai.getId().intValue()))
            .andExpect(jsonPath("$.tenXepLoai").value(DEFAULT_TEN_XEP_LOAI));
    }

    @Test
    @Transactional
    public void getNonExistingXepLoai() throws Exception {
        // Get the xepLoai
        restXepLoaiMockMvc.perform(get("/api/xep-loais/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateXepLoai() throws Exception {
        // Initialize the database
        xepLoaiService.save(xepLoai);

        int databaseSizeBeforeUpdate = xepLoaiRepository.findAll().size();

        // Update the xepLoai
        XepLoai updatedXepLoai = xepLoaiRepository.findById(xepLoai.getId()).get();
        // Disconnect from session so that the updates on updatedXepLoai are not directly saved in db
        em.detach(updatedXepLoai);
        updatedXepLoai
            .tenXepLoai(UPDATED_TEN_XEP_LOAI);

        restXepLoaiMockMvc.perform(put("/api/xep-loais")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(updatedXepLoai)))
            .andExpect(status().isOk());

        // Validate the XepLoai in the database
        List<XepLoai> xepLoaiList = xepLoaiRepository.findAll();
        assertThat(xepLoaiList).hasSize(databaseSizeBeforeUpdate);
        XepLoai testXepLoai = xepLoaiList.get(xepLoaiList.size() - 1);
        assertThat(testXepLoai.getTenXepLoai()).isEqualTo(UPDATED_TEN_XEP_LOAI);
    }

    @Test
    @Transactional
    public void updateNonExistingXepLoai() throws Exception {
        int databaseSizeBeforeUpdate = xepLoaiRepository.findAll().size();

        // Create the XepLoai

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restXepLoaiMockMvc.perform(put("/api/xep-loais")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(xepLoai)))
            .andExpect(status().isBadRequest());

        // Validate the XepLoai in the database
        List<XepLoai> xepLoaiList = xepLoaiRepository.findAll();
        assertThat(xepLoaiList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteXepLoai() throws Exception {
        // Initialize the database
        xepLoaiService.save(xepLoai);

        int databaseSizeBeforeDelete = xepLoaiRepository.findAll().size();

        // Delete the xepLoai
        restXepLoaiMockMvc.perform(delete("/api/xep-loais/{id}", xepLoai.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<XepLoai> xepLoaiList = xepLoaiRepository.findAll();
        assertThat(xepLoaiList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
