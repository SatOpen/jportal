package io.github.jhipster.sample.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;
import java.util.Objects;

import io.github.jhipster.sample.domain.enumeration.PackageNameEnum;

/**
 * A PackageTv.
 */
@Entity
@Table(name = "package_tv")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class PackageTv implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "package_name", nullable = false)
    private PackageNameEnum packageName;

    @NotNull
    @DecimalMax(value = "100")
    @Column(name = "price_skrill", precision=10, scale=2, nullable = false)
    private BigDecimal priceSkrill;

    @NotNull
    @DecimalMax(value = "100")
    @Column(name = "price_neteller", precision=10, scale=2, nullable = false)
    private BigDecimal priceNeteller;

    @NotNull
    @DecimalMax(value = "100")
    @Column(name = "price_bitcoins", precision=10, scale=2, nullable = false)
    private BigDecimal priceBitcoins;

    @Column(name = "note")
    private String note;

    @NotNull
    @DecimalMax(value = "100")
    @Column(name = "price_my_commerce", precision=10, scale=2, nullable = false)
    private BigDecimal priceMyCommerce;

    @Size(max = 255)
    @Column(name = "description_my_commerce", length = 255)
    private String descriptionMyCommerce;

    @Size(max = 255)
    @Column(name = "description_skrill", length = 255)
    private String descriptionSkrill;

    @Size(max = 255)
    @Column(name = "description_neteller", length = 255)
    private String descriptionNeteller;

    @Size(max = 255)
    @Column(name = "description_bitcoins", length = 255)
    private String descriptionBitcoins;

    @NotNull
    @DecimalMax(value = "100")
    @Column(name = "price_pay_safe_card", precision=10, scale=2, nullable = false)
    private BigDecimal pricePaySafeCard;

    @Size(max = 255)
    @Column(name = "description_pay_safe_card", length = 255)
    private String descriptionPaySafeCard;

    @OneToMany(mappedBy = "packageTv")
    @JsonIgnore
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<UserOrder> userOrders = new HashSet<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public PackageNameEnum getPackageName() {
        return packageName;
    }

    public PackageTv packageName(PackageNameEnum packageName) {
        this.packageName = packageName;
        return this;
    }

    public void setPackageName(PackageNameEnum packageName) {
        this.packageName = packageName;
    }

    public BigDecimal getPriceSkrill() {
        return priceSkrill;
    }

    public PackageTv priceSkrill(BigDecimal priceSkrill) {
        this.priceSkrill = priceSkrill;
        return this;
    }

    public void setPriceSkrill(BigDecimal priceSkrill) {
        this.priceSkrill = priceSkrill;
    }

    public BigDecimal getPriceNeteller() {
        return priceNeteller;
    }

    public PackageTv priceNeteller(BigDecimal priceNeteller) {
        this.priceNeteller = priceNeteller;
        return this;
    }

    public void setPriceNeteller(BigDecimal priceNeteller) {
        this.priceNeteller = priceNeteller;
    }

    public BigDecimal getPriceBitcoins() {
        return priceBitcoins;
    }

    public PackageTv priceBitcoins(BigDecimal priceBitcoins) {
        this.priceBitcoins = priceBitcoins;
        return this;
    }

    public void setPriceBitcoins(BigDecimal priceBitcoins) {
        this.priceBitcoins = priceBitcoins;
    }

    public String getNote() {
        return note;
    }

    public PackageTv note(String note) {
        this.note = note;
        return this;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public BigDecimal getPriceMyCommerce() {
        return priceMyCommerce;
    }

    public PackageTv priceMyCommerce(BigDecimal priceMyCommerce) {
        this.priceMyCommerce = priceMyCommerce;
        return this;
    }

    public void setPriceMyCommerce(BigDecimal priceMyCommerce) {
        this.priceMyCommerce = priceMyCommerce;
    }

    public String getDescriptionMyCommerce() {
        return descriptionMyCommerce;
    }

    public PackageTv descriptionMyCommerce(String descriptionMyCommerce) {
        this.descriptionMyCommerce = descriptionMyCommerce;
        return this;
    }

    public void setDescriptionMyCommerce(String descriptionMyCommerce) {
        this.descriptionMyCommerce = descriptionMyCommerce;
    }

    public String getDescriptionSkrill() {
        return descriptionSkrill;
    }

    public PackageTv descriptionSkrill(String descriptionSkrill) {
        this.descriptionSkrill = descriptionSkrill;
        return this;
    }

    public void setDescriptionSkrill(String descriptionSkrill) {
        this.descriptionSkrill = descriptionSkrill;
    }

    public String getDescriptionNeteller() {
        return descriptionNeteller;
    }

    public PackageTv descriptionNeteller(String descriptionNeteller) {
        this.descriptionNeteller = descriptionNeteller;
        return this;
    }

    public void setDescriptionNeteller(String descriptionNeteller) {
        this.descriptionNeteller = descriptionNeteller;
    }

    public String getDescriptionBitcoins() {
        return descriptionBitcoins;
    }

    public PackageTv descriptionBitcoins(String descriptionBitcoins) {
        this.descriptionBitcoins = descriptionBitcoins;
        return this;
    }

    public void setDescriptionBitcoins(String descriptionBitcoins) {
        this.descriptionBitcoins = descriptionBitcoins;
    }

    public BigDecimal getPricePaySafeCard() {
        return pricePaySafeCard;
    }

    public PackageTv pricePaySafeCard(BigDecimal pricePaySafeCard) {
        this.pricePaySafeCard = pricePaySafeCard;
        return this;
    }

    public void setPricePaySafeCard(BigDecimal pricePaySafeCard) {
        this.pricePaySafeCard = pricePaySafeCard;
    }

    public String getDescriptionPaySafeCard() {
        return descriptionPaySafeCard;
    }

    public PackageTv descriptionPaySafeCard(String descriptionPaySafeCard) {
        this.descriptionPaySafeCard = descriptionPaySafeCard;
        return this;
    }

    public void setDescriptionPaySafeCard(String descriptionPaySafeCard) {
        this.descriptionPaySafeCard = descriptionPaySafeCard;
    }

    public Set<UserOrder> getUserOrders() {
        return userOrders;
    }

    public PackageTv userOrders(Set<UserOrder> userOrders) {
        this.userOrders = userOrders;
        return this;
    }

    public PackageTv addUserOrder(UserOrder userOrder) {
        userOrders.add(userOrder);
        userOrder.setPackageTv(this);
        return this;
    }

    public PackageTv removeUserOrder(UserOrder userOrder) {
        userOrders.remove(userOrder);
        userOrder.setPackageTv(null);
        return this;
    }

    public void setUserOrders(Set<UserOrder> userOrders) {
        this.userOrders = userOrders;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        PackageTv packageTv = (PackageTv) o;
        if (packageTv.id == null || id == null) {
            return false;
        }
        return Objects.equals(id, packageTv.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "PackageTv{" +
            "id=" + id +
            ", packageName='" + packageName + "'" +
            ", priceSkrill='" + priceSkrill + "'" +
            ", priceNeteller='" + priceNeteller + "'" +
            ", priceBitcoins='" + priceBitcoins + "'" +
            ", note='" + note + "'" +
            ", priceMyCommerce='" + priceMyCommerce + "'" +
            ", descriptionMyCommerce='" + descriptionMyCommerce + "'" +
            ", descriptionSkrill='" + descriptionSkrill + "'" +
            ", descriptionNeteller='" + descriptionNeteller + "'" +
            ", descriptionBitcoins='" + descriptionBitcoins + "'" +
            ", pricePaySafeCard='" + pricePaySafeCard + "'" +
            ", descriptionPaySafeCard='" + descriptionPaySafeCard + "'" +
            '}';
    }
}
