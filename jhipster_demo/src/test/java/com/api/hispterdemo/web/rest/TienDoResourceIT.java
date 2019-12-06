package com.api.hispterdemo.web.rest;

import com.api.hispterdemo.JhispterDemoApp;
import com.api.hispterdemo.domain.TienDo;
import com.api.hispterdemo.repository.TienDoRepository;
import com.api.hispterdemo.service.TienDoService;
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
 * Integration tests for the {@link TienDoResource} REST controller.
 */
@SpringBootTest(classes = JhispterDemoApp.class)
public class TienDoResourceIT {

    private static final Integer DEFAULT_TIEN_DO_HOAN_THANH = 1;
    private static final Integer UPDATED_TIEN_DO_HOAN_THANH = 2;

    private static final String DEFAULT_MO_TA = "AAAAAAAAAA";
    private static final String UPDATED_MO_TA = "BBBBBBBBBB";

    private static final LocalDate DEFAULT_NGAY_CAP_NHAT = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_NGAY_CAP_NHAT = LocalDate.now(ZoneId.systemDefault());

    @Autowired
    private TienDoRepository tienDoRepository;

    @Autowired
    private TienDoService tienDoService;

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

    private MockMvc restTienDoMockMvc;

    private TienDo tienDo;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final TienDoResource tienDoResource = new TienDoResource(tienDoService);
        this.restTienDoMockMvc = MockMvcBuilders.standaloneSetup(tienDoResource)
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
    public static TienDo createEntity(EntityManager em) {
        TienDo tienDo = new TienDo()
            .tienDoHoanThanh(DEFAULT_TIEN_DO_HOAN_THANH)
            .moTa(DEFAULT_MO_TA)
            .ngayCapNhat(DEFAULT_NGAY_CAP_NHAT);
        return tienDo;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static TienDo createUpdatedEntity(EntityManager em) {
        TienDo tienDo = new TienDo()
            .tienDoHoanThanh(UPDATED_TIEN_DO_HOAN_THANH)
            .moTa(UPDATED_MO_TA)
            .ngayCapNhat(UPDATED_NGAY_CAP_NHAT);
        return tienDo;
    }

    @BeforeEach
    public void initTest() {
        tienDo = createEntity(em);
    }

    @Test
    @Transactional
    public void createTienDo() throws Exception {
        int databaseSizeBeforeCreate = tienDoRepository.findAll().size();

        // Create the TienDo
        restTienDoMockMvc.perform(post("/api/tien-dos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(tienDo)))
            .andExpect(status().isCreated());

        // Validate the TienDo in the database
        List<TienDo> tienDoList = tienDoRepository.findAll();
        assertThat(tienDoList).hasSize(databaseSizeBeforeCreate + 1);
        TienDo testTienDo = tienDoList.get(tienDoList.size() - 1);
        assertThat(testTienDo.getTienDoHoanThanh()).isEqualTo(DEFAULT_TIEN_DO_HOAN_THANH);
        assertThat(testTienDo.getMoTa()).isEqualTo(DEFAULT_MO_TA);
        assertThat(testTienDo.getNgayCapNhat()).isEqualTo(DEFAULT_NGAY_CAP_NHAT);
    }

    @Test
    @Transactional
    public void createTienDoWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = tienDoRepository.findAll().size();

        // Create the TienDo with an existing ID
        tienDo.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restTienDoMockMvc.perform(post("/api/tien-dos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(tienDo)))
            .andExpect(status().isBadRequest());

        // Validate the TienDo in the database
        List<TienDo> tienDoList = tienDoRepository.findAll();
        assertThat(tienDoList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void checkTienDoHoanThanhIsRequired() throws Exception {
        int databaseSizeBeforeTest = tienDoRepository.findAll().size();
        // set the field null
        tienDo.setTienDoHoanThanh(null);

        // Create the TienDo, which fails.

        restTienDoMockMvc.perform(post("/api/tien-dos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(tienDo)))
            .andExpect(status().isBadRequest());

        List<TienDo> tienDoList = tienDoRepository.findAll();
        assertThat(tienDoList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkMoTaIsRequired() throws Exception {
        int databaseSizeBeforeTest = tienDoRepository.findAll().size();
        // set the field null
        tienDo.setMoTa(null);

        // Create the TienDo, which fails.

        restTienDoMockMvc.perform(post("/api/tien-dos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(tienDo)))
            .andExpect(status().isBadRequest());

        List<TienDo> tienDoList = tienDoRepository.findAll();
        assertThat(tienDoList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkNgayCapNhatIsRequired() throws Exception {
        int databaseSizeBeforeTest = tienDoRepository.findAll().size();
        // set the field null
        tienDo.setNgayCapNhat(null);

        // Create the TienDo, which fails.

        restTienDoMockMvc.perform(post("/api/tien-dos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(tienDo)))
            .andExpect(status().isBadRequest());

        List<TienDo> tienDoList = tienDoRepository.findAll();
        assertThat(tienDoList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllTienDos() throws Exception {
        // Initialize the database
        tienDoRepository.saveAndFlush(tienDo);

        // Get all the tienDoList
        restTienDoMockMvc.perform(get("/api/tien-dos?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(tienDo.getId().intValue())))
            .andExpect(jsonPath("$.[*].tienDoHoanThanh").value(hasItem(DEFAULT_TIEN_DO_HOAN_THANH)))
            .andExpect(jsonPath("$.[*].moTa").value(hasItem(DEFAULT_MO_TA)))
            .andExpect(jsonPath("$.[*].ngayCapNhat").value(hasItem(DEFAULT_NGAY_CAP_NHAT.toString())));
    }
    
    @Test
    @Transactional
    public void getTienDo() throws Exception {
        // Initialize the database
        tienDoRepository.saveAndFlush(tienDo);

        // Get the tienDo
        restTienDoMockMvc.perform(get("/api/tien-dos/{id}", tienDo.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(tienDo.getId().intValue()))
            .andExpect(jsonPath("$.tienDoHoanThanh").value(DEFAULT_TIEN_DO_HOAN_THANH))
            .andExpect(jsonPath("$.moTa").value(DEFAULT_MO_TA))
            .andExpect(jsonPath("$.ngayCapNhat").value(DEFAULT_NGAY_CAP_NHAT.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingTienDo() throws Exception {
        // Get the tienDo
        restTienDoMockMvc.perform(get("/api/tien-dos/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateTienDo() throws Exception {
        // Initialize the database
        tienDoService.save(tienDo);

        int databaseSizeBeforeUpdate = tienDoRepository.findAll().size();

        // Update the tienDo
        TienDo updatedTienDo = tienDoRepository.findById(tienDo.getId()).get();
        // Disconnect from session so that the updates on updatedTienDo are not directly saved in db
        em.detach(updatedTienDo);
        updatedTienDo
            .tienDoHoanThanh(UPDATED_TIEN_DO_HOAN_THANH)
            .moTa(UPDATED_MO_TA)
            .ngayCapNhat(UPDATED_NGAY_CAP_NHAT);

        restTienDoMockMvc.perform(put("/api/tien-dos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(updatedTienDo)))
            .andExpect(status().isOk());

        // Validate the TienDo in the database
        List<TienDo> tienDoList = tienDoRepository.findAll();
        assertThat(tienDoList).hasSize(databaseSizeBeforeUpdate);
        TienDo testTienDo = tienDoList.get(tienDoList.size() - 1);
        assertThat(testTienDo.getTienDoHoanThanh()).isEqualTo(UPDATED_TIEN_DO_HOAN_THANH);
        assertThat(testTienDo.getMoTa()).isEqualTo(UPDATED_MO_TA);
        assertThat(testTienDo.getNgayCapNhat()).isEqualTo(UPDATED_NGAY_CAP_NHAT);
    }

    @Test
    @Transactional
    public void updateNonExistingTienDo() throws Exception {
        int databaseSizeBeforeUpdate = tienDoRepository.findAll().size();

        // Create the TienDo

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restTienDoMockMvc.perform(put("/api/tien-dos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(tienDo)))
            .andExpect(status().isBadRequest());

        // Validate the TienDo in the database
        List<TienDo> tienDoList = tienDoRepository.findAll();
        assertThat(tienDoList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteTienDo() throws Exception {
        // Initialize the database
        tienDoService.save(tienDo);

        int databaseSizeBeforeDelete = tienDoRepository.findAll().size();

        // Delete the tienDo
        restTienDoMockMvc.perform(delete("/api/tien-dos/{id}", tienDo.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<TienDo> tienDoList = tienDoRepository.findAll();
        assertThat(tienDoList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
