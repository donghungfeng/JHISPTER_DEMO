package com.api.hispterdemo.web.rest;

import com.api.hispterdemo.JhispterDemoApp;
import com.api.hispterdemo.domain.FileAttach;
import com.api.hispterdemo.repository.FileAttachRepository;
import com.api.hispterdemo.service.FileAttachService;
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
import org.springframework.util.Base64Utils;
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
 * Integration tests for the {@link FileAttachResource} REST controller.
 */
@SpringBootTest(classes = JhispterDemoApp.class)
public class FileAttachResourceIT {

    private static final String DEFAULT_MO_TA = "AAAAAAAAAA";
    private static final String UPDATED_MO_TA = "BBBBBBBBBB";

    private static final byte[] DEFAULT_NOI_DUNG = TestUtil.createByteArray(1, "0");
    private static final byte[] UPDATED_NOI_DUNG = TestUtil.createByteArray(1, "1");
    private static final String DEFAULT_NOI_DUNG_CONTENT_TYPE = "image/jpg";
    private static final String UPDATED_NOI_DUNG_CONTENT_TYPE = "image/png";

    private static final LocalDate DEFAULT_NGAY_CAP_NHAT = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_NGAY_CAP_NHAT = LocalDate.now(ZoneId.systemDefault());

    @Autowired
    private FileAttachRepository fileAttachRepository;

    @Autowired
    private FileAttachService fileAttachService;

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

    private MockMvc restFileAttachMockMvc;

    private FileAttach fileAttach;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final FileAttachResource fileAttachResource = new FileAttachResource(fileAttachService);
        this.restFileAttachMockMvc = MockMvcBuilders.standaloneSetup(fileAttachResource)
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
    public static FileAttach createEntity(EntityManager em) {
        FileAttach fileAttach = new FileAttach()
            .moTa(DEFAULT_MO_TA)
            .noiDung(DEFAULT_NOI_DUNG)
            .noiDungContentType(DEFAULT_NOI_DUNG_CONTENT_TYPE)
            .ngayCapNhat(DEFAULT_NGAY_CAP_NHAT);
        return fileAttach;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static FileAttach createUpdatedEntity(EntityManager em) {
        FileAttach fileAttach = new FileAttach()
            .moTa(UPDATED_MO_TA)
            .noiDung(UPDATED_NOI_DUNG)
            .noiDungContentType(UPDATED_NOI_DUNG_CONTENT_TYPE)
            .ngayCapNhat(UPDATED_NGAY_CAP_NHAT);
        return fileAttach;
    }

    @BeforeEach
    public void initTest() {
        fileAttach = createEntity(em);
    }

    @Test
    @Transactional
    public void createFileAttach() throws Exception {
        int databaseSizeBeforeCreate = fileAttachRepository.findAll().size();

        // Create the FileAttach
        restFileAttachMockMvc.perform(post("/api/file-attaches")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(fileAttach)))
            .andExpect(status().isCreated());

        // Validate the FileAttach in the database
        List<FileAttach> fileAttachList = fileAttachRepository.findAll();
        assertThat(fileAttachList).hasSize(databaseSizeBeforeCreate + 1);
        FileAttach testFileAttach = fileAttachList.get(fileAttachList.size() - 1);
        assertThat(testFileAttach.getMoTa()).isEqualTo(DEFAULT_MO_TA);
        assertThat(testFileAttach.getNoiDung()).isEqualTo(DEFAULT_NOI_DUNG);
        assertThat(testFileAttach.getNoiDungContentType()).isEqualTo(DEFAULT_NOI_DUNG_CONTENT_TYPE);
        assertThat(testFileAttach.getNgayCapNhat()).isEqualTo(DEFAULT_NGAY_CAP_NHAT);
    }

    @Test
    @Transactional
    public void createFileAttachWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = fileAttachRepository.findAll().size();

