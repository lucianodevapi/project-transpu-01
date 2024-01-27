package com.marketinginapp.startup.transpu.api.controller;


import com.marketinginapp.startup.transpu.api.payload.request.CompanyAddRequest;
import com.marketinginapp.startup.transpu.api.payload.request.EmailRequest;
import com.marketinginapp.startup.transpu.api.payload.request.UserRequest;
import com.marketinginapp.startup.transpu.api.payload.request.UserUpdateRequest;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

public interface UserController {

    @PostMapping(value = "/save")
    //@PreAuthorize("hasRole('ROLE_ADMIN')")
    ResponseEntity<?> save(@Valid @RequestBody UserRequest request);
    @PatchMapping(value = "/update")
    //@PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_MODERATOR','ROLE_DRIVER','ROLE_USER')")
    ResponseEntity<?> update(@Valid @RequestBody UserUpdateRequest request);
    @PutMapping(value = "/updateEmail")
    //@PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_MODERATOR','ROLE_DRIVER','ROLE_USER')")
    ResponseEntity<?> updateEmail(@Valid @RequestBody EmailRequest request);
    @PutMapping(value = "/updateStatus/{id}")
    //@PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_MODERATOR','ROLE_DRIVER','ROLE_USER')")
    ResponseEntity<?> updateStatus(@PathVariable Long id);
    @PutMapping(value = "/companyAdd")
    //@PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_MODERATOR','ROLE_DRIVER','ROLE_USER')")
    ResponseEntity<?> companyAdd(@Valid @RequestBody CompanyAddRequest request);
    @GetMapping(value = "/findById/{id}")
    //@PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_MODERATOR','ROLE_DRIVER','ROLE_USER')")
    ResponseEntity<?> findById(@PathVariable Long id);
    @GetMapping(value = "/findByEmail/{email}")
    //@PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_MODERATOR','ROLE_DRIVER','ROLE_USER')")
    ResponseEntity<?> findByEmail(@PathVariable String email);
}
