package com.api.hispterdemo.web.rest;

import com.api.hispterdemo.JhispterDemoApp;
import com.api.hispterdemo.domain.DanhGia;
import com.api.hispterdemo.repository.DanhGiaRepository;
import com.api.hispterdemo.service.DanhGiaService;
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
 * Integration tests for the {@link DanhGiaResource} REST controller.
 */
@SpringBootTest(classes = JhispterDemoApp.class)
public class DanhGiaResourceIT {

    private static final Integer DEFAULT_LOAI_DANH_GIA = 1;
    private static final Integer UPDATED_LOAI_DANH_GIA = 2;

    private static final Integer DEFAULT_DIEM_DANH_GIA = 1;
    private static final Integer UPDATED_DIEM_DANH_GIA = 2;

    @Autowired
    private DanhGiaRepository danhGiaRepository;

    @Autowired
    private DanhGiaService danhGiaService;

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

    private MockMvc restDanhGiaMockMvc;

    private DanhGia danhGia;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final DanhGiaResource danhGiaResource = new DanhGiaResource(danhGiaService);
        this.restDanhGiaMockMvc = MockMvcBuilders.standaloneSetup(danhGiaResource)
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
    public static DanhGia createEntity(EntityManager em) {
        DanhGia danhGia = new DanhGia()
            .loaiDanhGia(DEFAULT_LOAI_DANH_GIA)
            .diemDanhGia(DEFAULT_DIEM_DANH_GIA);
        return danhGia;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static DanhGia createUpdatedEntity(EntityManager em) {
        DanhGia danhGia = new DanhGia()
            .loaiDanhGia(UPDATED_LOAI_DANH_GIA)
            .diemDanhGia(UPDATED_DIEM_DANH_GIA);
        return danhGia;
    }

    @BeforeEach
    public void initTest() {
        danhGia = createEntity(em);
    }

    @Test
    @Transactional
    public void createDanhGia() throws Exception {
        int databaseSizeBeforeCreate = danhGiaRepository.findAll().size();

        // Create the DanhGia
        restDanhGiaMockMvc.perform(post("/api/danh-gias")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(danhGia)))
            .andExpect(status().isCreated());

        // Validate the DanhGia in the database
        List<DanhGia> danhGiaList = danhGiaRepository.findAll();
        assertThat(danhGiaList).hasSize(databaseSizeBeforeCreate + 1);
        DanhGia testDanhGia = danhGiaList.get(danhGiaList.size() - 1);
        assertThat(testDanhGia.getLoaiDanhGia()).isEqualTo(DEFAULT_LOAI_DANH_GIA);
        assertThat(testDanhGia.getDiemDanhGia()).isEqualTo(DEFAULT_DIEM_DANH_GIA);
    }

    @Test
    @Transactional
    public void createDanhGiaWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = danhGiaRepository.findAll().size();

        // Create the DanhGia with an existing ID
        danhGia.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restDanhGiaMockMvc.perform(post("/api/danh-gias")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(danhGia)))
            .andExpect(status().isBadRequest());

        // Validate the DanhGia in the database
        List<DanhGia> danhGiaList = danhGiaRepository.findAll();
        assertThat(danhGiaList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void checkLoaiDanhGiaIsRequired() throws Exception {
        int databaseSizeBeforeTest = danhGiaRepository.findAll().size();
        // set the field null
        danhGia.setLoaiDanhGia(null);

        // Create the DanhGia, which fails.

        restDanhGiaMockMvc.perform(post("/api/danh-gias")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(danhGia)))
            .andExpect(status().isBadRequest());

        List<DanhGia> danhGiaList = danhGiaRepository.findAll();
        assertThat(danhGiaList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkDiemDanhGiaIsRequired() throws Exception {
        int databaseSizeBeforeTest = danhGiaRepository.findAll().size();
        // set the field null
        danhGia.setDiemDanhGia(null);

        // Create the DanhGia, which fails.

        restDanhGiaMockMvc.perform(post("/api/danh-gias")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(danhGia)))
            .andExpect(status().isBadRequest());

        List<DanhGia> danhGiaList = danhGiaRepository.findAll();
        assertThat(danhGiaList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllDanhGias() throws Exception {
        // Initialize the database
        danhGiaRepository.saveAndFlush(danhGia);

        // Get all the danhGiaList
        restDanhGiaMockMvc.perform(get("/api/danh-gias?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(danhGia.getId().intValue())))
            .andExpect(jsonPath("$.[*].loaiDanhGia").value(hasItem(DEFAULT_LOAI_DANH_GIA)))
            .andExpect(jsonPath("$.[*].diemDanhGia").value(hasItem(DEFAULT_DIEM_DANH_GIA)));
    }
    
    @Test
    @Transactional
    public void getDanhGia() throws Exception {
        // Initialize the database
        danhGiaRepository.saveAndFlush(danhGia);

        // Get the danhGia
        restDanhGiaMockMvc.perform(get("/api/danh-gias/{id}", danhGia.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(danhGia.getId().intValue()))
            .andExpect(jsonPath("$.loaiDanhGia").value(DEFAULT_LOAI_DANH_GIA))
            .andExpect(jsonPath("$.diemDanhGia").value(DEFAULT_DIEM_DANH_GIA));
    }

    @Test
    @Transactional
    public void getNonExistingDanhGia() throws Exception {
        // Get the danhGia
        restDanhGiaMockMvc.perform(get("/api/danh-gias/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateDanhGia() throws Exception {
        // Initialize the database
        danhGiaService.save(danhGia);

        int databaseSizeBeforeUpdate = danhGiaRepository.findAll().size();

        // Update the danhGia
        DanhGia updatedDanhGia = danhGiaRepository.findById(danhGia.getId()).get();
        // Disconnect from session so that the updates on updatedDanhGia are not directly saved in db
        em.detach(updatedDanhGia);
        updatedDanhGia
            .loaiDanhGia(UPDATED_LOAI_DANH_GIA)
            .diemDanhGia(UPDATED_DIEM_DANH_GIA);

        restDanhGiaMockMvc.perform(put("/api/danh-gias")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(updatedDanhGia)))
            .andExpect(status().isOk());

        // Validate the DanhGia in the database
        List<DanhGia> danhGiaList = danhGiaRepository.findAll();
        assertThat(danhGiaList).hasSize(databaseSizeBeforeUpdate);
        DanhGia testDanhGia = danhGiaList.get(danhGiaList.size() - 1);
        assertThat(testDanhGia.getLoaiDanhGia()).isEqualTo(UPDATED_LOAI_DANH_GIA);
        assertThat(testDanhGia.getDiemDanhGia()).isEqualTo(UPDATED_DIEM_DANH_GIA);
    }

    @Test
    @Transactional
    public void updateNonExistingDanhGia() throws Exception {
        int databaseSizeBeforeUpdate = danhGiaRepository.findAll().size();

        // Create the DanhGia

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restDanhGiaMockMvc.perform(put("/api/danh-gias")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(danhGia)))
            .andExpect(status().isBadRequest());

        // Validate the DanhGia in the database
        List<DanhGia> danhGiaList = danhGiaRepository.findAll();
        assertThat(danhGiaList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteDanhGia() throws Exception {
        // Initialize the database
        danhGiaService.save(danhGia);

        int databaseSizeBeforeDelete = danhGiaRepository.findAll().size();

        // Delete the danhGia
        restDanhGiaMockMvc.perform(delete("/api/danh-gias/{id}", danhGia.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<DanhGia> danhGiaList = danhGiaRepository.findAll();
        assertThat(danhGiaList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
