package io.github.jhipster.sample.domain;

import io.github.jhipster.sample.domain.type.OrderStatusEnum;
import io.github.jhipster.sample.domain.type.PackageNameEnum;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.math.BigDecimal;

/**
 * Created by francesco on 27/01/17.
 */
@Entity
@Table(name = "jhi_packagetv")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class PackageTv {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotNull
    @Enumerated(EnumType.STRING)
    private PackageNameEnum packageName;

    @NotNull
    @Column(name = "price_mycommerce", precision=10, scale=2, nullable = false)
    private BigDecimal priceMyCommerce;

    @NotNull
    @Column(name = "price_skrill", precision=10, scale=2, nullable = false)
    private BigDecimal priceSkrill;

    @NotNull
    @Column(name = "price_neteller", precision=10, scale=2, nullable = false)
    private BigDecimal priceNeteller;

    @NotNull
    @Column(name = "price_bitcoins", precision=10, scale=2, nullable = false)
    private BigDecimal priceBitcoins;

    @NotNull
    @Column(name = "price_paysafecard", precision=10, scale=2, nullable = false)
    private BigDecimal pricePaysafecard;

    @Size(max = 50)
    @Column(name = "note", length = 50)
    private String note;
}
