package io.github.jhipster.sample.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

import io.github.jhipster.sample.domain.enumeration.OrderStatusEnum;

import io.github.jhipster.sample.domain.enumeration.OrderTypeEnum;

import io.github.jhipster.sample.domain.enumeration.PaySystemEnum;

/**
 * A UserOrder.
 */
@Entity
@Table(name = "user_order")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class UserOrder implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "validated")
    private Boolean validated;

    @Column(name = "mag_device")
    private Boolean magDevice;

    @Column(name = "creation_date")
    private LocalDate creationDate;

    @Enumerated(EnumType.STRING)
    @Column(name = "order_status")
    private OrderStatusEnum orderStatus;

    @Enumerated(EnumType.STRING)
    @Column(name = "order_type")
    private OrderTypeEnum orderType;

    @Enumerated(EnumType.STRING)
    @Column(name = "pay_system")
    private PaySystemEnum paySystem;

    @Column(name = "username_renewal")
    private String usernameRenewal;

    @Column(name = "password_renewal")
    private String passwordRenewal;

    @Size(max = 50)
    @Column(name = "note", length = 50)
    private String note;

    @Size(max = 50)
    @Column(name = "mag_address", length = 50)
    private String magAddress;

    @ManyToOne
    @NotNull
    private User user;

    @ManyToOne
    @NotNull
    private PackageTv packageTv;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Boolean isValidated() {
        return validated;
    }

    public UserOrder validated(Boolean validated) {
        this.validated = validated;
        return this;
    }

    public void setValidated(Boolean validated) {
        this.validated = validated;
    }

    public Boolean isMagDevice() {
        return magDevice;
    }

    public UserOrder magDevice(Boolean magDevice) {
        this.magDevice = magDevice;
        return this;
    }

    public void setMagDevice(Boolean magDevice) {
        this.magDevice = magDevice;
    }

    public LocalDate getCreationDate() {
        return creationDate;
    }

    public UserOrder creationDate(LocalDate creationDate) {
        this.creationDate = creationDate;
        return this;
    }

    public void setCreationDate(LocalDate creationDate) {
        this.creationDate = creationDate;
    }

    public OrderStatusEnum getOrderStatus() {
        return orderStatus;
    }

    public UserOrder orderStatus(OrderStatusEnum orderStatus) {
        this.orderStatus = orderStatus;
        return this;
    }

    public void setOrderStatus(OrderStatusEnum orderStatus) {
        this.orderStatus = orderStatus;
    }

    public OrderTypeEnum getOrderType() {
        return orderType;
    }

    public UserOrder orderType(OrderTypeEnum orderType) {
        this.orderType = orderType;
        return this;
    }

    public void setOrderType(OrderTypeEnum orderType) {
        this.orderType = orderType;
    }

    public PaySystemEnum getPaySystem() {
        return paySystem;
    }

    public UserOrder paySystem(PaySystemEnum paySystem) {
        this.paySystem = paySystem;
        return this;
    }

    public void setPaySystem(PaySystemEnum paySystem) {
        this.paySystem = paySystem;
    }

    public String getUsernameRenewal() {
        return usernameRenewal;
    }

    public UserOrder usernameRenewal(String usernameRenewal) {
        this.usernameRenewal = usernameRenewal;
        return this;
    }

    public void setUsernameRenewal(String usernameRenewal) {
        this.usernameRenewal = usernameRenewal;
    }

    public String getPasswordRenewal() {
        return passwordRenewal;
    }

    public UserOrder passwordRenewal(String passwordRenewal) {
        this.passwordRenewal = passwordRenewal;
        return this;
    }

    public void setPasswordRenewal(String passwordRenewal) {
        this.passwordRenewal = passwordRenewal;
    }

    public String getNote() {
        return note;
    }

    public UserOrder note(String note) {
        this.note = note;
        return this;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public String getMagAddress() {
        return magAddress;
    }

    public UserOrder magAddress(String magAddress) {
        this.magAddress = magAddress;
        return this;
    }

    public void setMagAddress(String magAddress) {
        this.magAddress = magAddress;
    }

    public User getUser() {
        return user;
    }

    public UserOrder user(User user) {
        this.user = user;
        return this;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public PackageTv getPackageTv() {
        return packageTv;
    }

    public UserOrder packageTv(PackageTv packageTv) {
        this.packageTv = packageTv;
        return this;
    }

    public void setPackageTv(PackageTv packageTv) {
        this.packageTv = packageTv;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        UserOrder userOrder = (UserOrder) o;
        if (userOrder.id == null || id == null) {
            return false;
        }
        return Objects.equals(id, userOrder.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "UserOrder{" +
            "id=" + id +
            ", validated='" + validated + "'" +
            ", magDevice='" + magDevice + "'" +
            ", creationDate='" + creationDate + "'" +
            ", orderStatus='" + orderStatus + "'" +
            ", orderType='" + orderType + "'" +
            ", paySystem='" + paySystem + "'" +
            ", usernameRenewal='" + usernameRenewal + "'" +
            ", passwordRenewal='" + passwordRenewal + "'" +
            ", note='" + note + "'" +
            ", magAddress='" + magAddress + "'" +
            '}';
    }
}
