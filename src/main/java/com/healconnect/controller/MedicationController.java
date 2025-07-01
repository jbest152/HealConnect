package com.healconnect.controller;

import java.io.IOException;

import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.healconnect.model.Image;
import com.healconnect.model.Medication;

import jakarta.validation.Valid;

@Controller
@RequestMapping("/medication")
public class MedicationController extends GenericController<Medication> {

	public MedicationController() {
		super(Medication.class);
	}
	
	@PostMapping("/create")
    public String createMedication(@Valid @ModelAttribute("item") Medication item,
                                   BindingResult result,
                                   @RequestParam(value = "imageFile", required = false) MultipartFile imageFile) throws IOException {

        if (result.hasErrors()) {
            return "medication/create";
        }

        if (imageFile != null && !imageFile.isEmpty()) {
            Image img = new Image();
            img.setName(imageFile.getOriginalFilename());
            img.setType(imageFile.getContentType());
            img.setData(imageFile.getBytes());
            item.setImage(img);
        }

        service.save(item);
        return "redirect:/medication/" + item.getId();
    }
	
	@PostMapping("/{id}/edit")
    public String updateMedication(@Valid @ModelAttribute("item") Medication item,
                                   BindingResult result,
                                   @RequestParam(value = "imageFile", required = false) MultipartFile imageFile) throws IOException {

        if (result.hasErrors()) {
            return "medication/create";
        }

        if (imageFile != null && !imageFile.isEmpty()) {
            Image img = new Image();
            img.setName(imageFile.getOriginalFilename());
            img.setType(imageFile.getContentType());
            img.setData(imageFile.getBytes());
            item.setImage(img);
        }

        service.save(item);
        return "redirect:/medication/" + item.getId();
    }
}
