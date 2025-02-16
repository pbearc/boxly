package com.pms.pms_backend.service;

import com.pms.pms_backend.dto.ParcelDto;
import com.pms.pms_backend.entity.Parcel;

public interface ParcelService {
    ParcelDto createParcel(ParcelDto parcelDto);
}
