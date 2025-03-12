package net.project.pms_backend.dto;

import java.util.List;

public class UnitDto {
    private Long id;
    private String block;
    private String floor;
    private String unit;
    private List<String> phoneNumbers; // Changed from PhoneNumberDto to String

    public UnitDto() {}

    public UnitDto(Long id, String block, String floor, String unit, List<String> phoneNumbers) {
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
