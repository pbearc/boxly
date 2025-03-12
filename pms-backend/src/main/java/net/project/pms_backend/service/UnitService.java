package net.project.pms_backend.service;

import net.project.pms_backend.dto.UnitDto;

import java.util.List;

public interface UnitService {
    List<UnitDto> getAllUnits();
    UnitDto createUnit(UnitDto unitDTO);
    UnitDto getUnitById(Long id);

}
