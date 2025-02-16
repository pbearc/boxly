package net.project.pms_backend.entity;

import jakarta.persistence.*;

@Entity
@Table(name="parcels")
public class Parcel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "block", nullable = false)
    private String block;

    @Column(name = "floor", nullable = false)
    private String floor;

    @Column(name = "unit_number", nullable = false)
    private String unitNumber;

    // No-argument constructor
    public Parcel() {}

    // All-argument constructor
    public Parcel(Long id, String block, String floor, String unitNumber) {
        this.id = id;
        this.block = block;
        this.floor = floor;
        this.unitNumber = unitNumber;
    }

    // Getter and setter methods
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getBlock() {
        return block;
    }

    public void setBlock(String block) {
        this.block = block;
    }

    public String getFloor() {
        return floor;
    }

    public void setFloor(String floor) {
        this.floor = floor;
    }

    public String getUnitNumber() {
        return unitNumber;
    }

    public void setUnitNumber(String unitNumber) {
        this.unitNumber = unitNumber;
    }
}
