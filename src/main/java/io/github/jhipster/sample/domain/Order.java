package io.github.jhipster.sample.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.github.jhipster.sample.config.Constants;
import io.github.jhipster.sample.domain.type.OrderStatusEnum;
import io.github.jhipster.sample.domain.type.OrderTypeEnum;
import io.github.jhipster.sample.domain.type.PaySystemEnum;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.validator.constraints.Email;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.ZonedDateTime;
import java.util.Date;
import java.util.HashSet;
import java.util.Locale;
import java.util.Set;

/**
 * A user.
 */
@Entity
@Table(name = "jhi_order")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Order extends AbstractAuditingEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotNull
    @Column(nullable = false)
    private boolean validated = false;

    @NotNull
    @Column(nullable = false)
    private boolean magDevice = false;

    @Column(name = "creation_date", length = 50)
    private Date creationDate;

    @Enumerated(EnumType.STRING)
    private OrderStatusEnum orderStatus;

    @Enumerated(EnumType.STRING)
    private OrderTypeEnum orderType;

    @Enumerated(EnumType.STRING)
    private PaySystemEnum paySystem;

    @NotNull
    @OneToOne(mappedBy = "order")
    private PackageTv packageTv;

    @Size(max = 50)
    @Column(name = "username_renewal", length = 50)
    private String usernameRenewal;

    @Size(max = 50)
    @Column(name = "password_renewal", length = 50)
    private String passwordRenewal;

    @Size(max = 50)
    @Column(name = "note", length = 50)
    private String note;

    @Size(max = 50)
    @Column(name = "mag_address", length = 50)
    private String magAddress;



}
