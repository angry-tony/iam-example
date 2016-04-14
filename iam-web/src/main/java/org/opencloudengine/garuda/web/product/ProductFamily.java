package org.opencloudengine.garuda.web.product;

import java.sql.Date;

/**
 * Created by cloudine on 2015. 6. 10..
 */
public class ProductFamily {

    private Long id;

    private String version;

    private String name;

    private String family;

    private String licenseType;

    private Date registration;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFamily() {
        return family;
    }

    public void setFamily(String family) {
        this.family = family;
    }

    public String getLicenseType() {
        return licenseType;
    }

    public void setLicenseType(String licenseType) {
        this.licenseType = licenseType;
    }

    public Date getRegistration() {
        return registration;
    }

    public void setRegistration(Date registration) {
        this.registration = registration;
    }

    @Override
    public String toString() {
        return "ProductFamily{" +
                "id=" + id +
                ", version='" + version + '\'' +
                ", name='" + name + '\'' +
                ", family='" + family + '\'' +
                ", licenseType='" + licenseType + '\'' +
                ", registration=" + registration +
                '}';
    }
}
