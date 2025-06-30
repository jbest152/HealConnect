package com.healconnect.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.healconnect.model.Medication;
import com.healconnect.service.MedicationService;

@RestController
@RequestMapping("/image")
public class ImageController {

    @Autowired
    private MedicationService medicationService;

    /**
     * Restituisce l'immagine associata a un medicinale
     * @param id ID del medicinale
     * @return ResponseEntity con byte[] dell'immagine e il MIME type
     */
    @GetMapping("/{id}")
    public ResponseEntity<byte[]> getImageByMedicationId(@PathVariable Long id) {
        Medication med = medicationService.findById(id);

        if (med.getImage() != null) {
            byte[] imageData = med.getImage().getData();
            String contentType = med.getImage().getType();

            return ResponseEntity.ok()
                    .contentType(MediaType.parseMediaType(contentType))
                    .body(imageData);
        }

        return ResponseEntity.notFound().build();
    }
}
