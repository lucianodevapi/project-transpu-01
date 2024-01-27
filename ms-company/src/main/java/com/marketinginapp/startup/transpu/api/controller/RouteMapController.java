package com.marketinginapp.startup.transpu.api.controller;


import com.marketinginapp.startup.transpu.api.payload.request.RouteMapRequest;
import com.marketinginapp.startup.transpu.api.payload.request.RouteMapUpdateRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

public interface RouteMapController {

    @PostMapping(value = "/save")
    ResponseEntity<?> save(@RequestBody RouteMapRequest request);

    @PutMapping(value = "/update")
    ResponseEntity<?> update(@RequestBody RouteMapUpdateRequest request);

    @GetMapping(value = "/findById/{id}")
    ResponseEntity<?> findById(@PathVariable Long id);

    @PutMapping(value = "/updateStatus/{id}")
    ResponseEntity<?> updateStatus(@PathVariable Long id);

    @GetMapping(value = "/findAllByRoute/{route}")
    ResponseEntity<?> findAllByRoute(@PathVariable Long route);
}
