package com.marketinginapp.startup.transpu.api.controller.impl;

import com.marketinginapp.startup.transpu.api.controller.PathController;
import com.marketinginapp.startup.transpu.api.payload.request.PathRequest;
import com.marketinginapp.startup.transpu.api.payload.request.PathUpdateRequest;
import com.marketinginapp.startup.transpu.api.payload.response.PathResponse;
import com.marketinginapp.startup.transpu.service.PathService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor

@RestController
@RequestMapping(value = "/api/v1/path")
public class PathControllerImpl implements PathController {

    private final PathService service;

    @Override
    public ResponseEntity<?> save(PathRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.save(request));
    }

    @Override
    public ResponseEntity<?> update(PathUpdateRequest request) {
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
        List<PathResponse> responses = service.findAllByRoute(route);
        if(!responses.isEmpty()){
            return ResponseEntity.status(HttpStatus.OK).body(responses);
        }
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
