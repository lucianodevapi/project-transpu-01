package com.marketinginapp.startup.transpu.api.controller;

import com.marketinginapp.startup.transpu.api.payload.request.RouteRequest;
import com.marketinginapp.startup.transpu.api.payload.request.RouteUpdateRequest;
import com.marketinginapp.startup.transpu.api.payload.request.StatusRequest;
import com.marketinginapp.startup.transpu.api.payload.response.RouteResponse;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

public interface RouteController {

    @PostMapping(value = "/save")
    ResponseEntity<?> save(@Valid @RequestBody RouteRequest request);

    @PatchMapping(value = "/update")
    ResponseEntity<?> update(@Valid @RequestBody RouteUpdateRequest request);

    @GetMapping(value = "/findById/{id}")
    ResponseEntity<?> findById(@PathVariable Long id);

    @PutMapping(value = "/updateStatus")
    ResponseEntity<?> updateStatus(@RequestBody StatusRequest request);

    @GetMapping(value = "/findAllByCompany/{id}")
    ResponseEntity<?> findAllByCompanyId(@PathVariable Long id);
}
