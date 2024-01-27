package com.marketinginapp.startup.transpu.service;

import com.marketinginapp.startup.transpu.api.payload.request.CompanyRequest;
import com.marketinginapp.startup.transpu.api.payload.request.CompanyUpdateRequest;
import com.marketinginapp.startup.transpu.api.payload.request.StatusRequest;
import com.marketinginapp.startup.transpu.api.payload.response.CompanyResponse;

public interface CompanyService {

    CompanyResponse save(CompanyRequest request);

    CompanyResponse update(CompanyUpdateRequest request);

    CompanyResponse findById(Long id);

    CompanyResponse updateStatus(StatusRequest request);
}
