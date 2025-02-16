package com.pms.pms_backend.mapper;

import com.pms.pms_backend.dto.ParcelDto;
import com.pms.pms_backend.entity.Parcel;
import org.springframework.web.bind.annotation.Mapping;


public class ParcelMapper {
    public static ParcelDto mapToParcelDto(Parcel parcel) {
        return new ParcelDto(
                parcel.getId(),
                parcel.getBlock(),
                parcel.getFloor(),
                parcel.getUnitNumber(),
                parcel.getDeliveredTime()
        );
    }

    public static Parcel mapToParcel(ParcelDto parcelDto) {
        return new Parcel(
                parcelDto.getId(),
                parcelDto.getBlock(),
                parcelDto.getFloor(),
                parcelDto.getUnitNumber(),
                parcelDto.getDeliveredTime()
        );
    }
}
