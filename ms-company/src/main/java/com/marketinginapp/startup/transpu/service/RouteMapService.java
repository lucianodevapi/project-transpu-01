package com.marketinginapp.startup.transpu.service;

import com.marketinginapp.startup.transpu.api.payload.request.RouteMapRequest;
import com.marketinginapp.startup.transpu.api.payload.request.RouteMapUpdateRequest;
import com.marketinginapp.startup.transpu.api.payload.response.RouteMapResponse;

import java.util.List;

public interface RouteMapService {

    RouteMapResponse save(RouteMapRequest request);

    RouteMapResponse update(RouteMapUpdateRequest request);

    RouteMapResponse findById(Long id);

    RouteMapResponse updateStatus(Long id);

    List<RouteMapResponse> findAllByRoute(Long route);
}
