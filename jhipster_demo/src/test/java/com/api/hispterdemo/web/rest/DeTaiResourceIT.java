package com.api.hispterdemo.web.rest;

import com.api.hispterdemo.JhispterDemoApp;
import com.api.hispterdemo.domain.DeTai;
import com.api.hispterdemo.repository.DeTaiRepository;
import com.api.hispterdemo.service.DeTaiService;
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
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.List;

import static com.api.hispterdemo.web.rest.TestUtil.createFormattingConversionService;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Integration tests for the {@link DeTaiResource} REST controller.
 */
@SpringBootTest(classes = JhispterDemoApp.class)
public class DeTaiResourceIT {

    private static final String DEFAULT_MA_DE_TAI = "AAAAAAAAAA";
    private static final String UPDATED_MA_DE_TAI = "BBBBBBBBBB";

    private static final String DEFAULT_TEN_DE_TAI = "AAAAAAAAAA";
    private static final String UPDATED_TEN_DE_TAI = "BBBBBBBBBB";

    private static final String DEFAULT_MUC_TIEU = "AAAAAAAAAA";
    private static final String UPDATED_MUC_TIEU = "BBBBBBBBBB";

    private static final LocalDate DEFAULT_NGAY_BD_DU_KIEN = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_NGAY_BD_DU_KIEN = LocalDate.now(ZoneId.systemDefault());

    private static final LocalDate DEFAULT_NGAY_KT_DU_KIEN = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_NGAY_KT_DU_KIEN = LocalDate.now(ZoneId.systemDefault());

    private static final Double DEFAULT_KINH_PHI_DU_KIEN = 1D;
    private static final Double UPDATED_KINH_PHI_DU_KIEN = 2D;

    private static final String DEFAULT_NOI_DUNG_TONG_QUAN = "AAAAAAAAAA";
    private static final String UPDATED_NOI_DUNG_TONG_QUAN = "BBBBBBBBBB";

    private static final Double DEFAULT_KINH_PHI_HOAN_THANH = 1D;
    private static final Double UPDATED_KINH_PHI_HOAN_THANH = 2D;

    private static final Double DEFAULT_TONG_DIEM = 1D;
    private static final Double UPDATED_TONG_DIEM = 2D;

    @Autowired
    private DeTaiRepository deTaiRepository;

    @Autowired
    private DeTaiService deTaiService;

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

    private MockMvc restDeTaiMockMvc;

