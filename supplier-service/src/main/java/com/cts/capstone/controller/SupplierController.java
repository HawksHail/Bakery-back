package com.cts.capstone.controller;

import com.cts.capstone.model.Supplier;
import com.cts.capstone.service.SupplierService;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("category")
public class SupplierController {

	private SupplierService supplierService;

	public SupplierController(SupplierService supplierService) {
		super();
		this.supplierService = supplierService;
	}

	public SupplierService getSupplierService() {
		return supplierService;
	}

	public void setSupplierService(SupplierService categoryService) {
		this.supplierService = categoryService;
	}

	@GetMapping()
	public List<Supplier> getAllCategories() {
		return supplierService.findAll();
	}

	@GetMapping("{id}")
	public Supplier getSupplier(@PathVariable Long id) {
		return supplierService.findById(id);
	}

	@PostMapping()
	public Supplier addSupplier(@RequestBody Supplier category) {
		return supplierService.add(category);
	}
}
