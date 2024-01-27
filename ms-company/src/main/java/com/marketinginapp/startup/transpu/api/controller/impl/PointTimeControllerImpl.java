package com.marketinginapp.startup.transpu.api.controller.impl;

import com.marketinginapp.startup.transpu.api.controller.PointTimeController;
import com.marketinginapp.startup.transpu.api.payload.request.PointTimeRequest;
import com.marketinginapp.startup.transpu.api.payload.request.PointTimeUpdateRequest;
import com.marketinginapp.startup.transpu.service.PointTimeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor

@RestController
@RequestMapping(value = "/api/v1/point_time")
public class PointTimeControllerImpl implements PointTimeController {

    private final PointTimeService service;

    @Override
    public ResponseEntity<?> save(PointTimeRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.save(request));
    }

    @Override
    public ResponseEntity<?> update(PointTimeUpdateRequest request) {
        return ResponseEntity.status(HttpStatus.OK).body(service.update(request));
    }

    @Override
    public ResponseEntity<?> findById(Long id) {
        return ResponseEntity.status(HttpStatus.OK).body(service.findById(id));
    }

    @Override
    public ResponseEntity<?> updateStatus(Long id) {
        return ResponseEntity.status(HttpStatus.OK).body(service.updateStatus(id));
    }
}
