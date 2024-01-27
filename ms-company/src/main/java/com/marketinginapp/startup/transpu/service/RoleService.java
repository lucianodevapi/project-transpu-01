package com.marketinginapp.startup.transpu.service;

import com.marketinginapp.startup.transpu.api.payload.request.RoleRequest;
import com.marketinginapp.startup.transpu.api.payload.response.RoleResponse;

public interface RoleService {

    RoleResponse save(RoleRequest request);

    RoleResponse update(RoleRequest request, Long id);

    RoleResponse findById(Long id);
}
