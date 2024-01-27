package com.marketinginapp.startup.transpu.api.controller;


import com.marketinginapp.startup.transpu.api.payload.request.DayRequest;
import com.marketinginapp.startup.transpu.api.payload.request.DayUpdateRequest;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

public interface DayController {

    @PostMapping(value = "/save")
    ResponseEntity<?> save(@Valid @RequestBody DayRequest request);

    @PatchMapping(value = "/update")
    ResponseEntity<?> update(@Valid @RequestBody DayUpdateRequest request);

    @GetMapping(value = "/findById/{id}")
    ResponseEntity<?> findById(@PathVariable Long id);

    @PutMapping(value = "/updateStatus/{id}")
    ResponseEntity<?> updateStatus(@PathVariable Long id);

    @GetMapping(value = "/findAllByRoute/{route}")
    ResponseEntity<?> findAllByCompanyId(@PathVariable Long route);
}
