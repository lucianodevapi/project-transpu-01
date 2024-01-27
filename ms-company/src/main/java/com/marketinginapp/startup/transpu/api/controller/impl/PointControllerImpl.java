package com.marketinginapp.startup.transpu.api.controller.impl;

import com.marketinginapp.startup.transpu.api.controller.PointController;
import com.marketinginapp.startup.transpu.api.payload.request.PointIdParamRequest;
import com.marketinginapp.startup.transpu.api.payload.request.PointRequest;
import com.marketinginapp.startup.transpu.api.payload.request.PointUpdateRequest;
import com.marketinginapp.startup.transpu.api.payload.response.PointResponse;
import com.marketinginapp.startup.transpu.service.PointService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor

@RestController
@RequestMapping(value = "/api/v1/point")
public class PointControllerImpl implements PointController {

    private final PointService service;

    @Override
    public ResponseEntity<?> save(PointRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.save(request));
    }

    @Override
    public ResponseEntity<?> update(PointUpdateRequest request) {
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
    public ResponseEntity<?> findAllByRoute(Long route) {
        List<PointResponse> responses = service.findAllByRoute(route);
        if(!responses.isEmpty()){
            return ResponseEntity.status(HttpStatus.OK).body(responses);
        }
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @Override
    public ResponseEntity<?> findAllByRouteAndDistrict(PointIdParamRequest request) {
        List<PointResponse> responses = service.findAllByRouteAndDistrict(request);
        if(!responses.isEmpty()){
            return ResponseEntity.status(HttpStatus.OK).body(responses);
        }
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @Override
    public ResponseEntity<?> findAllByRouteAndAddress(PointIdParamRequest request) {
        List<PointResponse> responses = service.findAllByRouteAndAddress(request);
        if(!responses.isEmpty()){
            return ResponseEntity.status(HttpStatus.OK).body(responses);
        }
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
