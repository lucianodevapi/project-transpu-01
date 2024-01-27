package com.marketinginapp.startup.transpu.api.controller.impl;

import com.marketinginapp.startup.transpu.api.controller.RouteController;
import com.marketinginapp.startup.transpu.api.payload.request.RouteRequest;
import com.marketinginapp.startup.transpu.api.payload.request.RouteUpdateRequest;
import com.marketinginapp.startup.transpu.api.payload.request.StatusRequest;
import com.marketinginapp.startup.transpu.api.payload.response.RouteResponse;
import com.marketinginapp.startup.transpu.service.RouteService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor

@RestController
@RequestMapping(value = "/api/v1/route")
public class RouteControllerImpl implements RouteController {

    private final RouteService service;

    @Override
    public ResponseEntity<?> save(RouteRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.save(request));
    }

    @Override
    public ResponseEntity<?> update(RouteUpdateRequest request) {
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

    @Override
    public ResponseEntity<?> findAllByCompanyId(Long id) {
        List<RouteResponse> responses = service.findAllByCompanyId(id);
        if(!responses.isEmpty()){
            return ResponseEntity.status(HttpStatus.OK).body(responses);
        }
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
