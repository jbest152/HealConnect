package com.healconnect.controller;

import com.healconnect.model.BaseEntity;
import com.healconnect.service.GenericService;

import java.lang.reflect.InvocationTargetException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

public abstract class GenericController<T extends BaseEntity> {

	@Autowired
	protected GenericService<T, Long> service;
	private final String className;
	private final Class<T> clazz;

	public GenericController(Class<T> clazz) {
		this.clazz = clazz;
		this.className = this.clazz.getSimpleName().toLowerCase();
	}

	@GetMapping
	public String list(Model model) {
		model.addAttribute(className + "s", service.findAll());
		return className + "/list";
	}

	@GetMapping("/{id}")
	public String view(@PathVariable Long id, Model model) {
		T item = service.findById(id);

		model.addAttribute(className, item);
		return className + "/view";
	}

	@GetMapping("/new")
	public String showCreateForm(Model model) throws Exception {
		model.addAttribute("item", getEntityInstance());
		return className + "/create";
	}

	@PostMapping
	public String create(@ModelAttribute("item") T item) {
		service.save(item);
		return "redirect:/" + className + "/" + item.getId();
	}

	@GetMapping("/{id}/edit")
	public String showEditForm(@PathVariable Long id, Model model) {
		T item = service.findById(id);

		model.addAttribute("item", item);
		return className + "/edit";
	}

	@PostMapping("/{id}")
	public String update(@PathVariable Long id, @ModelAttribute("item") T item) {
		item.setId(id);
		service.save(item);
		return "redirect:/" + className + "/" + id;
	}

	@PostMapping("/{id}/delete")
	public String delete(@PathVariable Long id) {
		service.deleteById(id);
		return "redirect:/" + className;
	}

	private T getEntityInstance() throws InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException {
		return clazz.getDeclaredConstructor().newInstance();
	}
}

