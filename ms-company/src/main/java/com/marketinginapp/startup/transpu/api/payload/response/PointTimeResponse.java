package com.marketinginapp.startup.transpu.api.payload.response;

public record PointTimeResponse(

        Long id,

        String time,

        boolean status,

        Long company,

        Long route,

        Long day,

        Long departureTime,

        Long point
) {}
