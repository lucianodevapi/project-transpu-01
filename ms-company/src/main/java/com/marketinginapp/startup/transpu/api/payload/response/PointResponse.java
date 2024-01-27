package com.marketinginapp.startup.transpu.api.payload.response;

import com.fasterxml.jackson.annotation.JsonInclude;

public record PointResponse(

        Long id,

        double latitude,

        double longitude,

        boolean go,

        boolean status,

        Long company,

        Long route,

        @JsonInclude(JsonInclude.Include.NON_NULL)
        Long path
) {}
