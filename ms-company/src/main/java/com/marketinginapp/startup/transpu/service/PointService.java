package com.marketinginapp.startup.transpu.service;

import com.marketinginapp.startup.transpu.api.payload.request.PointIdParamRequest;
import com.marketinginapp.startup.transpu.api.payload.request.PointRequest;
import com.marketinginapp.startup.transpu.api.payload.request.PointUpdateRequest;
import com.marketinginapp.startup.transpu.api.payload.response.PointResponse;

import java.util.List;

public interface PointService {

    PointResponse save(PointRequest request);

    PointResponse update(PointUpdateRequest request);

    PointResponse findById(Long id);

    PointResponse updateStatus(Long id);

    List<PointResponse> findAllByRoute(Long route);

    List<PointResponse> findAllByRouteAndDistrict(PointIdParamRequest request);

    List<PointResponse> findAllByRouteAndAddress(PointIdParamRequest request);
}
