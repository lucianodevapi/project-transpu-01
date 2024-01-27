package com.marketinginapp.startup.transpu.api.controller;

import com.marketinginapp.startup.transpu.api.payload.request.PointIdParamRequest;
import com.marketinginapp.startup.transpu.api.payload.request.PointRequest;
import com.marketinginapp.startup.transpu.api.payload.request.PointUpdateRequest;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

public interface PointController {

    @PostMapping(value = "/save")
    ResponseEntity<?> save(@RequestBody PointRequest request);

    @PutMapping(value = "/update")
    ResponseEntity<?> update(@RequestBody PointUpdateRequest request);

    @GetMapping(value = "/findById/{id}")
    ResponseEntity<?> findById(@PathVariable Long id);

    @PutMapping(value = "/updateStatus/{id}")
    ResponseEntity<?> updateStatus(@PathVariable Long id);

    @GetMapping(value = "/findAllByRoute/{route}")
    ResponseEntity<?> findAllByRoute(@PathVariable Long route);

    @GetMapping(value = "/findAllByRouteAndDistrict")
    ResponseEntity<?> findAllByRouteAndDistrict(@Valid @RequestBody PointIdParamRequest request);

    @GetMapping(value = "/findAllByRouteAndAddress")
    ResponseEntity<?> findAllByRouteAndAddress(@Valid @RequestBody PointIdParamRequest request);
}
