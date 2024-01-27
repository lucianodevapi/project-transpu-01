package com.marketinginapp.startup.transpu.api.controller;


import com.marketinginapp.startup.transpu.api.payload.request.PointTimeRequest;
import com.marketinginapp.startup.transpu.api.payload.request.PointTimeUpdateRequest;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

public interface PointTimeController {

    @PostMapping(value = "/save")
    ResponseEntity<?> save(@Valid @RequestBody PointTimeRequest request);

    @PutMapping(value = "/update")
    ResponseEntity<?> update(@Valid @RequestBody PointTimeUpdateRequest request);

    @GetMapping(value = "/findById/{id}")
    ResponseEntity<?> findById(@PathVariable Long id);

    @PutMapping(value = "/updateStatus/{id}")
    ResponseEntity<?> updateStatus(@PathVariable Long id);
}
