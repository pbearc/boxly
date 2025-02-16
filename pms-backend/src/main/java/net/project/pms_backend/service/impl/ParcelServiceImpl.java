package net.project.pms_backend.service.impl;

import net.project.pms_backend.dto.ParcelDto;
import net.project.pms_backend.entity.Parcel;
import net.project.pms_backend.exception.ResourceNotFoundException;
import net.project.pms_backend.mapper.ParcelMapper;
import net.project.pms_backend.repository.ParcelRepository;
import net.project.pms_backend.service.ParcelService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ParcelServiceImpl implements ParcelService {

    private ParcelRepository parcelRepository;

    // Constructor-based dependency injection
    public ParcelServiceImpl(ParcelRepository parcelRepository) {
        this.parcelRepository = parcelRepository;
    }

    @Override
    public ParcelDto createParcel(ParcelDto parcelDto) {
        Parcel parcel = ParcelMapper.mapToParcel(parcelDto);
        Parcel savedParcel = parcelRepository.save(parcel);
        return ParcelMapper.mapToParcelDto(savedParcel);
    }

    @Override
    public ParcelDto getParcelById(Long parcelId) {
        Parcel parcel = parcelRepository.findById(parcelId).orElseThrow(() -> new ResourceNotFoundException("Parcel not found with id: " + parcelId));
        return ParcelMapper.mapToParcelDto(parcel);
    }

    @Override
    public List<ParcelDto> getAllParcels() {
        List<Parcel> parcels = parcelRepository.findAll();
        return parcels.stream().map((parcel -> ParcelMapper.mapToParcelDto(parcel))).collect(Collectors.toList());
    }

    @Override
    public ParcelDto updateParcel(Long parcelId, ParcelDto updatedParcel) {
        Parcel parcel = parcelRepository.findById(parcelId).orElseThrow(() -> new ResourceNotFoundException("Parcel not found with id: " + parcelId));
        parcel.setBlock(updatedParcel.getBlock());
        parcel.setFloor(updatedParcel.getFloor());
        parcel.setUnitNumber(updatedParcel.getUnitNumber());
        Parcel updated = parcelRepository.save(parcel);
        return ParcelMapper.mapToParcelDto(updated);
    }


    @Override
    public void deleteParcel(Long id) {
        Parcel parcel = parcelRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Parcel not found with id: " + id));
        parcelRepository.delete(parcel);


    }

}
