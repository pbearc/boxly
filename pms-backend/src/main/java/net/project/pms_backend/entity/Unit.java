package net.project.pms_backend.entity;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "pms_units")
public class Unit {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String block;

    @Column(nullable = false)
    private String floor;

    @Column(nullable = false)
    private String unit;

    @ElementCollection
    private List<String> phoneNumbers; // Added

    public Unit() {}

    public Unit(Long id, String block, String floor, String unit, List<String> phoneNumbers) {
        this.id = id;
        this.block = block;
        this.floor = floor;
        this.unit = unit;
        this.phoneNumbers = phoneNumbers;
    }

    // Getters and Setters
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

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public List<String> getPhoneNumbers() {
        return phoneNumbers;
    }

    public void setPhoneNumbers(List<String> phoneNumbers) {
        this.phoneNumbers = phoneNumbers;
    }
}