    private DeTai deTai;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final DeTaiResource deTaiResource = new DeTaiResource(deTaiService);
        this.restDeTaiMockMvc = MockMvcBuilders.standaloneSetup(deTaiResource)
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
    public static DeTai createEntity(EntityManager em) {
        DeTai deTai = new DeTai()
            .maDeTai(DEFAULT_MA_DE_TAI)
            .tenDeTai(DEFAULT_TEN_DE_TAI)
            .mucTieu(DEFAULT_MUC_TIEU)
            .ngayBDDuKien(DEFAULT_NGAY_BD_DU_KIEN)
            .ngayKTDuKien(DEFAULT_NGAY_KT_DU_KIEN)
            .kinhPhiDuKien(DEFAULT_KINH_PHI_DU_KIEN)
            .noiDungTongQuan(DEFAULT_NOI_DUNG_TONG_QUAN)
            .kinhPhiHoanThanh(DEFAULT_KINH_PHI_HOAN_THANH)
            .tongDiem(DEFAULT_TONG_DIEM);
        return deTai;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static DeTai createUpdatedEntity(EntityManager em) {
        DeTai deTai = new DeTai()
            .maDeTai(UPDATED_MA_DE_TAI)
            .tenDeTai(UPDATED_TEN_DE_TAI)
            .mucTieu(UPDATED_MUC_TIEU)
            .ngayBDDuKien(UPDATED_NGAY_BD_DU_KIEN)
            .ngayKTDuKien(UPDATED_NGAY_KT_DU_KIEN)
            .kinhPhiDuKien(UPDATED_KINH_PHI_DU_KIEN)
            .noiDungTongQuan(UPDATED_NOI_DUNG_TONG_QUAN)
            .kinhPhiHoanThanh(UPDATED_KINH_PHI_HOAN_THANH)
            .tongDiem(UPDATED_TONG_DIEM);
        return deTai;
    }

    @BeforeEach
    public void initTest() {
        deTai = createEntity(em);
    }

    @Test
    @Transactional
    public void createDeTai() throws Exception {
        int databaseSizeBeforeCreate = deTaiRepository.findAll().size();

        // Create the DeTai
        restDeTaiMockMvc.perform(post("/api/de-tais")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(deTai)))
            .andExpect(status().isCreated());

        // Validate the DeTai in the database
        List<DeTai> deTaiList = deTaiRepository.findAll();
        assertThat(deTaiList).hasSize(databaseSizeBeforeCreate + 1);
        DeTai testDeTai = deTaiList.get(deTaiList.size() - 1);
        assertThat(testDeTai.getMaDeTai()).isEqualTo(DEFAULT_MA_DE_TAI);
        assertThat(testDeTai.getTenDeTai()).isEqualTo(DEFAULT_TEN_DE_TAI);
        assertThat(testDeTai.getMucTieu()).isEqualTo(DEFAULT_MUC_TIEU);
        assertThat(testDeTai.getNgayBDDuKien()).isEqualTo(DEFAULT_NGAY_BD_DU_KIEN);
        assertThat(testDeTai.getNgayKTDuKien()).isEqualTo(DEFAULT_NGAY_KT_DU_KIEN);
        assertThat(testDeTai.getKinhPhiDuKien()).isEqualTo(DEFAULT_KINH_PHI_DU_KIEN);
        assertThat(testDeTai.getNoiDungTongQuan()).isEqualTo(DEFAULT_NOI_DUNG_TONG_QUAN);
        assertThat(testDeTai.getKinhPhiHoanThanh()).isEqualTo(DEFAULT_KINH_PHI_HOAN_THANH);
        assertThat(testDeTai.getTongDiem()).isEqualTo(DEFAULT_TONG_DIEM);
    }

    @Test
    @Transactional
    public void createDeTaiWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = deTaiRepository.findAll().size();

