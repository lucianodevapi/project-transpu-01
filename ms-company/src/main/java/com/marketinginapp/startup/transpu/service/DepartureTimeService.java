package com.marketinginapp.startup.transpu.service;

import com.marketinginapp.startup.transpu.api.payload.request.DepartureTimeCopyRequest;
import com.marketinginapp.startup.transpu.api.payload.request.DepartureTimeRequest;
import com.marketinginapp.startup.transpu.api.payload.response.DepartureTimeResponse;

import java.util.List;

public interface DepartureTimeService {

    DepartureTimeResponse save(DepartureTimeRequest request);

    DepartureTimeResponse update(DepartureTimeRequest request, Long id);

    DepartureTimeResponse findById(Long id);

    boolean copy(DepartureTimeCopyRequest request);

    DepartureTimeResponse updateStatus(Long id);

    List<DepartureTimeResponse> findAllByDay(Long day);
}
