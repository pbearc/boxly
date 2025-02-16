package net.project.pms_backend.controller;

import net.project.pms_backend.dto.ParcelDto;
import net.project.pms_backend.service.ParcelService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin("*")
@RestController
@RequestMapping("/api/parcels")
public class ParcelController {

    private final ParcelService parcelService;

    // Constructor for dependency injection
    public ParcelController(ParcelService parcelService) {
        this.parcelService = parcelService;
    }

    // Build ADD Parcel Rest API
    @PostMapping
    public ResponseEntity<ParcelDto> createParcel(@RequestBody ParcelDto parcelDto){
        ParcelDto savedParcel = parcelService.createParcel(parcelDto);
        return new ResponseEntity<>(savedParcel, HttpStatus.CREATED);
    }

    // Build GET Parcel by ID Rest API
    @GetMapping("/{id}")
    public ResponseEntity<ParcelDto> getParcelById(@PathVariable("id") Long id){
        ParcelDto parcelDto = parcelService.getParcelById(id);
        return new ResponseEntity<>(parcelDto, HttpStatus.OK);
    }

    // Build GET All Parcels Rest API
    @GetMapping
    public ResponseEntity<List<ParcelDto>> getAllParcels(){
        return new ResponseEntity<>(parcelService.getAllParcels(), HttpStatus.OK);
    }

    // Build UPDATE Parcel Rest API
    @PutMapping("/{id}")
    public ResponseEntity<ParcelDto> updateParcel(@PathVariable Long id, @RequestBody ParcelDto parcelDto){
        ParcelDto updatedParcel = parcelService.updateParcel(id, parcelDto);
        return new ResponseEntity<>(updatedParcel, HttpStatus.OK);
    }

    // Build DELETE Parcel Rest API
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteParcel(@PathVariable Long id){
        parcelService.deleteParcel(id);
        return new ResponseEntity<>("Parcel deleted successfully", HttpStatus.OK);
    }
}
