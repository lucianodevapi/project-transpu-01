package com.marketinginapp.startup.transpu.api.payload.request;

import com.marketinginapp.startup.transpu.utils.Constant;
import com.marketinginapp.startup.transpu.utils.validator.TrimString;
import jakarta.validation.constraints.NotNull;

public record RouteMapRequest(

        @TrimString
        @NotNull(message = Constant.LATITUDE_MUST_NOT_BE_NULL)
        double latitude,

        @TrimString
        @NotNull(message = Constant.LONGITUDE_MUST_NOT_BE_NULL)
        double longitude,

        boolean go,

        String status,

        @NotNull(message = Constant.COMPANY_ID_MUST_NOT_BE_NULL)
        Long company,

        @NotNull(message = Constant.ROUTE_ID_MUST_NOT_BE_NULL)
        Long route
) {}
