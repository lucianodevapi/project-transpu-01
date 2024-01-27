package com.marketinginapp.startup.transpu.api.controller;

import com.marketinginapp.startup.transpu.api.payload.request.PathRequest;
import com.marketinginapp.startup.transpu.api.payload.request.PathUpdateRequest;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

public interface PathController {

    @PostMapping(value = "/save")
    ResponseEntity<?> save(@Valid @RequestBody PathRequest request);

    @PatchMapping(value = "/update")
    ResponseEntity<?> update(@Valid @RequestBody PathUpdateRequest request);

    @GetMapping(value = "/findById/{id}")
    ResponseEntity<?> findById(@PathVariable Long id);

    @PutMapping(value = "/updateStatus/{id}")
    ResponseEntity<?> updateStatus(@PathVariable Long id);

    @GetMapping(value = "/findAllByRoute/{route}")
    ResponseEntity<?> findAllByRoute(@PathVariable Long route);
}
