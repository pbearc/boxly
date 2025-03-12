package net.project.pms_backend.controller;

import net.project.pms_backend.service.impl.GeminiService;
import net.project.pms_backend.service.impl.OCRService;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@CrossOrigin("*")
@RestController
@RequestMapping("/api/ocr")
public class OCRController {

    @Autowired
    private OCRService ocrService;

    @Autowired
    private GeminiService geminiService;  // Autowire GeminiService

    @PostMapping("/parse")
    public ResponseEntity<String> parseParcelInfo(@RequestParam("image") MultipartFile file) {
        try {
            // Step 1: Extract text and barcode from the image
            String extractedInfo = ocrService.extractTextAndBarcodeFromImage(file);

            // Step 2: Call GeminiService to extract parcel info from the extracted text
            JSONObject parcelInfo = geminiService.extractInfoFromGemini(extractedInfo);

            // Step 3: If parcelInfo is null, return an error response
            if (parcelInfo == null) {
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error extracting parcel information from Gemini.");
            }

            // Step 4: Extract barcode from the OCR result and add it to parcelInfo
            String[] lines = extractedInfo.split("\n");
            for (String line : lines) {
                if (line.startsWith("BARCODE: ")) {
                    parcelInfo.put("barcode", line.substring(9).trim());
                    break;
                }
            }

            // Step 5: Return the extracted parcel information as a JSON response
            return ResponseEntity.ok(parcelInfo.toString());
        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("OCR Error: " + e.getMessage());
        }
    }
}