        // Create the DeTai with an existing ID
        deTai.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restDeTaiMockMvc.perform(post("/api/de-tais")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(deTai)))
            .andExpect(status().isBadRequest());

        // Validate the DeTai in the database
        List<DeTai> deTaiList = deTaiRepository.findAll();
        assertThat(deTaiList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void checkMaDeTaiIsRequired() throws Exception {
        int databaseSizeBeforeTest = deTaiRepository.findAll().size();
        // set the field null
        deTai.setMaDeTai(null);

        // Create the DeTai, which fails.

        restDeTaiMockMvc.perform(post("/api/de-tais")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(deTai)))
            .andExpect(status().isBadRequest());

        List<DeTai> deTaiList = deTaiRepository.findAll();
        assertThat(deTaiList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkTenDeTaiIsRequired() throws Exception {
        int databaseSizeBeforeTest = deTaiRepository.findAll().size();
        // set the field null
        deTai.setTenDeTai(null);

        // Create the DeTai, which fails.

        restDeTaiMockMvc.perform(post("/api/de-tais")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(deTai)))
            .andExpect(status().isBadRequest());

        List<DeTai> deTaiList = deTaiRepository.findAll();
        assertThat(deTaiList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkMucTieuIsRequired() throws Exception {
        int databaseSizeBeforeTest = deTaiRepository.findAll().size();
        // set the field null
        deTai.setMucTieu(null);

        // Create the DeTai, which fails.

        restDeTaiMockMvc.perform(post("/api/de-tais")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(deTai)))
            .andExpect(status().isBadRequest());

        List<DeTai> deTaiList = deTaiRepository.findAll();
        assertThat(deTaiList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkNgayBDDuKienIsRequired() throws Exception {
        int databaseSizeBeforeTest = deTaiRepository.findAll().size();
        // set the field null
        deTai.setNgayBDDuKien(null);

        // Create the DeTai, which fails.

        restDeTaiMockMvc.perform(post("/api/de-tais")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(deTai)))
            .andExpect(status().isBadRequest());

        List<DeTai> deTaiList = deTaiRepository.findAll();
        assertThat(deTaiList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkNgayKTDuKienIsRequired() throws Exception {
        int databaseSizeBeforeTest = deTaiRepository.findAll().size();
        // set the field null
        deTai.setNgayKTDuKien(null);

        // Create the DeTai, which fails.

        restDeTaiMockMvc.perform(post("/api/de-tais")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(deTai)))
            .andExpect(status().isBadRequest());

        List<DeTai> deTaiList = deTaiRepository.findAll();
        assertThat(deTaiList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkKinhPhiDuKienIsRequired() throws Exception {
        int databaseSizeBeforeTest = deTaiRepository.findAll().size();
        // set the field null
        deTai.setKinhPhiDuKien(null);

        // Create the DeTai, which fails.

        restDeTaiMockMvc.perform(post("/api/de-tais")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(deTai)))
            .andExpect(status().isBadRequest());

        List<DeTai> deTaiList = deTaiRepository.findAll();
        assertThat(deTaiList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkNoiDungTongQuanIsRequired() throws Exception {
        int databaseSizeBeforeTest = deTaiRepository.findAll().size();
        // set the field null
        deTai.setNoiDungTongQuan(null);

        // Create the DeTai, which fails.

        restDeTaiMockMvc.perform(post("/api/de-tais")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(deTai)))
            .andExpect(status().isBadRequest());

        List<DeTai> deTaiList = deTaiRepository.findAll();
        assertThat(deTaiList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllDeTais() throws Exception {
        // Initialize the database
        deTaiRepository.saveAndFlush(deTai);

        // Get all the deTaiList
        restDeTaiMockMvc.perform(get("/api/de-tais?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(deTai.getId().intValue())))
            .andExpect(jsonPath("$.[*].maDeTai").value(hasItem(DEFAULT_MA_DE_TAI)))
            .andExpect(jsonPath("$.[*].tenDeTai").value(hasItem(DEFAULT_TEN_DE_TAI)))
            .andExpect(jsonPath("$.[*].mucTieu").value(hasItem(DEFAULT_MUC_TIEU)))
            .andExpect(jsonPath("$.[*].ngayBDDuKien").value(hasItem(DEFAULT_NGAY_BD_DU_KIEN.toString())))
            .andExpect(jsonPath("$.[*].ngayKTDuKien").value(hasItem(DEFAULT_NGAY_KT_DU_KIEN.toString())))
            .andExpect(jsonPath("$.[*].kinhPhiDuKien").value(hasItem(DEFAULT_KINH_PHI_DU_KIEN.doubleValue())))
            .andExpect(jsonPath("$.[*].noiDungTongQuan").value(hasItem(DEFAULT_NOI_DUNG_TONG_QUAN)))
            .andExpect(jsonPath("$.[*].kinhPhiHoanThanh").value(hasItem(DEFAULT_KINH_PHI_HOAN_THANH.doubleValue())))
            .andExpect(jsonPath("$.[*].tongDiem").value(hasItem(DEFAULT_TONG_DIEM.doubleValue())));
    }
    
    @Test
    @Transactional
    public void getDeTai() throws Exception {
        // Initialize the database
        deTaiRepository.saveAndFlush(deTai);

        // Get the deTai
        restDeTaiMockMvc.perform(get("/api/de-tais/{id}", deTai.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(deTai.getId().intValue()))
            .andExpect(jsonPath("$.maDeTai").value(DEFAULT_MA_DE_TAI))
            .andExpect(jsonPath("$.tenDeTai").value(DEFAULT_TEN_DE_TAI))
            .andExpect(jsonPath("$.mucTieu").value(DEFAULT_MUC_TIEU))
            .andExpect(jsonPath("$.ngayBDDuKien").value(DEFAULT_NGAY_BD_DU_KIEN.toString()))
            .andExpect(jsonPath("$.ngayKTDuKien").value(DEFAULT_NGAY_KT_DU_KIEN.toString()))
            .andExpect(jsonPath("$.kinhPhiDuKien").value(DEFAULT_KINH_PHI_DU_KIEN.doubleValue()))
            .andExpect(jsonPath("$.noiDungTongQuan").value(DEFAULT_NOI_DUNG_TONG_QUAN))
            .andExpect(jsonPath("$.kinhPhiHoanThanh").value(DEFAULT_KINH_PHI_HOAN_THANH.doubleValue()))
            .andExpect(jsonPath("$.tongDiem").value(DEFAULT_TONG_DIEM.doubleValue()));
    }

    @Test
    @Transactional
    public void getNonExistingDeTai() throws Exception {
        // Get the deTai
        restDeTaiMockMvc.perform(get("/api/de-tais/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateDeTai() throws Exception {
        // Initialize the database
        deTaiService.save(deTai);

        int databaseSizeBeforeUpdate = deTaiRepository.findAll().size();

        // Update the deTai
        DeTai updatedDeTai = deTaiRepository.findById(deTai.getId()).get();
        // Disconnect from session so that the updates on updatedDeTai are not directly saved in db
        em.detach(updatedDeTai);
        updatedDeTai
            .maDeTai(UPDATED_MA_DE_TAI)
            .tenDeTai(UPDATED_TEN_DE_TAI)
            .mucTieu(UPDATED_MUC_TIEU)
            .ngayBDDuKien(UPDATED_NGAY_BD_DU_KIEN)
            .ngayKTDuKien(UPDATED_NGAY_KT_DU_KIEN)
            .kinhPhiDuKien(UPDATED_KINH_PHI_DU_KIEN)
            .noiDungTongQuan(UPDATED_NOI_DUNG_TONG_QUAN)
            .kinhPhiHoanThanh(UPDATED_KINH_PHI_HOAN_THANH)
            .tongDiem(UPDATED_TONG_DIEM);

        restDeTaiMockMvc.perform(put("/api/de-tais")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(updatedDeTai)))
            .andExpect(status().isOk());

        // Validate the DeTai in the database
        List<DeTai> deTaiList = deTaiRepository.findAll();
        assertThat(deTaiList).hasSize(databaseSizeBeforeUpdate);
        DeTai testDeTai = deTaiList.get(deTaiList.size() - 1);
        assertThat(testDeTai.getMaDeTai()).isEqualTo(UPDATED_MA_DE_TAI);
        assertThat(testDeTai.getTenDeTai()).isEqualTo(UPDATED_TEN_DE_TAI);
        assertThat(testDeTai.getMucTieu()).isEqualTo(UPDATED_MUC_TIEU);
        assertThat(testDeTai.getNgayBDDuKien()).isEqualTo(UPDATED_NGAY_BD_DU_KIEN);
        assertThat(testDeTai.getNgayKTDuKien()).isEqualTo(UPDATED_NGAY_KT_DU_KIEN);
        assertThat(testDeTai.getKinhPhiDuKien()).isEqualTo(UPDATED_KINH_PHI_DU_KIEN);
        assertThat(testDeTai.getNoiDungTongQuan()).isEqualTo(UPDATED_NOI_DUNG_TONG_QUAN);
        assertThat(testDeTai.getKinhPhiHoanThanh()).isEqualTo(UPDATED_KINH_PHI_HOAN_THANH);
        assertThat(testDeTai.getTongDiem()).isEqualTo(UPDATED_TONG_DIEM);
    }

    @Test
    @Transactional
    public void updateNonExistingDeTai() throws Exception {
        int databaseSizeBeforeUpdate = deTaiRepository.findAll().size();

        // Create the DeTai

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restDeTaiMockMvc.perform(put("/api/de-tais")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(deTai)))
            .andExpect(status().isBadRequest());

        // Validate the DeTai in the database
        List<DeTai> deTaiList = deTaiRepository.findAll();
        assertThat(deTaiList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteDeTai() throws Exception {
        // Initialize the database
        deTaiService.save(deTai);

        int databaseSizeBeforeDelete = deTaiRepository.findAll().size();

        // Delete the deTai
        restDeTaiMockMvc.perform(delete("/api/de-tais/{id}", deTai.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<DeTai> deTaiList = deTaiRepository.findAll();
        assertThat(deTaiList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
