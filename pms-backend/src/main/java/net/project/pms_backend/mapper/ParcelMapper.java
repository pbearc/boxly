package net.project.pms_backend.mapper;

import net.project.pms_backend.dto.ParcelDto;
import net.project.pms_backend.entity.Parcel;

public class ParcelMapper {
    public static ParcelDto mapToParcelDto(Parcel parcel) {
        return new ParcelDto(
                parcel.getId(),
                parcel.getBlock(),
                parcel.getFloor(),
                parcel.getUnitNumber()
        );
    }

    public static Parcel mapToParcel(ParcelDto parcelDto) {
        return new Parcel(
                parcelDto.getId(),
                parcelDto.getBlock(),
                parcelDto.getFloor(),
                parcelDto.getUnitNumber()
        );
    }
}
