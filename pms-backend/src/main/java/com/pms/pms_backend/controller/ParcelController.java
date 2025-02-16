package com.pms.pms_backend.controller;

import com.pms.pms_backend.dto.ParcelDto;
import com.pms.pms_backend.service.ParcelService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController // so that it can handle http request
@RequestMapping("/api/parcels")
@AllArgsConstructor
public class ParcelController {
    private ParcelService parcelService;
    // Build ADD Parcel Rest API
    @PostMapping
    public ResponseEntity<ParcelDto> createParcel(@RequestBody ParcelDto parcelDto){
        ParcelDto savedParcel = parcelService.createParcel(parcelDto);
        return new ResponseEntity<>(savedParcel, HttpStatus.CREATED);
    }
}
