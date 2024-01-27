package com.marketinginapp.startup.transpu.api.payload.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.marketinginapp.startup.transpu.utils.Constant;
import jakarta.validation.constraints.NotNull;

public record PointTimeRequest(

        @NotNull(message = Constant.MUST_NOT_BE_NULL_OR_EMPTY)
        String time,

        boolean status,
        @NotNull(message = Constant.COMPANY_ID_MUST_NOT_BE_NULL)
        Long company,

        @NotNull(message = Constant.ROUTE_ID_MUST_NOT_BE_NULL)
        Long route,

        @NotNull(message = Constant.DAY_ID_MUST_NOT_BE_NULL)
        Long day,

        @NotNull(message = Constant.DEPARTURE_TIME_ID_MUST_NOT_BE_NULL)
        @JsonProperty("departure_time")
        Long departureTime,

        @NotNull(message = Constant.POINT_ID_MUST_NOT_BE_NULL)
        Long point
) {
}