        // Create the FileAttach with an existing ID
        fileAttach.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restFileAttachMockMvc.perform(post("/api/file-attaches")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(fileAttach)))
            .andExpect(status().isBadRequest());

        // Validate the FileAttach in the database
        List<FileAttach> fileAttachList = fileAttachRepository.findAll();
        assertThat(fileAttachList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void checkMoTaIsRequired() throws Exception {
        int databaseSizeBeforeTest = fileAttachRepository.findAll().size();
        // set the field null
        fileAttach.setMoTa(null);

        // Create the FileAttach, which fails.

        restFileAttachMockMvc.perform(post("/api/file-attaches")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(fileAttach)))
            .andExpect(status().isBadRequest());

        List<FileAttach> fileAttachList = fileAttachRepository.findAll();
        assertThat(fileAttachList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkNgayCapNhatIsRequired() throws Exception {
        int databaseSizeBeforeTest = fileAttachRepository.findAll().size();
        // set the field null
        fileAttach.setNgayCapNhat(null);

        // Create the FileAttach, which fails.

        restFileAttachMockMvc.perform(post("/api/file-attaches")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(fileAttach)))
            .andExpect(status().isBadRequest());

        List<FileAttach> fileAttachList = fileAttachRepository.findAll();
        assertThat(fileAttachList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllFileAttaches() throws Exception {
        // Initialize the database
        fileAttachRepository.saveAndFlush(fileAttach);

        // Get all the fileAttachList
        restFileAttachMockMvc.perform(get("/api/file-attaches?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(fileAttach.getId().intValue())))
            .andExpect(jsonPath("$.[*].moTa").value(hasItem(DEFAULT_MO_TA)))
            .andExpect(jsonPath("$.[*].noiDungContentType").value(hasItem(DEFAULT_NOI_DUNG_CONTENT_TYPE)))
            .andExpect(jsonPath("$.[*].noiDung").value(hasItem(Base64Utils.encodeToString(DEFAULT_NOI_DUNG))))
            .andExpect(jsonPath("$.[*].ngayCapNhat").value(hasItem(DEFAULT_NGAY_CAP_NHAT.toString())));
    }
    
    @Test
    @Transactional
    public void getFileAttach() throws Exception {
        // Initialize the database
        fileAttachRepository.saveAndFlush(fileAttach);

        // Get the fileAttach
        restFileAttachMockMvc.perform(get("/api/file-attaches/{id}", fileAttach.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(fileAttach.getId().intValue()))
            .andExpect(jsonPath("$.moTa").value(DEFAULT_MO_TA))
            .andExpect(jsonPath("$.noiDungContentType").value(DEFAULT_NOI_DUNG_CONTENT_TYPE))
            .andExpect(jsonPath("$.noiDung").value(Base64Utils.encodeToString(DEFAULT_NOI_DUNG)))
            .andExpect(jsonPath("$.ngayCapNhat").value(DEFAULT_NGAY_CAP_NHAT.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingFileAttach() throws Exception {
        // Get the fileAttach
        restFileAttachMockMvc.perform(get("/api/file-attaches/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateFileAttach() throws Exception {
        // Initialize the database
        fileAttachService.save(fileAttach);

        int databaseSizeBeforeUpdate = fileAttachRepository.findAll().size();

        // Update the fileAttach
        FileAttach updatedFileAttach = fileAttachRepository.findById(fileAttach.getId()).get();
        // Disconnect from session so that the updates on updatedFileAttach are not directly saved in db
        em.detach(updatedFileAttach);
        updatedFileAttach
            .moTa(UPDATED_MO_TA)
            .noiDung(UPDATED_NOI_DUNG)
            .noiDungContentType(UPDATED_NOI_DUNG_CONTENT_TYPE)
            .ngayCapNhat(UPDATED_NGAY_CAP_NHAT);

        restFileAttachMockMvc.perform(put("/api/file-attaches")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(updatedFileAttach)))
            .andExpect(status().isOk());

        // Validate the FileAttach in the database
        List<FileAttach> fileAttachList = fileAttachRepository.findAll();
        assertThat(fileAttachList).hasSize(databaseSizeBeforeUpdate);
        FileAttach testFileAttach = fileAttachList.get(fileAttachList.size() - 1);
        assertThat(testFileAttach.getMoTa()).isEqualTo(UPDATED_MO_TA);
        assertThat(testFileAttach.getNoiDung()).isEqualTo(UPDATED_NOI_DUNG);
        assertThat(testFileAttach.getNoiDungContentType()).isEqualTo(UPDATED_NOI_DUNG_CONTENT_TYPE);
        assertThat(testFileAttach.getNgayCapNhat()).isEqualTo(UPDATED_NGAY_CAP_NHAT);
    }

    @Test
    @Transactional
    public void updateNonExistingFileAttach() throws Exception {
        int databaseSizeBeforeUpdate = fileAttachRepository.findAll().size();

        // Create the FileAttach

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restFileAttachMockMvc.perform(put("/api/file-attaches")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(fileAttach)))
            .andExpect(status().isBadRequest());

        // Validate the FileAttach in the database
        List<FileAttach> fileAttachList = fileAttachRepository.findAll();
        assertThat(fileAttachList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteFileAttach() throws Exception {
        // Initialize the database
        fileAttachService.save(fileAttach);

        int databaseSizeBeforeDelete = fileAttachRepository.findAll().size();

        // Delete the fileAttach
        restFileAttachMockMvc.perform(delete("/api/file-attaches/{id}", fileAttach.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<FileAttach> fileAttachList = fileAttachRepository.findAll();
        assertThat(fileAttachList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
