package com.marketinginapp.startup.transpu.api.controller.impl;

import com.marketinginapp.startup.transpu.api.controller.RoleController;
import com.marketinginapp.startup.transpu.api.payload.request.RoleRequest;
import com.marketinginapp.startup.transpu.service.RoleService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor

@RestController
@RequestMapping(value = "/api/v1/role")
public class RoleControllerImpl implements RoleController {

    private final RoleService service;

    @Override
    public ResponseEntity<?> save(RoleRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.save(request));
    }

    @Override
    public ResponseEntity<?> update(RoleRequest request, Long id) {
        return ResponseEntity.status(HttpStatus.OK).body(service.update(request, id));
    }

    @Override
    public ResponseEntity<?> findById(Long id) {
        return ResponseEntity.status(HttpStatus.OK).body(service.findById(id));
    }
}
