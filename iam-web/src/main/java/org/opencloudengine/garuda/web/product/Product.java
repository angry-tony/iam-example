package org.opencloudengine.garuda.web.product;

import java.sql.Date;

/**
 * Created by cloudine on 2015. 6. 10..
 */
public class Product {

    private Long id;

    private Long productFamilyId;

    private int days;

    private String country;

    private int maxNode;

    private Long price;

    private Date registration;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getProductFamilyId() {
        return productFamilyId;
    }

    public void setProductFamilyId(Long productFamilyId) {
        this.productFamilyId = productFamilyId;
    }

    public int getDays() {
        return days;
    }

    public void setDays(int days) {
        this.days = days;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public int getMaxNode() {
        return maxNode;
    }

    public void setMaxNode(int maxNode) {
        this.maxNode = maxNode;
    }

    public Long getPrice() {
        return price;
    }

    public void setPrice(Long price) {
        this.price = price;
    }

    public Date getRegistration() {
        return registration;
    }

    public void setRegistration(Date registration) {
        this.registration = registration;
    }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", productFamilyId=" + productFamilyId +
                ", days=" + days +
                ", country='" + country + '\'' +
                ", maxNode=" + maxNode +
                ", price=" + price +
                ", registration=" + registration +
                '}';
    }
}
