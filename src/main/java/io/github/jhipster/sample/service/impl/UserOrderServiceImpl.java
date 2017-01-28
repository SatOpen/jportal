package io.github.jhipster.sample.service.impl;

import io.github.jhipster.sample.service.UserOrderService;
import io.github.jhipster.sample.domain.UserOrder;
import io.github.jhipster.sample.repository.UserOrderRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import java.util.List;

/**
 * Service Implementation for managing UserOrder.
 */
@Service
@Transactional
public class UserOrderServiceImpl implements UserOrderService{

    private final Logger log = LoggerFactory.getLogger(UserOrderServiceImpl.class);
    
    @Inject
    private UserOrderRepository userOrderRepository;

    /**
     * Save a userOrder.
     *
     * @param userOrder the entity to save
     * @return the persisted entity
     */
    public UserOrder save(UserOrder userOrder) {
        log.debug("Request to save UserOrder : {}", userOrder);
        UserOrder result = userOrderRepository.save(userOrder);
        return result;
    }

    /**
     *  Get all the userOrders.
     *  
     *  @param pageable the pagination information
     *  @return the list of entities
     */
    @Transactional(readOnly = true) 
    public Page<UserOrder> findAll(Pageable pageable) {
        log.debug("Request to get all UserOrders");
        Page<UserOrder> result = userOrderRepository.findAll(pageable);
        return result;
    }

    /**
     *  Get one userOrder by id.
     *
     *  @param id the id of the entity
     *  @return the entity
     */
    @Transactional(readOnly = true) 
    public UserOrder findOne(Long id) {
        log.debug("Request to get UserOrder : {}", id);
        UserOrder userOrder = userOrderRepository.findOne(id);
        return userOrder;
    }

    /**
     *  Delete the  userOrder by id.
     *
     *  @param id the id of the entity
     */
    public void delete(Long id) {
        log.debug("Request to delete UserOrder : {}", id);
        userOrderRepository.delete(id);
    }
}
