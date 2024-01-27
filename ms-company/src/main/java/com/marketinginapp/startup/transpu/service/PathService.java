package com.marketinginapp.startup.transpu.service;

import com.marketinginapp.startup.transpu.api.payload.request.PathRequest;
import com.marketinginapp.startup.transpu.api.payload.request.PathUpdateRequest;
import com.marketinginapp.startup.transpu.api.payload.response.PathResponse;

import java.util.List;

public interface PathService {

    PathResponse save(PathRequest request);

    PathResponse update(PathUpdateRequest request);

    PathResponse findById(Long id);

    PathResponse updateStatus(Long id);

    List<PathResponse> findAllByRoute(Long route);
}
