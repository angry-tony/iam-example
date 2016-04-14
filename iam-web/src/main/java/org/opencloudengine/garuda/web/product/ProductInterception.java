package org.opencloudengine.garuda.web.product;

import java.sql.Date;

/**
 * Created by cloudine on 2015. 6. 10..
 */
public class ProductInterception {

    private Long id;

    private String country;

    private Date registration;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public Date getRegistration() {
        return registration;
    }

    public void setRegistration(Date registration) {
        this.registration = registration;
    }

    @Override
    public String toString() {
        return "ProductInterception{" +
                "id=" + id +
                ", country='" + country + '\'' +
                ", registration=" + registration +
                '}';
    }
}
