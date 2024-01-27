package com.marketinginapp.startup.transpu.service;

import com.marketinginapp.startup.transpu.api.payload.request.DayRequest;
import com.marketinginapp.startup.transpu.api.payload.request.DayUpdateRequest;
import com.marketinginapp.startup.transpu.api.payload.response.DayResponse;

import java.util.List;

public interface DayService {

    DayResponse save(DayRequest request);

    DayResponse update(DayUpdateRequest request);

    DayResponse findById(Long id);

    DayResponse updateStatus(Long id);

    List<DayResponse> findAllByRouteId(Long route);
}
