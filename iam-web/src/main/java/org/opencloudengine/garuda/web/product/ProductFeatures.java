package org.opencloudengine.garuda.web.product;

import java.sql.Date;

/**
 * Created by cloudine on 2015. 6. 10..
 */
public class ProductFeatures {

    private Long id;

    private Long productFamilyId;

    private String feature;

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

    public String getFeature() {
        return feature;
    }

    public void setFeature(String feature) {
        this.feature = feature;
    }

    public Date getRegistration() {
        return registration;
    }

    public void setRegistration(Date registration) {
        this.registration = registration;
    }

    @Override
    public String toString() {
        return "ProductFeatures{" +
                "id=" + id +
                ", productFamilyId=" + productFamilyId +
                ", feature='" + feature + '\'' +
                ", registration=" + registration +
                '}';
    }
}
