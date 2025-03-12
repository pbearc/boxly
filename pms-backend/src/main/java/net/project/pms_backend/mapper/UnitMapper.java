package net.project.pms_backend.mapper;

import net.project.pms_backend.dto.UnitDto;
import net.project.pms_backend.entity.Unit;
import org.springframework.stereotype.Component;

@Component
public class UnitMapper {

    public UnitDto toDTO(Unit unit) {
        return new UnitDto(
                unit.getId(),
                unit.getBlock(),
                unit.getFloor(),
                unit.getUnit(),
                unit.getPhoneNumbers() // Changed
        );
    }

    public Unit toEntity(UnitDto unitDTO) {
        return new Unit(
                unitDTO.getId(),
                unitDTO.getBlock(),
                unitDTO.getFloor(),
                unitDTO.getUnit(),
                unitDTO.getPhoneNumbers() // Changed
        );
    }
}
