package com.marketinginapp.startup.transpu.api.payload.request;

import com.marketinginapp.startup.transpu.utils.Constant;
import jakarta.validation.constraints.NotNull;

public record RouteMapUpdateRequest(

        @NotNull(message = Constant.LATITUDE_MUST_NOT_BE_NULL)
        Long id,

        @NotNull(message = Constant.LATITUDE_MUST_NOT_BE_NULL)
        Double latitude,

        @NotNull(message = Constant.LATITUDE_MUST_NOT_BE_NULL)
        Double longitude,

        boolean go
) {}
