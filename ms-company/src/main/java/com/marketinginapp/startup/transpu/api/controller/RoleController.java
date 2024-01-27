package com.marketinginapp.startup.transpu.api.controller;

import com.marketinginapp.startup.transpu.api.payload.request.RoleRequest;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

public interface RoleController {

    @PostMapping(value = "/save")
    ResponseEntity<?> save(@RequestBody @Valid RoleRequest request);
    @PutMapping(value = "/update/{id}")
    //@PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_MODERATOR','ROLE_DRIVER','ROLE_USER')")
    ResponseEntity<?> update(@RequestBody @Valid RoleRequest request, @PathVariable Long id);
    @GetMapping(value = "/findById/{id}")
    //@PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_MODERATOR','ROLE_DRIVER','ROLE_USER')")
    ResponseEntity<?> findById(@PathVariable Long id);
}
