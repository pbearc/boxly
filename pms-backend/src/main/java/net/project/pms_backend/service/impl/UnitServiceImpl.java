package net.project.pms_backend.service.impl;

import net.project.pms_backend.dto.UnitDto;
import net.project.pms_backend.entity.Unit;
import net.project.pms_backend.exception.ResourceNotFoundException;
import net.project.pms_backend.mapper.UnitMapper;
import net.project.pms_backend.repository.UnitRepository;
import net.project.pms_backend.service.UnitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UnitServiceImpl implements UnitService {

    @Autowired
    private UnitRepository unitRepository;

    @Autowired
    private UnitMapper unitMapper;

    public UnitServiceImpl(UnitRepository unitRepository, UnitMapper unitMapper) {
        this.unitRepository = unitRepository;
        this.unitMapper = unitMapper;
    }

    @Override
    public List<UnitDto> getAllUnits() {
        return unitRepository.findAll().stream()
                .map(unitMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public UnitDto createUnit(UnitDto unitDTO) {
        Unit unit = unitMapper.toEntity(unitDTO);
        unit = unitRepository.save(unit);
        return unitMapper.toDTO(unit);
    }

    @Override
    public UnitDto getUnitById(Long id) {
        Unit unit = unitRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Unit not found with id: " + id));
        return unitMapper.toDTO(unit);
    }
}
