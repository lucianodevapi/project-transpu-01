package com.marketinginapp.startup.transpu.api.controller.impl;


import com.marketinginapp.startup.transpu.api.controller.DayController;
import com.marketinginapp.startup.transpu.api.payload.request.DayRequest;
import com.marketinginapp.startup.transpu.api.payload.request.DayUpdateRequest;
import com.marketinginapp.startup.transpu.api.payload.response.DayResponse;
import com.marketinginapp.startup.transpu.service.DayService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor

@RestController
@RequestMapping(value = "/api/v1/day")
public class DayControllerImpl implements DayController {

    private final DayService service;

    @Override
    public ResponseEntity<?> save(DayRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.save(request));
    }

    @Override
    public ResponseEntity<?> update(DayUpdateRequest request) {
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

    @Override
    public ResponseEntity<?> findAllByCompanyId(Long route) {
        List<DayResponse> responses = service.findAllByRouteId(route);
        if(!responses.isEmpty()){
            return ResponseEntity.status(HttpStatus.OK).body(responses);
        }
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
