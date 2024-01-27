package com.marketinginapp.startup.transpu.api.payload.response;

public record RouteMapResponse(

        Long id,

        double latitude,
        double longitude,

        boolean go,

        boolean status,

        Long company,

        Long route
) {}
