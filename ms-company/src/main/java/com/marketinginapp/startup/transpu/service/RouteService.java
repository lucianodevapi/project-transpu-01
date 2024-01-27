package com.marketinginapp.startup.transpu.service;

import com.marketinginapp.startup.transpu.api.payload.request.RouteRequest;
import com.marketinginapp.startup.transpu.api.payload.request.RouteUpdateRequest;
import com.marketinginapp.startup.transpu.api.payload.request.StatusRequest;
import com.marketinginapp.startup.transpu.api.payload.response.RouteResponse;

import java.util.List;

public interface RouteService {

    RouteResponse save(RouteRequest request);

    RouteResponse update(RouteUpdateRequest request);

    RouteResponse findById(Long id);

    RouteResponse updateStatus(StatusRequest request);

    List<RouteResponse> findAllByCompanyId(Long id);
}
