package com.healconnect.controller;

import com.healconnect.model.BaseEntity;
import com.healconnect.service.CredentialsService;
import com.healconnect.service.GenericService;

import jakarta.validation.Valid;

import java.lang.reflect.InvocationTargetException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

public abstract class GenericController<T extends BaseEntity> {

	@Autowired
	protected GenericService<T, Long> service;
	
	@Autowired
	private CredentialsService credentialsService;
	
	private final String className;
	private final Class<T> clazz;

	public GenericController(Class<T> clazz) {
		this.clazz = clazz;
		this.className = this.clazz.getSimpleName().toLowerCase();
	}

	@GetMapping
	public String list(@AuthenticationPrincipal UserDetails userDetails, Model model) {
		if (userDetails == null)
			model.addAttribute("user", null);
		else
			model.addAttribute("user", credentialsService.findByUsername(userDetails.getUsername()).getUser());
		model.addAttribute(className + "s", service.findAll());
		return className + "/list";
	}

	@GetMapping("/{id}")
	public String view(@PathVariable Long id, Model model) {
		T item = service.findById(id);

		model.addAttribute(className, item);
		return className + "/view";
	}

	@PreAuthorize("hasAuthority('ADMIN')")
	@GetMapping("/new")
	public String showCreateForm(Model model){
		try {
			model.addAttribute("item", getEntityInstance());
		} catch (Exception e) {
			return "redirect:/" + className;
		}
		return className + "/create";
	}
	
	@PostMapping
	public String create(@Valid @ModelAttribute("item") T item, BindingResult result) {
		if (result.hasErrors()) {
			return className + "/create";
		}
		service.save(item);
		return "redirect:/" + className + "/" + item.getId();
	}

	@PreAuthorize("hasAuthority('ADMIN')")
	@GetMapping("/{id}/edit")
	public String showEditForm(@PathVariable Long id, Model model) {
		T item = service.findById(id);

		model.addAttribute("item", item);
		return className + "/edit";
	}

	@PostMapping("/{id}")
	public String update(@Valid @ModelAttribute("item") T item, BindingResult result, @PathVariable Long id) {
		if (result.hasErrors()) {
			return className + "/edit";
		}
		service.save(item);
		return "redirect:/" + className + "/" + id;
	}

	@PreAuthorize("hasAuthority('ADMIN')")
	@PostMapping("/{id}/delete")
	public String delete(@PathVariable Long id, @AuthenticationPrincipal UserDetails userDetails) {
		service.deleteById(id);
		return "redirect:/" + className;
	}

	private T getEntityInstance() throws InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException {
		return clazz.getDeclaredConstructor().newInstance();
	}
}

