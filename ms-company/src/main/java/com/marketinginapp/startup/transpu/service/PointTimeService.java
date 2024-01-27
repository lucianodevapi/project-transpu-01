package com.marketinginapp.startup.transpu.service;

import com.marketinginapp.startup.transpu.api.payload.request.PointTimeRequest;
import com.marketinginapp.startup.transpu.api.payload.request.PointTimeUpdateRequest;
import com.marketinginapp.startup.transpu.api.payload.response.PointTimeResponse;

public interface PointTimeService {

    PointTimeResponse save(PointTimeRequest request);

    PointTimeResponse update(PointTimeUpdateRequest request);

    PointTimeResponse findById(Long id);

    PointTimeResponse updateStatus(Long id);

}
