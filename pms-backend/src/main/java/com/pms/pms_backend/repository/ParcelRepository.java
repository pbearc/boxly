package com.pms.pms_backend.repository;

import com.pms.pms_backend.entity.Parcel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ParcelRepository extends JpaRepository<Parcel, Long> {
}
