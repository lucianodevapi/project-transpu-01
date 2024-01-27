package com.marketinginapp.startup.transpu.api.controller;

import com.marketinginapp.startup.transpu.api.payload.request.DepartureTimeRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

public interface DepartureTimeController {

    @PostMapping(value = "/save")
    ResponseEntity<?> save(@RequestBody DepartureTimeRequest request);

    @PutMapping(value = "/update/{id}")
    ResponseEntity<?> update(@RequestBody DepartureTimeRequest request, @PathVariable Long id);

    @GetMapping(value = "/findById/{id}")
    ResponseEntity<?> findById(@PathVariable Long id);

   // @PostMapping(value = "/copy")
   // ResponseEntity<Boolean> copy(@RequestBody DepartureTimeCopyRequest request);
    @PutMapping(value = "/updateStatus/{id}")
    ResponseEntity<?> updateStatus(@PathVariable Long id);

    @GetMapping(value = "/findAllByDay/{day}")
    ResponseEntity<?> findAllByCompanyId(@PathVariable Long day);
}
