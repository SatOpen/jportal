package io.github.jhipster.sample.service;

import io.github.jhipster.sample.domain.UserOrder;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import java.util.List;

/**
 * Service Interface for managing UserOrder.
 */
public interface UserOrderService {

    /**
     * Save a userOrder.
     *
     * @param userOrder the entity to save
     * @return the persisted entity
     */
    UserOrder save(UserOrder userOrder);

    /**
     *  Get all the userOrders.
     *  
     *  @param pageable the pagination information
     *  @return the list of entities
     */
    Page<UserOrder> findAll(Pageable pageable);

    /**
     *  Get the "id" userOrder.
     *
     *  @param id the id of the entity
     *  @return the entity
     */
    UserOrder findOne(Long id);

    /**
     *  Delete the "id" userOrder.
     *
     *  @param id the id of the entity
     */
    void delete(Long id);
}
