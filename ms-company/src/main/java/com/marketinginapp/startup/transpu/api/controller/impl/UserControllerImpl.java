package com.marketinginapp.startup.transpu.api.controller.impl;


import com.marketinginapp.startup.transpu.api.controller.UserController;
import com.marketinginapp.startup.transpu.api.payload.request.CompanyAddRequest;
import com.marketinginapp.startup.transpu.api.payload.request.EmailRequest;
import com.marketinginapp.startup.transpu.api.payload.request.UserRequest;
import com.marketinginapp.startup.transpu.api.payload.request.UserUpdateRequest;
import com.marketinginapp.startup.transpu.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor

@RestController
@RequestMapping(value = "/api/v1/user")
public class UserControllerImpl implements UserController {

    private final UserService service;

    @Override
    public ResponseEntity<?> save(UserRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.save(request));
    }

    @Override
    public ResponseEntity<?> update(UserUpdateRequest request) {
        return ResponseEntity.status(HttpStatus.OK).body(service.update(request));
    }

    @Override
    public ResponseEntity<?> updateEmail(EmailRequest request) {
        return ResponseEntity.status(HttpStatus.OK).body(service.updateEmail(request));
    }

    @Override
    public ResponseEntity<?> updateStatus(Long id) {
        return ResponseEntity.status(HttpStatus.OK).body(service.updateStatus(id));
    }

    @Override
    public ResponseEntity<?> companyAdd(CompanyAddRequest request) {
        return null;
    }

    @Override
    public ResponseEntity<?> findById(Long id) {
        return ResponseEntity.status(HttpStatus.OK).body(service.findById(id));
    }

    @Override
    public ResponseEntity<?> findByEmail(String email) {
        return ResponseEntity.status(HttpStatus.OK).body(service.findByEmail(email));
    }
}
