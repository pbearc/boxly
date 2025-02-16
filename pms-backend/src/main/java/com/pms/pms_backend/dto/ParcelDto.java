package com.pms.pms_backend.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
public class ParcelDto {
    private Long id;
    private String block;
    private String floor;
    private String unitNumber;
    @CreationTimestamp
    private LocalDateTime deliveredTime;

    // Add the required constructor
    public ParcelDto(Long id, String block, String floor, String unitNumber, LocalDateTime deliveredTime) {
        this.id = id;
        this.block = block;
        this.floor = floor;
        this.unitNumber = unitNumber;
        this.deliveredTime = deliveredTime;
    }

    // Getters and setters
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

    public LocalDateTime getDeliveredTime() {
        return deliveredTime;
    }

    public void setDeliveredTime(LocalDateTime deliveredTime) {
        this.deliveredTime = deliveredTime;
    }
}