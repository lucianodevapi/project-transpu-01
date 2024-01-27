package com.marketinginapp.startup.transpu.service;

import com.marketinginapp.startup.transpu.api.payload.request.CompanyAddRequest;
import com.marketinginapp.startup.transpu.api.payload.request.EmailRequest;
import com.marketinginapp.startup.transpu.api.payload.request.UserRequest;
import com.marketinginapp.startup.transpu.api.payload.request.UserUpdateRequest;
import com.marketinginapp.startup.transpu.api.payload.response.UserResponse;

public interface UserService{
    UserResponse save(UserRequest request);

    UserResponse update(UserUpdateRequest request);

    UserResponse addCompany(CompanyAddRequest request);

    UserResponse updateEmail(EmailRequest request);

    UserResponse findById(Long id);

    UserResponse findByEmail(String email);

    UserResponse updateStatus(Long id);

    UserResponse updateUser(UserRequest request, Long id);

}
