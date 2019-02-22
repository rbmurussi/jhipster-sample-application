package io.github.jhipster.application.web.rest;

import io.github.jhipster.application.JhipsterSampleApplicationApp;

import io.github.jhipster.application.domain.Exemple;
import io.github.jhipster.application.repository.ExempleRepository;
import io.github.jhipster.application.service.ExempleService;
import io.github.jhipster.application.web.rest.errors.ExceptionTranslator;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.web.PageableHandlerMethodArgumentResolver;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.util.Base64Utils;
import org.springframework.validation.Validator;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.Instant;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.List;


import static io.github.jhipster.application.web.rest.TestUtil.createFormattingConversionService;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Test class for the ExempleResource REST controller.
 *
 * @see ExempleResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = JhipsterSampleApplicationApp.class)
public class ExempleResourceIntTest {

    private static final String DEFAULT_TYPE_STRING = "AAAAAAAAAA";
    private static final String UPDATED_TYPE_STRING = "BBBBBBBBBB";

    private static final String DEFAULT_TYPE_STRING_PATTERN = "AAAAAAAAAA";
    private static final String UPDATED_TYPE_STRING_PATTERN = "BBBBBBBBBB";

    private static final Integer DEFAULT_TYPE_INTEGER = 1;
    private static final Integer UPDATED_TYPE_INTEGER = 2;

    private static final Long DEFAULT_TYPE_LONG = 1L;
    private static final Long UPDATED_TYPE_LONG = 2L;

    private static final BigDecimal DEFAULT_TYPE_BIG_DECIMAL = new BigDecimal(1);
    private static final BigDecimal UPDATED_TYPE_BIG_DECIMAL = new BigDecimal(2);

    private static final LocalDate DEFAULT_TYPE_LOCAL_DATE = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_TYPE_LOCAL_DATE = LocalDate.now(ZoneId.systemDefault());

    private static final Instant DEFAULT_TYPE_INSTANT = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_TYPE_INSTANT = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    private static final byte[] DEFAULT_TYPE_IMAGE_BLOB = TestUtil.createByteArray(1, "0");
    private static final byte[] UPDATED_TYPE_IMAGE_BLOB = TestUtil.createByteArray(1, "1");
    private static final String DEFAULT_TYPE_IMAGE_BLOB_CONTENT_TYPE = "image/jpg";
    private static final String UPDATED_TYPE_IMAGE_BLOB_CONTENT_TYPE = "image/png";

    private static final String DEFAULT_TYPE_TEXT_BLOB = "AAAAAAAAAA";
    private static final String UPDATED_TYPE_TEXT_BLOB = "BBBBBBBBBB";

    private static final byte[] DEFAULT_TYPE_ANY_BLOB = TestUtil.createByteArray(1, "0");
    private static final byte[] UPDATED_TYPE_ANY_BLOB = TestUtil.createByteArray(1, "1");
    private static final String DEFAULT_TYPE_ANY_BLOB_CONTENT_TYPE = "image/jpg";
    private static final String UPDATED_TYPE_ANY_BLOB_CONTENT_TYPE = "image/png";

    private static final byte[] DEFAULT_TYPE_BLOB = TestUtil.createByteArray(1, "0");
    private static final byte[] UPDATED_TYPE_BLOB = TestUtil.createByteArray(1, "1");
    private static final String DEFAULT_TYPE_BLOB_CONTENT_TYPE = "image/jpg";
    private static final String UPDATED_TYPE_BLOB_CONTENT_TYPE = "image/png";

    @Autowired
    private ExempleRepository exempleRepository;

    @Autowired
    private ExempleService exempleService;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private Validator validator;

    private MockMvc restExempleMockMvc;

    private Exemple exemple;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final ExempleResource exempleResource = new ExempleResource(exempleService);
        this.restExempleMockMvc = MockMvcBuilders.standaloneSetup(exempleResource)
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
    public static Exemple createEntity() {
        Exemple exemple = new Exemple()
            .typeString(DEFAULT_TYPE_STRING)
            .typeStringPattern(DEFAULT_TYPE_STRING_PATTERN)
            .typeInteger(DEFAULT_TYPE_INTEGER)
            .typeLong(DEFAULT_TYPE_LONG)
            .typeBigDecimal(DEFAULT_TYPE_BIG_DECIMAL)
            .typeLocalDate(DEFAULT_TYPE_LOCAL_DATE)
            .typeInstant(DEFAULT_TYPE_INSTANT)
            .typeImageBlob(DEFAULT_TYPE_IMAGE_BLOB)
            .typeImageBlobContentType(DEFAULT_TYPE_IMAGE_BLOB_CONTENT_TYPE)
            .typeTextBlob(DEFAULT_TYPE_TEXT_BLOB)
            .typeAnyBlob(DEFAULT_TYPE_ANY_BLOB)
            .typeAnyBlobContentType(DEFAULT_TYPE_ANY_BLOB_CONTENT_TYPE)
            .typeBlob(DEFAULT_TYPE_BLOB)
            .typeBlobContentType(DEFAULT_TYPE_BLOB_CONTENT_TYPE);
        return exemple;
    }

