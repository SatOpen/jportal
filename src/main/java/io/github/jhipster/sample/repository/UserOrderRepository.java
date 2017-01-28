package io.github.jhipster.sample.repository;

import io.github.jhipster.sample.domain.UserOrder;

import org.springframework.data.jpa.repository.*;

import java.util.List;

/**
 * Spring Data JPA repository for the UserOrder entity.
 */
@SuppressWarnings("unused")
public interface UserOrderRepository extends JpaRepository<UserOrder,Long> {

    @Query("select userOrder from UserOrder userOrder where userOrder.user.login = ?#{principal.username}")
    List<UserOrder> findByUserIsCurrentUser();

}
