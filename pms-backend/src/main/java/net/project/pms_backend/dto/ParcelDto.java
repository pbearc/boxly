package net.project.pms_backend.dto;

public class ParcelDto {
    private Long id;
    private String block;
    private String floor;
    private String unitNumber;

    // No-argument constructor
    public ParcelDto() {}

    // All-arguments constructor
    public ParcelDto(Long id, String block, String floor, String unitNumber) {
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
