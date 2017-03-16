package com.restaurant.dto;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by Martha on 2/24/2017.
 */
@Entity
@NamedQueries({
        @NamedQuery(
                name = "Restaurant.getByNumber",
                query = "SELECT r FROM Restaurant r WHERE r.number  =:" + "number",
                hints = {@QueryHint(name = "org.hibernate.cacheable", value = "true")}),
        @NamedQuery(
                name = "Restaurant.getByName",
                query = "SELECT r FROM Restaurant r WHERE r.name  =:" + "name",
                hints = {@QueryHint(name = "org.hibernate.cacheable", value = "true")}),
        @NamedQuery(
                name = "Restaurant.deleteById",
                query = "DELETE FROM Restaurant r WHERE r.id  = :" + "id",
                hints = {@QueryHint(name = "org.hibernate.cacheable", value = "true")}),
        @NamedQuery(
                name = "Restaurant.getAll",
                query = "FROM Restaurant",
                hints = {@QueryHint(name = "org.hibernate.cacheable", value = "true")}),
})
@Table(name = "restaurant")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Restaurant implements Serializable {

    // region Fields
    private Integer id;
    private String restaurantName;
    private String number;
    // endregion

    // region Constructors
    protected Restaurant() {
    }

    public Restaurant(String restaurantName,
                      String number) {
        this.restaurantName = restaurantName;
        this.number = number;
    }
    // endregion

    // region Properties
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_product")
    public Integer getId() {
        return id;
    }

    @Column(name = "restaurantName",
            unique = true,
            nullable = false)
    public String getRestaurantName() {
        return restaurantName;
    }

    @Column(name = "business_key",
            unique = true,
            nullable = false,
            updatable = false)
    public String getNumber() {
        return number;
    }
    // endregion

    // region Setters

    private void setId(Integer id) {
        this.id = id;
    }

    public void setRestaurantName(String restaurantName) {
        this.restaurantName = restaurantName;
    }

    private void setNumber(String number) {
        this.number = number;
    }
    // endregion

    // region Hashcode/equals overrides
    @Override
    public boolean equals(Object other) {
        if (this == other) return true;
        if ( !(other instanceof Restaurant) ) return false;

        final Restaurant restaurant = (Restaurant) other;

        if (!restaurant.getNumber().equals(getNumber())) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = getId() != null ? getId().hashCode() : 0;
        result = 31 * result + (getNumber() != null ? getNumber().hashCode() : 0);
        return result;
    }
    // endregion

}