package net.project.pms_backend.service;

import net.project.pms_backend.dto.ParcelDto;

import java.util.List;

public interface ParcelService {
    ParcelDto createParcel(ParcelDto parcelDto);
    ParcelDto getParcelById(Long id);
    List<ParcelDto> getAllParcels();
    ParcelDto updateParcel(Long parcelId, ParcelDto updatedParcel);

    void deleteParcel(Long id);

}
