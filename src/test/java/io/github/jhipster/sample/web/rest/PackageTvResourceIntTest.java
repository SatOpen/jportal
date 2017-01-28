package io.github.jhipster.sample.web.rest;

import io.github.jhipster.sample.JhipsterSampleApplicationApp;

import io.github.jhipster.sample.domain.PackageTv;
import io.github.jhipster.sample.repository.PackageTvRepository;
import io.github.jhipster.sample.service.PackageTvService;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.web.PageableHandlerMethodArgumentResolver;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import java.math.BigDecimal;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import io.github.jhipster.sample.domain.enumeration.PackageNameEnum;
/**
 * Test class for the PackageTvResource REST controller.
 *
 * @see PackageTvResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = JhipsterSampleApplicationApp.class)
public class PackageTvResourceIntTest {

    private static final PackageNameEnum DEFAULT_PACKAGE_NAME = PackageNameEnum.Iptv_1;
    private static final PackageNameEnum UPDATED_PACKAGE_NAME = PackageNameEnum.Cs_1;

    private static final BigDecimal DEFAULT_PRICE_SKRILL = new BigDecimal(100);
    private static final BigDecimal UPDATED_PRICE_SKRILL = new BigDecimal(99);

    private static final BigDecimal DEFAULT_PRICE_NETELLER = new BigDecimal(100);
    private static final BigDecimal UPDATED_PRICE_NETELLER = new BigDecimal(99);

    private static final BigDecimal DEFAULT_PRICE_BITCOINS = new BigDecimal(100);
    private static final BigDecimal UPDATED_PRICE_BITCOINS = new BigDecimal(99);

    private static final String DEFAULT_NOTE = "AAAAAAAAAA";
    private static final String UPDATED_NOTE = "BBBBBBBBBB";

    private static final BigDecimal DEFAULT_PRICE_MY_COMMERCE = new BigDecimal(100);
    private static final BigDecimal UPDATED_PRICE_MY_COMMERCE = new BigDecimal(99);

    private static final String DEFAULT_DESCRIPTION_MY_COMMERCE = "AAAAAAAAAA";
    private static final String UPDATED_DESCRIPTION_MY_COMMERCE = "BBBBBBBBBB";

    private static final String DEFAULT_DESCRIPTION_SKRILL = "AAAAAAAAAA";
    private static final String UPDATED_DESCRIPTION_SKRILL = "BBBBBBBBBB";

    private static final String DEFAULT_DESCRIPTION_NETELLER = "AAAAAAAAAA";
    private static final String UPDATED_DESCRIPTION_NETELLER = "BBBBBBBBBB";

    private static final String DEFAULT_DESCRIPTION_BITCOINS = "AAAAAAAAAA";
    private static final String UPDATED_DESCRIPTION_BITCOINS = "BBBBBBBBBB";

    private static final BigDecimal DEFAULT_PRICE_PAY_SAFE_CARD = new BigDecimal(100);
    private static final BigDecimal UPDATED_PRICE_PAY_SAFE_CARD = new BigDecimal(99);

    private static final String DEFAULT_DESCRIPTION_PAY_SAFE_CARD = "AAAAAAAAAA";
    private static final String UPDATED_DESCRIPTION_PAY_SAFE_CARD = "BBBBBBBBBB";

    @Inject
    private PackageTvRepository packageTvRepository;

    @Inject
    private PackageTvService packageTvService;

    @Inject
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Inject
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Inject
    private EntityManager em;

    private MockMvc restPackageTvMockMvc;

    private PackageTv packageTv;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        PackageTvResource packageTvResource = new PackageTvResource();
        ReflectionTestUtils.setField(packageTvResource, "packageTvService", packageTvService);
        this.restPackageTvMockMvc = MockMvcBuilders.standaloneSetup(packageTvResource)
            .setCustomArgumentResolvers(pageableArgumentResolver)
            .setMessageConverters(jacksonMessageConverter).build();
    }

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static PackageTv createEntity(EntityManager em) {
        PackageTv packageTv = new PackageTv()
                .packageName(DEFAULT_PACKAGE_NAME)
                .priceSkrill(DEFAULT_PRICE_SKRILL)
                .priceNeteller(DEFAULT_PRICE_NETELLER)
                .priceBitcoins(DEFAULT_PRICE_BITCOINS)
                .note(DEFAULT_NOTE)
                .priceMyCommerce(DEFAULT_PRICE_MY_COMMERCE)
                .descriptionMyCommerce(DEFAULT_DESCRIPTION_MY_COMMERCE)
                .descriptionSkrill(DEFAULT_DESCRIPTION_SKRILL)
                .descriptionNeteller(DEFAULT_DESCRIPTION_NETELLER)
                .descriptionBitcoins(DEFAULT_DESCRIPTION_BITCOINS)
                .pricePaySafeCard(DEFAULT_PRICE_PAY_SAFE_CARD)
                .descriptionPaySafeCard(DEFAULT_DESCRIPTION_PAY_SAFE_CARD);
        return packageTv;
    }

    @Before
    public void initTest() {
        packageTv = createEntity(em);
    }

    @Test
    @Transactional
    public void createPackageTv() throws Exception {
        int databaseSizeBeforeCreate = packageTvRepository.findAll().size();

        // Create the PackageTv

        restPackageTvMockMvc.perform(post("/api/package-tvs")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(packageTv)))
            .andExpect(status().isCreated());

        // Validate the PackageTv in the database
        List<PackageTv> packageTvList = packageTvRepository.findAll();
        assertThat(packageTvList).hasSize(databaseSizeBeforeCreate + 1);
        PackageTv testPackageTv = packageTvList.get(packageTvList.size() - 1);
        assertThat(testPackageTv.getPackageName()).isEqualTo(DEFAULT_PACKAGE_NAME);
        assertThat(testPackageTv.getPriceSkrill()).isEqualTo(DEFAULT_PRICE_SKRILL);
        assertThat(testPackageTv.getPriceNeteller()).isEqualTo(DEFAULT_PRICE_NETELLER);
        assertThat(testPackageTv.getPriceBitcoins()).isEqualTo(DEFAULT_PRICE_BITCOINS);
        assertThat(testPackageTv.getNote()).isEqualTo(DEFAULT_NOTE);
        assertThat(testPackageTv.getPriceMyCommerce()).isEqualTo(DEFAULT_PRICE_MY_COMMERCE);
        assertThat(testPackageTv.getDescriptionMyCommerce()).isEqualTo(DEFAULT_DESCRIPTION_MY_COMMERCE);
        assertThat(testPackageTv.getDescriptionSkrill()).isEqualTo(DEFAULT_DESCRIPTION_SKRILL);
        assertThat(testPackageTv.getDescriptionNeteller()).isEqualTo(DEFAULT_DESCRIPTION_NETELLER);
        assertThat(testPackageTv.getDescriptionBitcoins()).isEqualTo(DEFAULT_DESCRIPTION_BITCOINS);
        assertThat(testPackageTv.getPricePaySafeCard()).isEqualTo(DEFAULT_PRICE_PAY_SAFE_CARD);
        assertThat(testPackageTv.getDescriptionPaySafeCard()).isEqualTo(DEFAULT_DESCRIPTION_PAY_SAFE_CARD);
    }

    @Test
    @Transactional
    public void createPackageTvWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = packageTvRepository.findAll().size();

        // Create the PackageTv with an existing ID
        PackageTv existingPackageTv = new PackageTv();
        existingPackageTv.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restPackageTvMockMvc.perform(post("/api/package-tvs")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(existingPackageTv)))
            .andExpect(status().isBadRequest());

        // Validate the Alice in the database
        List<PackageTv> packageTvList = packageTvRepository.findAll();
        assertThat(packageTvList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void checkPackageNameIsRequired() throws Exception {
        int databaseSizeBeforeTest = packageTvRepository.findAll().size();
        // set the field null
        packageTv.setPackageName(null);

        // Create the PackageTv, which fails.

        restPackageTvMockMvc.perform(post("/api/package-tvs")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(packageTv)))
            .andExpect(status().isBadRequest());

        List<PackageTv> packageTvList = packageTvRepository.findAll();
        assertThat(packageTvList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkPriceSkrillIsRequired() throws Exception {
        int databaseSizeBeforeTest = packageTvRepository.findAll().size();
        // set the field null
        packageTv.setPriceSkrill(null);

        // Create the PackageTv, which fails.

        restPackageTvMockMvc.perform(post("/api/package-tvs")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(packageTv)))
            .andExpect(status().isBadRequest());

        List<PackageTv> packageTvList = packageTvRepository.findAll();
        assertThat(packageTvList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkPriceNetellerIsRequired() throws Exception {
        int databaseSizeBeforeTest = packageTvRepository.findAll().size();
        // set the field null
        packageTv.setPriceNeteller(null);

        // Create the PackageTv, which fails.

        restPackageTvMockMvc.perform(post("/api/package-tvs")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(packageTv)))
            .andExpect(status().isBadRequest());

        List<PackageTv> packageTvList = packageTvRepository.findAll();
        assertThat(packageTvList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkPriceBitcoinsIsRequired() throws Exception {
        int databaseSizeBeforeTest = packageTvRepository.findAll().size();
        // set the field null
        packageTv.setPriceBitcoins(null);

        // Create the PackageTv, which fails.

        restPackageTvMockMvc.perform(post("/api/package-tvs")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(packageTv)))
            .andExpect(status().isBadRequest());

        List<PackageTv> packageTvList = packageTvRepository.findAll();
        assertThat(packageTvList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkPriceMyCommerceIsRequired() throws Exception {
        int databaseSizeBeforeTest = packageTvRepository.findAll().size();
        // set the field null
        packageTv.setPriceMyCommerce(null);

        // Create the PackageTv, which fails.

        restPackageTvMockMvc.perform(post("/api/package-tvs")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(packageTv)))
            .andExpect(status().isBadRequest());

        List<PackageTv> packageTvList = packageTvRepository.findAll();
        assertThat(packageTvList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkPricePaySafeCardIsRequired() throws Exception {
        int databaseSizeBeforeTest = packageTvRepository.findAll().size();
        // set the field null
        packageTv.setPricePaySafeCard(null);

        // Create the PackageTv, which fails.

        restPackageTvMockMvc.perform(post("/api/package-tvs")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(packageTv)))
            .andExpect(status().isBadRequest());

        List<PackageTv> packageTvList = packageTvRepository.findAll();
        assertThat(packageTvList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllPackageTvs() throws Exception {
        // Initialize the database
        packageTvRepository.saveAndFlush(packageTv);

        // Get all the packageTvList
        restPackageTvMockMvc.perform(get("/api/package-tvs?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(packageTv.getId().intValue())))
            .andExpect(jsonPath("$.[*].packageName").value(hasItem(DEFAULT_PACKAGE_NAME.toString())))
            .andExpect(jsonPath("$.[*].priceSkrill").value(hasItem(DEFAULT_PRICE_SKRILL.intValue())))
            .andExpect(jsonPath("$.[*].priceNeteller").value(hasItem(DEFAULT_PRICE_NETELLER.intValue())))
            .andExpect(jsonPath("$.[*].priceBitcoins").value(hasItem(DEFAULT_PRICE_BITCOINS.intValue())))
            .andExpect(jsonPath("$.[*].note").value(hasItem(DEFAULT_NOTE.toString())))
            .andExpect(jsonPath("$.[*].priceMyCommerce").value(hasItem(DEFAULT_PRICE_MY_COMMERCE.intValue())))
            .andExpect(jsonPath("$.[*].descriptionMyCommerce").value(hasItem(DEFAULT_DESCRIPTION_MY_COMMERCE.toString())))
            .andExpect(jsonPath("$.[*].descriptionSkrill").value(hasItem(DEFAULT_DESCRIPTION_SKRILL.toString())))
            .andExpect(jsonPath("$.[*].descriptionNeteller").value(hasItem(DEFAULT_DESCRIPTION_NETELLER.toString())))
            .andExpect(jsonPath("$.[*].descriptionBitcoins").value(hasItem(DEFAULT_DESCRIPTION_BITCOINS.toString())))
            .andExpect(jsonPath("$.[*].pricePaySafeCard").value(hasItem(DEFAULT_PRICE_PAY_SAFE_CARD.intValue())))
            .andExpect(jsonPath("$.[*].descriptionPaySafeCard").value(hasItem(DEFAULT_DESCRIPTION_PAY_SAFE_CARD.toString())));
    }

    @Test
    @Transactional
    public void getPackageTv() throws Exception {
        // Initialize the database
        packageTvRepository.saveAndFlush(packageTv);

        // Get the packageTv
        restPackageTvMockMvc.perform(get("/api/package-tvs/{id}", packageTv.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(packageTv.getId().intValue()))
            .andExpect(jsonPath("$.packageName").value(DEFAULT_PACKAGE_NAME.toString()))
            .andExpect(jsonPath("$.priceSkrill").value(DEFAULT_PRICE_SKRILL.intValue()))
            .andExpect(jsonPath("$.priceNeteller").value(DEFAULT_PRICE_NETELLER.intValue()))
            .andExpect(jsonPath("$.priceBitcoins").value(DEFAULT_PRICE_BITCOINS.intValue()))
            .andExpect(jsonPath("$.note").value(DEFAULT_NOTE.toString()))
            .andExpect(jsonPath("$.priceMyCommerce").value(DEFAULT_PRICE_MY_COMMERCE.intValue()))
            .andExpect(jsonPath("$.descriptionMyCommerce").value(DEFAULT_DESCRIPTION_MY_COMMERCE.toString()))
            .andExpect(jsonPath("$.descriptionSkrill").value(DEFAULT_DESCRIPTION_SKRILL.toString()))
            .andExpect(jsonPath("$.descriptionNeteller").value(DEFAULT_DESCRIPTION_NETELLER.toString()))
            .andExpect(jsonPath("$.descriptionBitcoins").value(DEFAULT_DESCRIPTION_BITCOINS.toString()))
            .andExpect(jsonPath("$.pricePaySafeCard").value(DEFAULT_PRICE_PAY_SAFE_CARD.intValue()))
            .andExpect(jsonPath("$.descriptionPaySafeCard").value(DEFAULT_DESCRIPTION_PAY_SAFE_CARD.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingPackageTv() throws Exception {
        // Get the packageTv
        restPackageTvMockMvc.perform(get("/api/package-tvs/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updatePackageTv() throws Exception {
        // Initialize the database
        packageTvService.save(packageTv);

        int databaseSizeBeforeUpdate = packageTvRepository.findAll().size();

        // Update the packageTv
        PackageTv updatedPackageTv = packageTvRepository.findOne(packageTv.getId());
        updatedPackageTv
                .packageName(UPDATED_PACKAGE_NAME)
                .priceSkrill(UPDATED_PRICE_SKRILL)
                .priceNeteller(UPDATED_PRICE_NETELLER)
                .priceBitcoins(UPDATED_PRICE_BITCOINS)
                .note(UPDATED_NOTE)
                .priceMyCommerce(UPDATED_PRICE_MY_COMMERCE)
                .descriptionMyCommerce(UPDATED_DESCRIPTION_MY_COMMERCE)
                .descriptionSkrill(UPDATED_DESCRIPTION_SKRILL)
                .descriptionNeteller(UPDATED_DESCRIPTION_NETELLER)
                .descriptionBitcoins(UPDATED_DESCRIPTION_BITCOINS)
                .pricePaySafeCard(UPDATED_PRICE_PAY_SAFE_CARD)
                .descriptionPaySafeCard(UPDATED_DESCRIPTION_PAY_SAFE_CARD);

        restPackageTvMockMvc.perform(put("/api/package-tvs")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(updatedPackageTv)))
            .andExpect(status().isOk());

        // Validate the PackageTv in the database
        List<PackageTv> packageTvList = packageTvRepository.findAll();
        assertThat(packageTvList).hasSize(databaseSizeBeforeUpdate);
        PackageTv testPackageTv = packageTvList.get(packageTvList.size() - 1);
        assertThat(testPackageTv.getPackageName()).isEqualTo(UPDATED_PACKAGE_NAME);
        assertThat(testPackageTv.getPriceSkrill()).isEqualTo(UPDATED_PRICE_SKRILL);
        assertThat(testPackageTv.getPriceNeteller()).isEqualTo(UPDATED_PRICE_NETELLER);
        assertThat(testPackageTv.getPriceBitcoins()).isEqualTo(UPDATED_PRICE_BITCOINS);
        assertThat(testPackageTv.getNote()).isEqualTo(UPDATED_NOTE);
        assertThat(testPackageTv.getPriceMyCommerce()).isEqualTo(UPDATED_PRICE_MY_COMMERCE);
        assertThat(testPackageTv.getDescriptionMyCommerce()).isEqualTo(UPDATED_DESCRIPTION_MY_COMMERCE);
        assertThat(testPackageTv.getDescriptionSkrill()).isEqualTo(UPDATED_DESCRIPTION_SKRILL);
        assertThat(testPackageTv.getDescriptionNeteller()).isEqualTo(UPDATED_DESCRIPTION_NETELLER);
        assertThat(testPackageTv.getDescriptionBitcoins()).isEqualTo(UPDATED_DESCRIPTION_BITCOINS);
        assertThat(testPackageTv.getPricePaySafeCard()).isEqualTo(UPDATED_PRICE_PAY_SAFE_CARD);
        assertThat(testPackageTv.getDescriptionPaySafeCard()).isEqualTo(UPDATED_DESCRIPTION_PAY_SAFE_CARD);
    }

    @Test
    @Transactional
    public void updateNonExistingPackageTv() throws Exception {
        int databaseSizeBeforeUpdate = packageTvRepository.findAll().size();

        // Create the PackageTv

        // If the entity doesn't have an ID, it will be created instead of just being updated
        restPackageTvMockMvc.perform(put("/api/package-tvs")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(packageTv)))
            .andExpect(status().isCreated());

        // Validate the PackageTv in the database
        List<PackageTv> packageTvList = packageTvRepository.findAll();
        assertThat(packageTvList).hasSize(databaseSizeBeforeUpdate + 1);
    }

    @Test
    @Transactional
    public void deletePackageTv() throws Exception {
        // Initialize the database
        packageTvService.save(packageTv);

        int databaseSizeBeforeDelete = packageTvRepository.findAll().size();

        // Get the packageTv
        restPackageTvMockMvc.perform(delete("/api/package-tvs/{id}", packageTv.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<PackageTv> packageTvList = packageTvRepository.findAll();
        assertThat(packageTvList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
