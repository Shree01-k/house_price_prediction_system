package com.example.demo.Module;





import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.*;

@Entity
@Table(name = "property_master")
public class Property {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int propId;

    @Column(nullable = false, length = 100)
    private String propName;

    @Column(nullable = false)
    private String propAddress;

    @Column(nullable = false)
    private int propSqft;

    @Column(nullable = false)
    private int propPrice;

    @Column(nullable = false)
    private int priceWithAmenity;

    // Many-to-One Relationship with Area
    @ManyToOne
    @JoinColumn(name = "area_id", nullable = false)
    @JsonIgnore  // 🚀 Prevents infinite recursion
    private Area area;

    // Constructors
    public Property() {}

    public Property(int priceWithAmenity, int propSqft) {
        this.propSqft = propSqft;
        this.priceWithAmenity = priceWithAmenity;
    }

    public Property(int propId, String propName, String propAddress, int propPrice, int propSqft, int priceWithAmenity, Area area) {
        this.propId = propId;
        this.propName = propName;
        this.propAddress = propAddress;
        this.propSqft = propSqft;
        this.propPrice = propPrice;
        this.priceWithAmenity = priceWithAmenity;
        this.area = area;
    }

    // Getters and Setters
    public int getPropId() {
        return propId;
    }

    public void setPropId(int propId) {
        this.propId = propId;
    }

    public String getPropName() {
        return propName;
    }

    public void setPropName(String propName) {
        this.propName = propName;
    }

    public String getPropAddress() {
        return propAddress;
    }

    public void setPropAddress(String propAddress) {
        this.propAddress = propAddress;
    }

    public int getPropSqft() {
        return propSqft;
    }

    public void setPropSqft(int propSqft) {
        this.propSqft = propSqft;
    }

    public int getPropPrice() {
        return propPrice;
    }

    public void setPropPrice(int propPrice) {
        this.propPrice = propPrice;
    }

    public int getPriceWithAmenity() {
        return priceWithAmenity;
    }

    public void setPriceWithAmenity(int priceWithAmenity) {
        this.priceWithAmenity = priceWithAmenity;
    }

    public Area getArea() {
        return area;
    }

    public void setArea(Area area) {
        this.area = area;
    }
}
