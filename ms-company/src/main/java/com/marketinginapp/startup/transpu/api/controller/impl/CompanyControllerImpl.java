package com.marketinginapp.startup.transpu.api.controller.impl;

import com.marketinginapp.startup.transpu.api.controller.CompanyController;
import com.marketinginapp.startup.transpu.api.payload.request.CompanyRequest;
import com.marketinginapp.startup.transpu.api.payload.request.CompanyUpdateRequest;
import com.marketinginapp.startup.transpu.api.payload.request.StatusRequest;
import com.marketinginapp.startup.transpu.service.CompanyService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor

@RestController
@RequestMapping(value = "/api/v1/company")
public class CompanyControllerImpl implements CompanyController {

    private final CompanyService service;

    @Override
    public ResponseEntity<?> save(CompanyRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.save(request));
    }

    @Override
    public ResponseEntity<?> update(CompanyUpdateRequest request) {
        return ResponseEntity.status(HttpStatus.OK).body(service.update(request));
    }

    @Override
    public ResponseEntity<?> findById(Long id) {
        return ResponseEntity.status(HttpStatus.OK).body(service.findById(id));
    }

    @Override
    public ResponseEntity<?> updateStatus(StatusRequest request) {
        return ResponseEntity.status(HttpStatus.OK).body(service.updateStatus(request));
    }
}
