package com.marketinginapp.startup.transpu.api.controller.impl;

import com.marketinginapp.startup.transpu.api.controller.RouteMapController;
import com.marketinginapp.startup.transpu.api.payload.request.RouteMapRequest;
import com.marketinginapp.startup.transpu.api.payload.request.RouteMapUpdateRequest;
import com.marketinginapp.startup.transpu.api.payload.response.RouteMapResponse;
import com.marketinginapp.startup.transpu.service.RouteMapService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor

@RestController
@RequestMapping(value = "/api/v1/route_map")
public class RouteMapControllerImpl implements RouteMapController {

    private final RouteMapService service;

    @Override
    public ResponseEntity<?> save(RouteMapRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.save(request));
    }

    @Override
    public ResponseEntity<?> update(RouteMapUpdateRequest request) {
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
        List<RouteMapResponse> list = service.findAllByRoute(route);
        if(!list.isEmpty()){
            return ResponseEntity.status(HttpStatus.OK).body(list);
        }
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
