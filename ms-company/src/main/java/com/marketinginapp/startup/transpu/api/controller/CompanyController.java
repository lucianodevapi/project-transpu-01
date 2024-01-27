package com.marketinginapp.startup.transpu.api.controller;

import com.marketinginapp.startup.transpu.api.payload.request.CompanyRequest;
import com.marketinginapp.startup.transpu.api.payload.request.CompanyUpdateRequest;
import com.marketinginapp.startup.transpu.api.payload.request.StatusRequest;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

public interface CompanyController {
    @PostMapping(value = "/save")
    ResponseEntity<?> save(@Valid @RequestBody CompanyRequest request);
    @PatchMapping(value = "/update")
    ResponseEntity<?> update(@Valid @RequestBody CompanyUpdateRequest request);
    @GetMapping(value = "/findById/{id}")
    ResponseEntity<?> findById(@PathVariable Long id);
    @PutMapping(value = "/updateStatus")
    ResponseEntity<?> updateStatus(@RequestBody StatusRequest request);
}
