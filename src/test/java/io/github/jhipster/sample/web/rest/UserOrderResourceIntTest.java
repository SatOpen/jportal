package io.github.jhipster.sample.web.rest;

import io.github.jhipster.sample.JhipsterSampleApplicationApp;

import io.github.jhipster.sample.domain.UserOrder;
import io.github.jhipster.sample.domain.User;
import io.github.jhipster.sample.domain.PackageTv;
import io.github.jhipster.sample.repository.UserOrderRepository;
import io.github.jhipster.sample.service.UserOrderService;

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
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import io.github.jhipster.sample.domain.enumeration.OrderStatusEnum;
import io.github.jhipster.sample.domain.enumeration.OrderTypeEnum;
import io.github.jhipster.sample.domain.enumeration.PaySystemEnum;
/**
 * Test class for the UserOrderResource REST controller.
 *
 * @see UserOrderResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = JhipsterSampleApplicationApp.class)
public class UserOrderResourceIntTest {

    private static final Boolean DEFAULT_VALIDATED = false;
    private static final Boolean UPDATED_VALIDATED = true;

    private static final Boolean DEFAULT_MAG_DEVICE = false;
    private static final Boolean UPDATED_MAG_DEVICE = true;

    private static final LocalDate DEFAULT_CREATION_DATE = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_CREATION_DATE = LocalDate.now(ZoneId.systemDefault());

    private static final OrderStatusEnum DEFAULT_ORDER_STATUS = OrderStatusEnum.Pending;
    private static final OrderStatusEnum UPDATED_ORDER_STATUS = OrderStatusEnum.Validated;

    private static final OrderTypeEnum DEFAULT_ORDER_TYPE = OrderTypeEnum.New;
    private static final OrderTypeEnum UPDATED_ORDER_TYPE = OrderTypeEnum.Renewal;

    private static final PaySystemEnum DEFAULT_PAY_SYSTEM = PaySystemEnum.MyCommerce;
    private static final PaySystemEnum UPDATED_PAY_SYSTEM = PaySystemEnum.Skrill;

    private static final String DEFAULT_USERNAME_RENEWAL = "AAAAAAAAAA";
    private static final String UPDATED_USERNAME_RENEWAL = "BBBBBBBBBB";

    private static final String DEFAULT_PASSWORD_RENEWAL = "AAAAAAAAAA";
    private static final String UPDATED_PASSWORD_RENEWAL = "BBBBBBBBBB";

    private static final String DEFAULT_NOTE = "AAAAAAAAAA";
    private static final String UPDATED_NOTE = "BBBBBBBBBB";

    private static final String DEFAULT_MAG_ADDRESS = "AAAAAAAAAA";
    private static final String UPDATED_MAG_ADDRESS = "BBBBBBBBBB";

    @Inject
    private UserOrderRepository userOrderRepository;

    @Inject
    private UserOrderService userOrderService;

    @Inject
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Inject
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Inject
    private EntityManager em;

    private MockMvc restUserOrderMockMvc;

    private UserOrder userOrder;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        UserOrderResource userOrderResource = new UserOrderResource();
        ReflectionTestUtils.setField(userOrderResource, "userOrderService", userOrderService);
        this.restUserOrderMockMvc = MockMvcBuilders.standaloneSetup(userOrderResource)
            .setCustomArgumentResolvers(pageableArgumentResolver)
            .setMessageConverters(jacksonMessageConverter).build();
    }

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static UserOrder createEntity(EntityManager em) {
        UserOrder userOrder = new UserOrder()
                .validated(DEFAULT_VALIDATED)
                .magDevice(DEFAULT_MAG_DEVICE)
                .creationDate(DEFAULT_CREATION_DATE)
                .orderStatus(DEFAULT_ORDER_STATUS)
                .orderType(DEFAULT_ORDER_TYPE)
                .paySystem(DEFAULT_PAY_SYSTEM)
                .usernameRenewal(DEFAULT_USERNAME_RENEWAL)
                .passwordRenewal(DEFAULT_PASSWORD_RENEWAL)
                .note(DEFAULT_NOTE)
                .magAddress(DEFAULT_MAG_ADDRESS);
        // Add required entity
        User user = UserResourceIntTest.createEntity(em);
        em.persist(user);
        em.flush();
        userOrder.setUser(user);
        // Add required entity
        PackageTv packageTv = PackageTvResourceIntTest.createEntity(em);
        em.persist(packageTv);
        em.flush();
        userOrder.setPackageTv(packageTv);
        return userOrder;
    }

    @Before
    public void initTest() {
        userOrder = createEntity(em);
    }

    @Test
    @Transactional
    public void createUserOrder() throws Exception {
        int databaseSizeBeforeCreate = userOrderRepository.findAll().size();

        // Create the UserOrder

        restUserOrderMockMvc.perform(post("/api/user-orders")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(userOrder)))
            .andExpect(status().isCreated());

        // Validate the UserOrder in the database
        List<UserOrder> userOrderList = userOrderRepository.findAll();
        assertThat(userOrderList).hasSize(databaseSizeBeforeCreate + 1);
        UserOrder testUserOrder = userOrderList.get(userOrderList.size() - 1);
        assertThat(testUserOrder.isValidated()).isEqualTo(DEFAULT_VALIDATED);
        assertThat(testUserOrder.isMagDevice()).isEqualTo(DEFAULT_MAG_DEVICE);
        assertThat(testUserOrder.getCreationDate()).isEqualTo(DEFAULT_CREATION_DATE);
        assertThat(testUserOrder.getOrderStatus()).isEqualTo(DEFAULT_ORDER_STATUS);
        assertThat(testUserOrder.getOrderType()).isEqualTo(DEFAULT_ORDER_TYPE);
        assertThat(testUserOrder.getPaySystem()).isEqualTo(DEFAULT_PAY_SYSTEM);
        assertThat(testUserOrder.getUsernameRenewal()).isEqualTo(DEFAULT_USERNAME_RENEWAL);
        assertThat(testUserOrder.getPasswordRenewal()).isEqualTo(DEFAULT_PASSWORD_RENEWAL);
        assertThat(testUserOrder.getNote()).isEqualTo(DEFAULT_NOTE);
        assertThat(testUserOrder.getMagAddress()).isEqualTo(DEFAULT_MAG_ADDRESS);
    }

    @Test
    @Transactional
    public void createUserOrderWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = userOrderRepository.findAll().size();

        // Create the UserOrder with an existing ID
        UserOrder existingUserOrder = new UserOrder();
        existingUserOrder.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restUserOrderMockMvc.perform(post("/api/user-orders")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(existingUserOrder)))
            .andExpect(status().isBadRequest());

        // Validate the Alice in the database
        List<UserOrder> userOrderList = userOrderRepository.findAll();
        assertThat(userOrderList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void getAllUserOrders() throws Exception {
        // Initialize the database
        userOrderRepository.saveAndFlush(userOrder);

        // Get all the userOrderList
        restUserOrderMockMvc.perform(get("/api/user-orders?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(userOrder.getId().intValue())))
            .andExpect(jsonPath("$.[*].validated").value(hasItem(DEFAULT_VALIDATED.booleanValue())))
            .andExpect(jsonPath("$.[*].magDevice").value(hasItem(DEFAULT_MAG_DEVICE.booleanValue())))
            .andExpect(jsonPath("$.[*].creationDate").value(hasItem(DEFAULT_CREATION_DATE.toString())))
            .andExpect(jsonPath("$.[*].orderStatus").value(hasItem(DEFAULT_ORDER_STATUS.toString())))
            .andExpect(jsonPath("$.[*].orderType").value(hasItem(DEFAULT_ORDER_TYPE.toString())))
            .andExpect(jsonPath("$.[*].paySystem").value(hasItem(DEFAULT_PAY_SYSTEM.toString())))
            .andExpect(jsonPath("$.[*].usernameRenewal").value(hasItem(DEFAULT_USERNAME_RENEWAL.toString())))
            .andExpect(jsonPath("$.[*].passwordRenewal").value(hasItem(DEFAULT_PASSWORD_RENEWAL.toString())))
            .andExpect(jsonPath("$.[*].note").value(hasItem(DEFAULT_NOTE.toString())))
            .andExpect(jsonPath("$.[*].magAddress").value(hasItem(DEFAULT_MAG_ADDRESS.toString())));
    }

    @Test
    @Transactional
    public void getUserOrder() throws Exception {
        // Initialize the database
        userOrderRepository.saveAndFlush(userOrder);

        // Get the userOrder
        restUserOrderMockMvc.perform(get("/api/user-orders/{id}", userOrder.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(userOrder.getId().intValue()))
            .andExpect(jsonPath("$.validated").value(DEFAULT_VALIDATED.booleanValue()))
            .andExpect(jsonPath("$.magDevice").value(DEFAULT_MAG_DEVICE.booleanValue()))
            .andExpect(jsonPath("$.creationDate").value(DEFAULT_CREATION_DATE.toString()))
            .andExpect(jsonPath("$.orderStatus").value(DEFAULT_ORDER_STATUS.toString()))
            .andExpect(jsonPath("$.orderType").value(DEFAULT_ORDER_TYPE.toString()))
            .andExpect(jsonPath("$.paySystem").value(DEFAULT_PAY_SYSTEM.toString()))
            .andExpect(jsonPath("$.usernameRenewal").value(DEFAULT_USERNAME_RENEWAL.toString()))
            .andExpect(jsonPath("$.passwordRenewal").value(DEFAULT_PASSWORD_RENEWAL.toString()))
            .andExpect(jsonPath("$.note").value(DEFAULT_NOTE.toString()))
            .andExpect(jsonPath("$.magAddress").value(DEFAULT_MAG_ADDRESS.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingUserOrder() throws Exception {
        // Get the userOrder
        restUserOrderMockMvc.perform(get("/api/user-orders/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateUserOrder() throws Exception {
        // Initialize the database
        userOrderService.save(userOrder);

        int databaseSizeBeforeUpdate = userOrderRepository.findAll().size();

        // Update the userOrder
        UserOrder updatedUserOrder = userOrderRepository.findOne(userOrder.getId());
        updatedUserOrder
                .validated(UPDATED_VALIDATED)
                .magDevice(UPDATED_MAG_DEVICE)
                .creationDate(UPDATED_CREATION_DATE)
                .orderStatus(UPDATED_ORDER_STATUS)
                .orderType(UPDATED_ORDER_TYPE)
                .paySystem(UPDATED_PAY_SYSTEM)
                .usernameRenewal(UPDATED_USERNAME_RENEWAL)
                .passwordRenewal(UPDATED_PASSWORD_RENEWAL)
                .note(UPDATED_NOTE)
                .magAddress(UPDATED_MAG_ADDRESS);

        restUserOrderMockMvc.perform(put("/api/user-orders")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(updatedUserOrder)))
            .andExpect(status().isOk());

        // Validate the UserOrder in the database
        List<UserOrder> userOrderList = userOrderRepository.findAll();
        assertThat(userOrderList).hasSize(databaseSizeBeforeUpdate);
        UserOrder testUserOrder = userOrderList.get(userOrderList.size() - 1);
        assertThat(testUserOrder.isValidated()).isEqualTo(UPDATED_VALIDATED);
        assertThat(testUserOrder.isMagDevice()).isEqualTo(UPDATED_MAG_DEVICE);
        assertThat(testUserOrder.getCreationDate()).isEqualTo(UPDATED_CREATION_DATE);
        assertThat(testUserOrder.getOrderStatus()).isEqualTo(UPDATED_ORDER_STATUS);
        assertThat(testUserOrder.getOrderType()).isEqualTo(UPDATED_ORDER_TYPE);
        assertThat(testUserOrder.getPaySystem()).isEqualTo(UPDATED_PAY_SYSTEM);
        assertThat(testUserOrder.getUsernameRenewal()).isEqualTo(UPDATED_USERNAME_RENEWAL);
        assertThat(testUserOrder.getPasswordRenewal()).isEqualTo(UPDATED_PASSWORD_RENEWAL);
        assertThat(testUserOrder.getNote()).isEqualTo(UPDATED_NOTE);
        assertThat(testUserOrder.getMagAddress()).isEqualTo(UPDATED_MAG_ADDRESS);
    }

    @Test
    @Transactional
    public void updateNonExistingUserOrder() throws Exception {
        int databaseSizeBeforeUpdate = userOrderRepository.findAll().size();

        // Create the UserOrder

        // If the entity doesn't have an ID, it will be created instead of just being updated
        restUserOrderMockMvc.perform(put("/api/user-orders")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(userOrder)))
            .andExpect(status().isCreated());

        // Validate the UserOrder in the database
        List<UserOrder> userOrderList = userOrderRepository.findAll();
        assertThat(userOrderList).hasSize(databaseSizeBeforeUpdate + 1);
    }

    @Test
    @Transactional
    public void deleteUserOrder() throws Exception {
        // Initialize the database
        userOrderService.save(userOrder);

        int databaseSizeBeforeDelete = userOrderRepository.findAll().size();

        // Get the userOrder
        restUserOrderMockMvc.perform(delete("/api/user-orders/{id}", userOrder.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<UserOrder> userOrderList = userOrderRepository.findAll();
        assertThat(userOrderList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
