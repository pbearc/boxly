package net.project.pms_backend.controller;

import net.project.pms_backend.dto.UnitDto;
import net.project.pms_backend.service.UnitService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin("*")
@RestController
@RequestMapping("/api/units")
public class UnitController {

    private final UnitService unitService;

    public UnitController(UnitService unitService) {
        this.unitService = unitService;
    }

    @GetMapping
    public ResponseEntity<List<UnitDto>> getAllUnits() {
        List<UnitDto> unitDto = unitService.getAllUnits();
        return new ResponseEntity<>(unitDto, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<UnitDto> createUnit(@RequestBody UnitDto unitDto) {
        // Debug log to check what data is being received
        System.out.println("Received UnitDto: " + unitDto);

        UnitDto savedUnit = unitService.createUnit(unitDto);

        // Log the saved unit
        System.out.println("Saved UnitDto: " + savedUnit);

        return new ResponseEntity<>(savedUnit, HttpStatus.CREATED);
    }


    @GetMapping("/{id}")
    public ResponseEntity<UnitDto> getUnitById(@PathVariable Long id) {
        UnitDto unitDto = unitService.getUnitById(id);
        return new ResponseEntity<>(unitDto, HttpStatus.OK);
    }
}
