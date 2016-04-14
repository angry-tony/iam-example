package org.opencloudengine.garuda.web.product;

import java.io.Serializable;
import java.sql.Date;

/**
 * Created by cloudine on 2015. 6. 10..
 */
public class ProductMix implements Serializable{

    private Long id;

    private Long productFamilyId;

    private int days;

    private String country;

    private int maxNode;

    private Long price;

    private Date registration;

    private Long familyId;

    private String productVersion;

    private String productName;

    private String familyName;

    private String licenseType;

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

    public Long getFamilyId() {
        return familyId;
    }

    public void setFamilyId(Long familyId) {
        this.familyId = familyId;
    }

    public String getProductVersion() {
        return productVersion;
    }

    public void setProductVersion(String productVersion) {
        this.productVersion = productVersion;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getFamilyName() {
        return familyName;
    }

    public void setFamilyName(String familyName) {
        this.familyName = familyName;
    }

    public String getLicenseType() {
        return licenseType;
    }

    public void setLicenseType(String licenseType) {
        this.licenseType = licenseType;
    }

    @Override
    public String toString() {
        return "ProductMix{" +
                "id=" + id +
                ", productFamilyId=" + productFamilyId +
                ", days=" + days +
                ", country='" + country + '\'' +
                ", maxNode=" + maxNode +
                ", price=" + price +
                ", registration=" + registration +
                ", familyId=" + familyId +
                ", productVersion='" + productVersion + '\'' +
                ", productName='" + productName + '\'' +
                ", familyName='" + familyName + '\'' +
                ", licenseType='" + licenseType + '\'' +
                '}';
    }
}
