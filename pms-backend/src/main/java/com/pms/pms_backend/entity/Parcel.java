package com.pms.pms_backend.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDate;
import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
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

    @CreationTimestamp
    @Column(name = "delivered_time", nullable = false)
    private LocalDateTime deliveredTime;

    public Parcel(Long id, String block, String floor, String unitNumber, LocalDateTime deliveredTime) {
        this.id = id;
        this.block = block;
        this.floor = floor;
        this.unitNumber = unitNumber;
        this.deliveredTime = deliveredTime;
    }
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