    @Before
    public void initTest() {
        exempleRepository.deleteAll();
        exemple = createEntity();
    }

    @Test
    public void createExemple() throws Exception {
        int databaseSizeBeforeCreate = exempleRepository.findAll().size();

        // Create the Exemple
        restExempleMockMvc.perform(post("/api/exemples")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(exemple)))
            .andExpect(status().isCreated());

        // Validate the Exemple in the database
        List<Exemple> exempleList = exempleRepository.findAll();
        assertThat(exempleList).hasSize(databaseSizeBeforeCreate + 1);
        Exemple testExemple = exempleList.get(exempleList.size() - 1);
        assertThat(testExemple.getTypeString()).isEqualTo(DEFAULT_TYPE_STRING);
        assertThat(testExemple.getTypeStringPattern()).isEqualTo(DEFAULT_TYPE_STRING_PATTERN);
        assertThat(testExemple.getTypeInteger()).isEqualTo(DEFAULT_TYPE_INTEGER);
        assertThat(testExemple.getTypeLong()).isEqualTo(DEFAULT_TYPE_LONG);
        assertThat(testExemple.getTypeBigDecimal()).isEqualTo(DEFAULT_TYPE_BIG_DECIMAL);
        assertThat(testExemple.getTypeLocalDate()).isEqualTo(DEFAULT_TYPE_LOCAL_DATE);
        assertThat(testExemple.getTypeInstant()).isEqualTo(DEFAULT_TYPE_INSTANT);
        assertThat(testExemple.getTypeImageBlob()).isEqualTo(DEFAULT_TYPE_IMAGE_BLOB);
        assertThat(testExemple.getTypeImageBlobContentType()).isEqualTo(DEFAULT_TYPE_IMAGE_BLOB_CONTENT_TYPE);
        assertThat(testExemple.getTypeTextBlob()).isEqualTo(DEFAULT_TYPE_TEXT_BLOB);
        assertThat(testExemple.getTypeAnyBlob()).isEqualTo(DEFAULT_TYPE_ANY_BLOB);
        assertThat(testExemple.getTypeAnyBlobContentType()).isEqualTo(DEFAULT_TYPE_ANY_BLOB_CONTENT_TYPE);
        assertThat(testExemple.getTypeBlob()).isEqualTo(DEFAULT_TYPE_BLOB);
        assertThat(testExemple.getTypeBlobContentType()).isEqualTo(DEFAULT_TYPE_BLOB_CONTENT_TYPE);
    }

    @Test
    public void createExempleWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = exempleRepository.findAll().size();

        // Create the Exemple with an existing ID
        exemple.setId("existing_id");

        // An entity with an existing ID cannot be created, so this API call must fail
        restExempleMockMvc.perform(post("/api/exemples")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(exemple)))
            .andExpect(status().isBadRequest());

        // Validate the Exemple in the database
        List<Exemple> exempleList = exempleRepository.findAll();
        assertThat(exempleList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    public void checkTypeStringPatternIsRequired() throws Exception {
        int databaseSizeBeforeTest = exempleRepository.findAll().size();
        // set the field null
        exemple.setTypeStringPattern(null);

        // Create the Exemple, which fails.

        restExempleMockMvc.perform(post("/api/exemples")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(exemple)))
            .andExpect(status().isBadRequest());

        List<Exemple> exempleList = exempleRepository.findAll();
        assertThat(exempleList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    public void getAllExemples() throws Exception {
        // Initialize the database
        exempleRepository.save(exemple);

        // Get all the exempleList
        restExempleMockMvc.perform(get("/api/exemples?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(exemple.getId())))
            .andExpect(jsonPath("$.[*].typeString").value(hasItem(DEFAULT_TYPE_STRING.toString())))
            .andExpect(jsonPath("$.[*].typeStringPattern").value(hasItem(DEFAULT_TYPE_STRING_PATTERN.toString())))
            .andExpect(jsonPath("$.[*].typeInteger").value(hasItem(DEFAULT_TYPE_INTEGER)))
            .andExpect(jsonPath("$.[*].typeLong").value(hasItem(DEFAULT_TYPE_LONG.intValue())))
            .andExpect(jsonPath("$.[*].typeBigDecimal").value(hasItem(DEFAULT_TYPE_BIG_DECIMAL.intValue())))
            .andExpect(jsonPath("$.[*].typeLocalDate").value(hasItem(DEFAULT_TYPE_LOCAL_DATE.toString())))
            .andExpect(jsonPath("$.[*].typeInstant").value(hasItem(DEFAULT_TYPE_INSTANT.toString())))
            .andExpect(jsonPath("$.[*].typeImageBlobContentType").value(hasItem(DEFAULT_TYPE_IMAGE_BLOB_CONTENT_TYPE)))
            .andExpect(jsonPath("$.[*].typeImageBlob").value(hasItem(Base64Utils.encodeToString(DEFAULT_TYPE_IMAGE_BLOB))))
            .andExpect(jsonPath("$.[*].typeTextBlob").value(hasItem(DEFAULT_TYPE_TEXT_BLOB.toString())))
            .andExpect(jsonPath("$.[*].typeAnyBlobContentType").value(hasItem(DEFAULT_TYPE_ANY_BLOB_CONTENT_TYPE)))
            .andExpect(jsonPath("$.[*].typeAnyBlob").value(hasItem(Base64Utils.encodeToString(DEFAULT_TYPE_ANY_BLOB))))
            .andExpect(jsonPath("$.[*].typeBlobContentType").value(hasItem(DEFAULT_TYPE_BLOB_CONTENT_TYPE)))
            .andExpect(jsonPath("$.[*].typeBlob").value(hasItem(Base64Utils.encodeToString(DEFAULT_TYPE_BLOB))));
    }
    
    @Test
    public void getExemple() throws Exception {
        // Initialize the database
        exempleRepository.save(exemple);

        // Get the exemple
        restExempleMockMvc.perform(get("/api/exemples/{id}", exemple.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(exemple.getId()))
            .andExpect(jsonPath("$.typeString").value(DEFAULT_TYPE_STRING.toString()))
            .andExpect(jsonPath("$.typeStringPattern").value(DEFAULT_TYPE_STRING_PATTERN.toString()))
            .andExpect(jsonPath("$.typeInteger").value(DEFAULT_TYPE_INTEGER))
            .andExpect(jsonPath("$.typeLong").value(DEFAULT_TYPE_LONG.intValue()))
            .andExpect(jsonPath("$.typeBigDecimal").value(DEFAULT_TYPE_BIG_DECIMAL.intValue()))
            .andExpect(jsonPath("$.typeLocalDate").value(DEFAULT_TYPE_LOCAL_DATE.toString()))
            .andExpect(jsonPath("$.typeInstant").value(DEFAULT_TYPE_INSTANT.toString()))
            .andExpect(jsonPath("$.typeImageBlobContentType").value(DEFAULT_TYPE_IMAGE_BLOB_CONTENT_TYPE))
            .andExpect(jsonPath("$.typeImageBlob").value(Base64Utils.encodeToString(DEFAULT_TYPE_IMAGE_BLOB)))
            .andExpect(jsonPath("$.typeTextBlob").value(DEFAULT_TYPE_TEXT_BLOB.toString()))
            .andExpect(jsonPath("$.typeAnyBlobContentType").value(DEFAULT_TYPE_ANY_BLOB_CONTENT_TYPE))
            .andExpect(jsonPath("$.typeAnyBlob").value(Base64Utils.encodeToString(DEFAULT_TYPE_ANY_BLOB)))
            .andExpect(jsonPath("$.typeBlobContentType").value(DEFAULT_TYPE_BLOB_CONTENT_TYPE))
            .andExpect(jsonPath("$.typeBlob").value(Base64Utils.encodeToString(DEFAULT_TYPE_BLOB)));
    }

    @Test
    public void getNonExistingExemple() throws Exception {
        // Get the exemple
        restExempleMockMvc.perform(get("/api/exemples/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    public void updateExemple() throws Exception {
        // Initialize the database
        exempleService.save(exemple);

        int databaseSizeBeforeUpdate = exempleRepository.findAll().size();

        // Update the exemple
        Exemple updatedExemple = exempleRepository.findById(exemple.getId()).get();
        updatedExemple
            .typeString(UPDATED_TYPE_STRING)
            .typeStringPattern(UPDATED_TYPE_STRING_PATTERN)
            .typeInteger(UPDATED_TYPE_INTEGER)
            .typeLong(UPDATED_TYPE_LONG)
            .typeBigDecimal(UPDATED_TYPE_BIG_DECIMAL)
            .typeLocalDate(UPDATED_TYPE_LOCAL_DATE)
            .typeInstant(UPDATED_TYPE_INSTANT)
            .typeImageBlob(UPDATED_TYPE_IMAGE_BLOB)
            .typeImageBlobContentType(UPDATED_TYPE_IMAGE_BLOB_CONTENT_TYPE)
            .typeTextBlob(UPDATED_TYPE_TEXT_BLOB)
            .typeAnyBlob(UPDATED_TYPE_ANY_BLOB)
            .typeAnyBlobContentType(UPDATED_TYPE_ANY_BLOB_CONTENT_TYPE)
            .typeBlob(UPDATED_TYPE_BLOB)
            .typeBlobContentType(UPDATED_TYPE_BLOB_CONTENT_TYPE);

        restExempleMockMvc.perform(put("/api/exemples")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(updatedExemple)))
            .andExpect(status().isOk());

        // Validate the Exemple in the database
        List<Exemple> exempleList = exempleRepository.findAll();
        assertThat(exempleList).hasSize(databaseSizeBeforeUpdate);
        Exemple testExemple = exempleList.get(exempleList.size() - 1);
        assertThat(testExemple.getTypeString()).isEqualTo(UPDATED_TYPE_STRING);
        assertThat(testExemple.getTypeStringPattern()).isEqualTo(UPDATED_TYPE_STRING_PATTERN);
        assertThat(testExemple.getTypeInteger()).isEqualTo(UPDATED_TYPE_INTEGER);
        assertThat(testExemple.getTypeLong()).isEqualTo(UPDATED_TYPE_LONG);
        assertThat(testExemple.getTypeBigDecimal()).isEqualTo(UPDATED_TYPE_BIG_DECIMAL);
        assertThat(testExemple.getTypeLocalDate()).isEqualTo(UPDATED_TYPE_LOCAL_DATE);
        assertThat(testExemple.getTypeInstant()).isEqualTo(UPDATED_TYPE_INSTANT);
        assertThat(testExemple.getTypeImageBlob()).isEqualTo(UPDATED_TYPE_IMAGE_BLOB);
        assertThat(testExemple.getTypeImageBlobContentType()).isEqualTo(UPDATED_TYPE_IMAGE_BLOB_CONTENT_TYPE);
        assertThat(testExemple.getTypeTextBlob()).isEqualTo(UPDATED_TYPE_TEXT_BLOB);
        assertThat(testExemple.getTypeAnyBlob()).isEqualTo(UPDATED_TYPE_ANY_BLOB);
        assertThat(testExemple.getTypeAnyBlobContentType()).isEqualTo(UPDATED_TYPE_ANY_BLOB_CONTENT_TYPE);
        assertThat(testExemple.getTypeBlob()).isEqualTo(UPDATED_TYPE_BLOB);
        assertThat(testExemple.getTypeBlobContentType()).isEqualTo(UPDATED_TYPE_BLOB_CONTENT_TYPE);
    }

    @Test
    public void updateNonExistingExemple() throws Exception {
        int databaseSizeBeforeUpdate = exempleRepository.findAll().size();

        // Create the Exemple

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restExempleMockMvc.perform(put("/api/exemples")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(exemple)))
            .andExpect(status().isBadRequest());

        // Validate the Exemple in the database
        List<Exemple> exempleList = exempleRepository.findAll();
        assertThat(exempleList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    public void deleteExemple() throws Exception {
        // Initialize the database
        exempleService.save(exemple);

        int databaseSizeBeforeDelete = exempleRepository.findAll().size();

        // Delete the exemple
        restExempleMockMvc.perform(delete("/api/exemples/{id}", exemple.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<Exemple> exempleList = exempleRepository.findAll();
        assertThat(exempleList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Exemple.class);
        Exemple exemple1 = new Exemple();
        exemple1.setId("id1");
        Exemple exemple2 = new Exemple();
        exemple2.setId(exemple1.getId());
        assertThat(exemple1).isEqualTo(exemple2);
        exemple2.setId("id2");
        assertThat(exemple1).isNotEqualTo(exemple2);
        exemple1.setId(null);
        assertThat(exemple1).isNotEqualTo(exemple2);
    }
}
