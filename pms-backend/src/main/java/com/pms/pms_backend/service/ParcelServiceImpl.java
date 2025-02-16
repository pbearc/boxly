package com.pms.pms_backend.service;

import com.pms.pms_backend.dto.ParcelDto;
import com.pms.pms_backend.entity.Parcel;
import com.pms.pms_backend.mapper.ParcelMapper;
import com.pms.pms_backend.repository.ParcelRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class ParcelServiceImpl implements ParcelService{
    private ParcelRepository parcelRepository;
    @Override
    public ParcelDto createParcel(ParcelDto parcelDto) {
        Parcel parcel = ParcelMapper.mapToParcel(parcelDto);
        Parcel savedParcel = parcelRepository.save(parcel);
        return ParcelMapper.mapToParcelDto(savedParcel);
    }
}
